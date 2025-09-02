package org.example.taskmanagementsystem.controllers.task;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.taskmanagementsystem.dao.EmployeeDao;
import org.example.taskmanagementsystem.dao.TaskDao;
import org.example.taskmanagementsystem.models.Employee;
import org.example.taskmanagementsystem.models.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/viewTask")
public class ViewTasks extends HttpServlet {
    private final Logger logger = Logger.getLogger(ViewTasks.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String taskId_str = request.getParameter("taskId");

        if(taskId_str == null || taskId_str.isEmpty()){

            request.setAttribute("errorMessage","Please enter a valid task Id");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request,response);
            return;
        }

        int taskId = Integer.parseInt(request.getParameter("taskId"));

        try{
            TaskDao taskDao = new TaskDao();
            Task task = taskDao.getTaskById(taskId);

            EmployeeDao employeeDao = new EmployeeDao();
            List<Employee> employeeList = employeeDao.getAllEmployees();

            request.setAttribute("task",task);
            request.setAttribute("employeeList",employeeList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/templates/viewTasks.jsp");
            requestDispatcher.forward(request,response);
        }catch (SQLException e){

            logger.info("Error getting task: "+ e.getMessage());

            request.setAttribute("errorMessage","Error fetching task");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request,response);
        }
    }
}
