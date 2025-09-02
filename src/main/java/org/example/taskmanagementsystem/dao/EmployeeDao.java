package org.example.taskmanagementsystem.dao;

import org.example.taskmanagementsystem.db.DatabaseConnector;
import org.example.taskmanagementsystem.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EmployeeDao {
    private final Logger logger = Logger.getLogger(EmployeeDao.class.getName());

    //get employee by ID
    public Employee getEmployeeById(int id) throws SQLException {
        String query = "SELECT * FROM employee WHERE id = ?";

        try(Connection connection = DatabaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Employee employee = new Employee(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
                employee.setEmployeeId(resultSet.getInt("id"));
                return employee;
            }
        }catch (SQLException e){
            logger.info("Error getting employee: "+ e.getMessage());
            throw new SQLException();
        }
        return null;
    }

    //create an Employee
    public void addEmployee(Employee employee) throws SQLException{
        String query = "INSERT INTO employee(first_name,last_name) VALUES(?,?)";

        try(Connection connection = DatabaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.info("Error creating employee: "+ e.getMessage());
            throw new SQLException();
        }
    }

    // update an employee
    public void updateEmployee(Employee employee) throws SQLException{
        String query = "UPDATE employee SET first_name = ?, last_name = ? WHERE id = ?";

        try(Connection connection = DatabaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,employee.getFirstName());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.info("Error updating employee: "+ e.getMessage());
            throw new SQLException();
        }
    }

    // delete an employee
    public void deleteEmployee(int id) throws SQLException{
        String query = "DELETE FROM employee WHERE id = ?";

        try(Connection connection = DatabaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.info("Error deleting employee: "+ e.getMessage());
            throw new SQLException();
        }
    }

    //get all employees
    public List<Employee> getAllEmployees() throws SQLException{
        String query = "SELECT * FROM employee";
        List<Employee> employeesList = new ArrayList<>();

        try(Connection connection = DatabaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Employee employee = new Employee(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
                employee.setEmployeeId(resultSet.getInt("id"));
                employeesList.add(employee);
            }
        }
        return  employeesList;
    }
}
