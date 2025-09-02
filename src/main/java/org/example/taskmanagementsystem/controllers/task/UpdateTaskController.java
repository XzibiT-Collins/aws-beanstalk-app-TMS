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
import java.sql.Date;

@WebServlet(name = "UpdateTaskController" , value = "/updateTask")
public class UpdateTaskController extends HttpServlet {
    private final Logger logger = Logger.getLogger(UpdateTaskController.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskId_str = request.getParameter("taskId");
        String date_str = request.getParameter("dueDate");
        if(taskId_str == null || taskId_str.isEmpty() || date_str == null || date_str.isEmpty()){

            request.setAttribute("errorMessage","Please enter a valid task ID or a valid date");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request,response);
            return;
        }

        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String status = request.getParameter("status");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Date dueDate = Date.valueOf(request.getParameter("dueDate"));
        int userId =Integer.parseInt(request.getParameter("userId"));

        if(title.isBlank() || description.isBlank() || status.isBlank() || dueDate == null){

            request.setAttribute("errorMessage","Required fields are missing");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request,response);
            return;
        }

        //get and update task status
        try{
            TaskDao taskDao = new TaskDao();
            Task task = taskDao.getTaskById(taskId);

            if(task != null){
                task.setStatus(status);
                task.setUserId(userId);
                task.setTitle(title);
                task.setDescription(description);
                task.setDueDate(dueDate);

                taskDao.updateTask(task); // update task
                request.setAttribute("task",task);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/viewTasks.jsp");
            dispatcher.forward(request, response);
        }catch (SQLException e){

            logger.info("Error updating task"+ e.getMessage());

            request.setAttribute("errorMessage","Error updating task");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
