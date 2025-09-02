package org.example.taskmanagementsystem.controllers.task;

import org.example.taskmanagementsystem.dao.EmployeeDao;
import org.example.taskmanagementsystem.dao.TaskDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.taskmanagementsystem.models.Employee;
import org.example.taskmanagementsystem.models.Task;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AddTaskController", value = "/addTask")
public class AddTaskController extends HttpServlet{
    private final Logger logger = Logger.getLogger(AddTaskController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            TaskDao taskDao = new TaskDao();
            List<Task> taskList = taskDao.getAllTasks(); // get all Tasks

            //Get all employees from db
            EmployeeDao employeeDao = new EmployeeDao();
            List<Employee> employeeList = employeeDao.getAllEmployees();

            // Add taskList to request scope
            request.setAttribute("taskList", taskList);
            request.setAttribute("employeeList", employeeList);

            // Forward to JSP
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/templates/addTask.jsp");
            requestDispatcher.forward(request, response);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());

            request.setAttribute("errorMessage","Error getting task list");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //call dao to create a new task
        String id_str = request.getParameter("userId");
        if(id_str.isEmpty()){

            request.setAttribute("errorMessage","User id is required");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request,response);
            return;
        }
        //get all other inputs and convert
        int userId = Integer.parseInt(request.getParameter("userId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        Date dueDate = Date.valueOf(request.getParameter("dueDate"));

        if(userId <= 0 || title.isBlank() || description.isBlank() || status.isBlank() || dueDate == null){

            request.setAttribute("errorMessage","Required fields are missing");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request,response);
            return;
        }

        try{
            TaskDao taskDao = new TaskDao();
            //create a task object
            Task task = new Task(userId,title,description,status,dueDate);
            taskDao.addTask(task); //creates the task in db

            response.sendRedirect(request.getContextPath() +"/addTask"); // redirect user
        }catch (SQLException e){

            logger.info("Error creating task: "+ e.getMessage());

            request.setAttribute("errorMessage","Error adding task.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request,response);
        }
    }
}
