package org.example.taskmanagementsystem.controllers.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.taskmanagementsystem.dao.EmployeeDao;
import org.example.taskmanagementsystem.models.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "AddEmployeeController", value = "/addEmployee")
public class AddEmployeeController extends HttpServlet {
    private final Logger logger = Logger.getLogger(AddEmployeeController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            EmployeeDao employeeDao = new EmployeeDao();
            List<Employee> employeeList = employeeDao.getAllEmployees();

            request.setAttribute("employeeList", employeeList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/employee/addEmployee.jsp");
            dispatcher.forward(request, response);
        }catch (SQLException e){

            logger.info("Error getting employee list: "+ e.getMessage());

            request.setAttribute("errorMessage","Error getting employee list");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");

        if(firstName.isBlank() && lastName.isBlank()){

            request.setAttribute("errorMessage","First name and last name are required");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try{
            EmployeeDao employeeDao = new EmployeeDao();
            Employee employee = new Employee(firstName,lastName);

            employeeDao.addEmployee(employee);
            //redirect instead of using dispatch to prevent form resubmission
            response.sendRedirect(request.getContextPath() +"/addEmployee");

        }catch (SQLException e){
            logger.info("Error creating employee: "+ e.getMessage());

            request.setAttribute("errorMessage","Error creating employee");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/templates/errors/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
