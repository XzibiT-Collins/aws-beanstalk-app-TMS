<%@ page import="java.util.List" %>
<%@ page import="org.example.taskmanagementsystem.models.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Management</title>
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

        .table {
            border-radius: 0 0 1rem 1rem;
            overflow: hidden;
        }

        .status-badge {
            padding: 0.35rem 0.65rem;
            border-radius: 50rem;
            font-size: 0.75rem;
            font-weight: 600;
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

        .footer {
            background-color: var(--primary-color);
            color: var(--light-text);
            padding: 1.5rem 0;
            margin-top: 2rem;
        }
    </style>
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">NovaTech Task Manager</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="index.jsp">Manage Tasks</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Header -->
<header class="page-header text-center">
    <div class="container">
        <h1 class="fw-bold">NovaTech Task Management</h1>
        <p class="lead">Create and manage your tasks in one place</p>
    </div>
</header>

<div class="container">
    <!-- Create Task Section -->
    <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
            <span>Create New Employee</span>
        </div>
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/addEmployee" method="post">
                <div class="row g-3">
                    <div class="form-floating col-md-6">
                        <input type="text" class="form-control" id="floatingInput" name="first_name" required>
                        <label for="floatingInput" class="form-label">First Name</label>
                    </div>
                    <div class="form-floating col-md-6">
                        <input type="text" class="form-control" id="floatingInput" name="last_name" required>
                        <label for="floatingInput" class="form-label">Last Name</label>
                    </div>

                    <div class="col-12">
                        <button type="submit" class="btn btn-primary">Create Employee</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- Task List Section -->
    <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
            <span>Employee List</span>
            <span>Number of Employees: <%= employeeList != null ? employeeList.size() : 0 %></span>
        </div>
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover mb-0">
                    <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        if(employeeList != null){
                            for(Employee employee: employeeList){
                    %>
                    <tr>
                        <td><%= employee.getEmployeeId() %></td>
                        <td><%= employee.getFirstName() %></td>
                        <td><%= employee.getLastName() %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/deleteEmployee?employeeId=<%= employee.getEmployeeId() %>" class="btn btn-sm btn-danger" onauxclick="alert('Are you sure you want to delete employee: <%= employee.getEmployeeId() %>')">Delete</a>
                        </td>
                    </tr>
                    <%
                        }
                    }else{
                    %>
                    <tr>
                        <td colspan="7" class="text-center py-4">No Employees found.</td>
                    </tr>
                    <% }%>
                    </tbody>

                </table>
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

<!-- Bootstrap JS (for navbar toggle only) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>