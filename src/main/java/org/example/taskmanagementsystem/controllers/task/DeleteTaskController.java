package org.example.taskmanagementsystem.controllers.task;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.taskmanagementsystem.dao.TaskDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "DeleteTaskController", value = "/deleteTask")
public class DeleteTaskController extends HttpServlet {
    private final Logger logger = Logger.getLogger(DeleteTaskController.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String taskId_str = request.getParameter("taskId");

        if(taskId_str == null || taskId_str.isEmpty()){

            request.setAttribute("errorMessage","Please enter a valid task ID");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request,response);
            return;
        }

        int taskId = Integer.parseInt(request.getParameter("taskId"));

        try{
            TaskDao taskDao = new TaskDao();
            taskDao.deleteTask(taskId);

            response.sendRedirect(request.getContextPath() +"/addTask");
        }catch (SQLException e){

            logger.info("Error deleting task: "+ e.getMessage());

            request.setAttribute("errorMessage","Unable to delete task");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request,response);
        }
    }
}
