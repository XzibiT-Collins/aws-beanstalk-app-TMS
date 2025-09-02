package org.example.taskmanagementsystem.controllers.task;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.taskmanagementsystem.dao.TaskDao;
import org.example.taskmanagementsystem.models.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "UpdateTaskStatusController" , value = "/updateTaskStatus")
public class UpdateTaskStatusController extends HttpServlet {
    private final Logger logger = Logger.getLogger(UpdateTaskStatusController.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskId_str = request.getParameter("taskId");
        String status = request.getParameter("status");

        if(!status.equalsIgnoreCase("Completed") && !status.equalsIgnoreCase("In Progress") && !status.equalsIgnoreCase("Pending")){

            request.setAttribute("errorMessage","Invalid task status specified");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request,response);
            return;
        }

        if(taskId_str == null || taskId_str.isEmpty()){

            request.setAttribute("errorMessage","Please enter a valid task ID");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request,response);
            return;
        }

        int taskId = Integer.parseInt(request.getParameter("taskId"));

        //get and update task status
        try{
            TaskDao taskDao = new TaskDao();
            Task task = taskDao.getTaskById(taskId);

            if(task != null){
                task.setStatus(status);
                taskDao.updateTask(task); // update task
                request.setAttribute("task",task);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/viewTasks.jsp");
            dispatcher.forward(request, response);
        }catch (SQLException e){
            logger.info("Error in updating task status"+ e.getMessage());

            request.setAttribute("errorMessage","Error updating task status");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
