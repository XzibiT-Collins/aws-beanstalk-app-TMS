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

@WebServlet(name = "FilterTaskController", value = "/filterTasks")
public class FilterTaskController extends HttpServlet {
    private final Logger logger = Logger.getLogger(FilterTaskController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");

        if(status == null || status.isBlank()){
            request.setAttribute("errorMessage","Status is required");
            request.getRequestDispatcher("views/templates/errors/error.jsp").forward(request,response);
            return;
        }

        try{
            //filter tasks based on status
            TaskDao taskDao = new TaskDao();
            List<Task> taskList = taskDao.getAllTasks();

            //get all employees from db
            EmployeeDao employeeDao = new EmployeeDao();
            List<Employee> employeeList = employeeDao.getAllEmployees();

            //filter tasks using streams API
            switch (status){
                case "Pending":
                    taskList = taskList.stream().filter(task -> task.getStatus().equalsIgnoreCase("Pending")).toList();
                    break;
                case "In Progress":
                    taskList = taskList.stream().filter(task -> task.getStatus().equalsIgnoreCase("In Progress")).toList();
                    break;
                case "Completed":
                    taskList = taskList.stream().filter(task -> task.getStatus().equalsIgnoreCase("Completed")).toList();
                    break;
            }

            //pass task List to JSP
            request.setAttribute("taskList",taskList);
            request.setAttribute("employeeList",employeeList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/addTask.jsp");
            dispatcher.forward(request,response);

        }catch (SQLException e){
            logger.info("Error filtering tasks: "+ e.getMessage());
            request.setAttribute("errorMessage","Error filtering tasks");
            request.getRequestDispatcher("views/templates/errors/error.jsp").forward(request,response);
        }
    }
}
