<%@ page import="org.example.taskmanagementsystem.models.Task" %>
<%@ page import="org.example.taskmanagementsystem.models.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
    Task task = (Task) request.getAttribute("task");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Details</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        :root {
            --primary-color: #5d8aa8;
            --secondary-color: #a8c5d8;
            --accent-color: #f0f7fa;
            --success-color: #6b9080;
            --warning-color: #e9c46a;
            --danger-color: #e76f51;
            --text-color: #2c3e50;
            --light-text: #f8f9fa;
        }

        body {
            background-color: var(--accent-color);
            color: var(--text-color);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .navbar {
            background-color: var(--primary-color);
        }

        .page-header {
            background: linear-gradient(135deg, var(--primary-color) 0%, var(--secondary-color) 100%);
            padding: 2rem 0;
            color: var(--light-text);
            border-radius: 0 0 1rem 1rem;
            margin-bottom: 2rem;
        }

        .card {
            border: none;
            border-radius: 1rem;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-bottom: 2rem;
        }

        .card-header {
            background-color: var(--primary-color);
            color: var(--light-text);
            border-radius: 1rem 1rem 0 0 !important;
            padding: 1rem 1.5rem;
            font-weight: 600;
        }

        .btn-primary {
            background-color: var(--primary-color);
            border-color: var(--primary-color);
        }

        .btn-primary:hover {
            background-color: #4a7a98;
            border-color: #4a7a98;
        }

        .btn-success {
            background-color: var(--success-color);
            border-color: var(--success-color);
        }

        .btn-warning {
            background-color: var(--warning-color);
            border-color: var(--warning-color);
            color: var(--text-color);
        }

        .btn-danger {
            background-color: var(--danger-color);
            border-color: var(--danger-color);
        }

        .form-control:focus, .form-select:focus {
            border-color: var(--secondary-color);
            box-shadow: 0 0 0 0.25rem rgba(168, 197, 216, 0.25);
        }

        .status-badge {
            padding: 0.5rem 1rem;
            border-radius: 50rem;
            font-size: 0.875rem;
            font-weight: 600;
            display: inline-block;
        }

        .status-pending {
            background-color: var(--warning-color);
            color: var(--text-color);
        }

        .status-completed {
            background-color: var(--success-color);
            color: var(--light-text);
        }

        .status-progress {
            background-color: var(--primary-color);
            color: var(--light-text);
        }

        .status-cancelled {
            background-color: var(--danger-color);
            color: var(--light-text);
        }

        .detail-label {
            font-weight: 600;
            color: #5d8aa8;
        }

        .footer {
            background-color: var(--primary-color);
            color: var(--light-text);
            padding: 1.5rem 0;
            margin-top: 2rem;
        }

        .modal-header {
            background-color: var(--primary-color);
            color: var(--light-text);
        }

        .modal-content {
            border-radius: 1rem;
            border: none;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand" href="<%= request.getContextPath() %>">Task Manager</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>/addTask">Manage Tasks</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Header -->
<header class="page-header text-center">
    <div class="container">
        <h1 class="fw-bold">Task Details</h1>
        <p class="lead">View and manage task details</p>
    </div>
</header>

<div class="container">
    <div class="row">
        <div class="col-lg-8 mx-auto">
            <!-- Task Detail Card -->
            <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <span>Task #<%= task.getId() %></span>
                    <div>
                        <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#updateTaskModal">
                            Edit Task
                        </button>
                        <a href="<%= request.getContextPath() %>/deleteTask?taskId=<%= task.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this task?')">
                            Delete Task
                        </a>
                    </div>
                </div>
                <div class="card-body">
                    <div class="row mb-4">
                        <div class="col-md-8">
                            <h3 class="mb-3"><%= task.getTitle() %></h3>
                            <p class="mb-4"><%= task.getDescription() %></p>
                        </div>
                        <div class="col-md-4 text-md-end">
                            <%
                                String statusClass = "";
                                if(task.getStatus().equalsIgnoreCase("Pending")) {
                                    statusClass = "status-pending";
                                } else if(task.getStatus().equalsIgnoreCase("Completed")) {
                                    statusClass = "status-completed";
                                } else if(task.getStatus().equalsIgnoreCase("In Progress")) {
                                    statusClass = "status-progress";
                                }
                            %>
                            <span class="status-badge <%= statusClass %>"><%= task.getStatus() %></span>
                        </div>
                    </div>

                    <div class="row g-4">
                        <div class="col-md-6">
                            <p><span class="detail-label">Due Date:</span> <%= task.getDueDate() %></p>
                        </div>
                        <div class="col-md-6">
                            <p><span class="detail-label">Assigned To:</span> User #<%= task.getUserId() %></p>
                        </div>
                    </div>

                    <hr class="my-4">

                    <!-- Status Update Form -->
                    <form action="<%= request.getContextPath() %>/updateTaskStatus" method="post" class="mt-4">
                        <input type="hidden" name="taskId" value="<%= task.getId() %>">
                        <div class="row g-3 align-items-center">
                            <div class="col-md-4">
                                <label for="newStatus" class="form-label detail-label">Update Status:</label>
                            </div>
                            <div class="col-md-5">
                                <select class="form-select" id="newStatus" name="status" required>
                                    <option value="">Select new status</option>
                                    <option value="Pending" <%= task.getStatus().equalsIgnoreCase("Pending") ? "selected" : "" %>>Pending</option>
                                    <option value="In Progress" <%= task.getStatus().equalsIgnoreCase("In-Progress") ? "selected" : "" %>>In Progress</option>
                                    <option value="Completed" <%= task.getStatus().equalsIgnoreCase("Completed") ? "selected" : "" %>>Completed</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-primary w-100">Update</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <a href="<%= request.getContextPath() %>/addTask" class="btn btn-secondary">Back to Task List</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Update Task Modal -->
<div class="modal fade" id="updateTaskModal" tabindex="-1" aria-labelledby="updateTaskModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateTaskModalLabel">Update Task</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="updateTaskForm" action="<%= request.getContextPath() %>/updateTask" method="post">
                    <input type="hidden" name="taskId" value="<%= task.getId() %>">
                    <div class="row g-3">
                        <div class="form-floating col-md-6">
                            <input type="text" class="form-control" id="floatingInput" name="title" value="<%= task.getTitle() %>" required>
                            <label for="floatingInput" class="form-label">Title</label>
                        </div>
                        <div class="form-floating col-md-6">
                            <select class="form-select" id="floatingSelect" name="status" required>
                                <option value="Pending" <%= task.getStatus().equals("Pending") ? "selected" : "" %>>Pending</option>
                                <option value="In Progress" <%= task.getStatus().equals("In Progress") ? "selected" : "" %>>In Progress</option>
                                <option value="Completed" <%= task.getStatus().equals("Completed") ? "selected" : "" %>>Completed</option>
                                <option value="Cancelled" <%= task.getStatus().equals("Cancelled") ? "selected" : "" %>>Cancelled</option>
                            </select>
                            <label for="floatingSelect" class="form-label">Status</label>
                        </div>
                        <div class="form-floating col-md-12">
                            <textarea class="form-control" id="floatingTextarea" name="description" rows="3"><%= task.getDescription() %></textarea>
                            <label for="floatingTextarea" class="form-label">Description</label>
                        </div>
                        <div class="form-floating col-md-6">
                            <input type="date" class="form-control" id="floatingInput" name="dueDate" value="<%= task.getDueDate() %>" required>
                            <label for="floatingInput" class="form-label">Due Date</label>
                        </div>
                        <div class="form-floating col-md-6">
                            <select name="userId" id="floatingSelect" class="form-select">
                                <option value="<%= task.getUserId() %>">
                                    <%
                                        Employee employee = null;
                                    %>
                                    <%
                                        employee = employeeList.stream()
                                                .filter(emp -> emp.getEmployeeId() == task.getUserId())
                                                .findFirst()
                                                .orElse(null);
                                        if(employee != null){
                                    %>
                                    <%= employee.getEmployeeId() %> : <%= employee.getLastName() %> <%= employee.getFirstName() %>
                                    <%
                                        }
                                    %>

                                </option>
                                <%
                                    for (Employee emp : employeeList) {

                                %>
                                <option value="<%=emp.getEmployeeId()%>"> <%=emp.getEmployeeId()%> :
                                <%=emp.getLastName()%> <%=emp.getFirstName()%></option>
                            <%
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="text-end mt-4">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary updateTask">Save Changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="footer text-center">
    <div class="container">
        <p class="mb-0">© 2025 Task Management System. All rights reserved.</p>
    </div>
</footer>

<!-- Bootstrap JS (for modal and navbar toggle) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>

<script>
    <%--$(document).ready(function(){--%>
    <%--    $('.updateTaskForm').on('submit', function (e){--%>
    <%--        e.preventDefault();--%>

    <%--        var form = $(this).serialize();--%>

    <%--        $.ajax({--%>
    <%--            url: '<%= request.getContextPath() %>/updateTask',--%>
    <%--            type: 'post',--%>
    <%--            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },--%>
    <%--            data: form,--%>
    <%--            success: function (response){--%>
    <%--                if(response.status === 'success'){--%>
    <%--                    location.reload();--%>
    <%--                }--%>
    <%--            },--%>
    <%--            error: function (error){--%>
    <%--                alert('Error updating task');--%>
    <%--            }--%>
    <%--        })--%>

    <%--    })--%>
    <%--})--%>
</script>
</body>
</html>