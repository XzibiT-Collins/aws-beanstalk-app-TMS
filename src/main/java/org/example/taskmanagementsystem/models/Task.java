package org.example.taskmanagementsystem.models;

import java.sql.Date;

public class Task {
    private int id;
    private int userId;
    private String title;
    private String description;
    private String status;
    private Date dueDate;

    public Task(int userId,String title, String description, String status, Date dueDate) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }
    public Task(int id,int userId,String title, String description, String status, Date dueDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    //getters
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getStatus() {
        return status;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public int getUserId() {
        return userId;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
