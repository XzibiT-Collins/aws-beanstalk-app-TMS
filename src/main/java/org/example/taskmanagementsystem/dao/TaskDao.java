package org.example.taskmanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.example.taskmanagementsystem.db.DatabaseConnector;
import org.example.taskmanagementsystem.models.Task;

public class TaskDao {
    Logger logger = Logger.getLogger(TaskDao.class.getName());

    //get Task by ID
    public Task getTaskById(int id) throws SQLException {
        String query = "SELECT * FROM task WHERE id = ?";

        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Task task = new Task(
                        resultSet.getInt("user_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getDate("due_date")
                );
                task.setId(resultSet.getInt("id")); //set id on a task
                return task;
            }
        }catch (SQLException e){
            throw new SQLException();
        }
        return null;
    }

    public void addTask(Task task)throws SQLException{
        System.out.println("Adding task");
        String query = "INSERT INTO task(title,description,status,due_date,user_id) VALUES(?,?,?,?,?)";

        try(Connection connection = DatabaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,task.getTitle());
            preparedStatement.setString(2,task.getDescription());
            preparedStatement.setString(3,task.getStatus());
            preparedStatement.setDate(4, task.getDueDate());
            preparedStatement.setInt(5,task.getUserId());

            //create the task
            preparedStatement.executeUpdate();

            logger.info("Task created successfully");
        }catch (SQLException e){
            logger.info("Error creating task: "+ e.getMessage());
            throw new SQLException();
        }
    }

    public List<Task> getAllTasks()throws SQLException{
        String query = "SELECT * FROM task";
        try(Connection connection = DatabaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            List<Task> tasksList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                //create a task object from the result set
                Task task = new Task(
                        resultSet.getInt("user_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getDate("due_date")
                );
                task.setId(resultSet.getInt("id"));

                tasksList.add(task); //add a task to the list
            }
            return tasksList;
        }catch (SQLException e){
            throw new SQLException();
        }
    }

    public void deleteTask(int id) throws SQLException{
        String query = "DELETE FROM task WHERE id = ?";
        try(Connection connection = DatabaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            logger.info("Task deleted successfully");
        }catch (SQLException e){
            throw new SQLException();
        }
    }

    public void updateTask(Task task) throws SQLException{

        System.out.println("id:" + task.getId());
        String query = "UPDATE task SET title = ?, description = ?, status = ?, due_date = ?, user_id = ? WHERE id = ?";
        try(Connection connection = DatabaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,task.getTitle());
            preparedStatement.setString(2,task.getDescription());
            preparedStatement.setString(3,task.getStatus());
            preparedStatement.setDate(4,task.getDueDate());
            preparedStatement.setInt(5,task.getUserId());
            preparedStatement.setInt(6,task.getId());

            preparedStatement.executeUpdate();
            logger.info("Task updated successfully");
        }
    }
}
