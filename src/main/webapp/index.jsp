<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Management System</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        :root {
            --primary-color: #5d8aa8;
            --secondary-color: #a8c5d8;
            --accent-color: #f0f7fa;
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

        .hero-section {
            background: linear-gradient(135deg, var(--primary-color) 0%, var(--secondary-color) 100%);
            padding: 5rem 0;
            color: var(--light-text);
            border-radius: 0 0 2rem 2rem;
            margin-bottom: 3rem;
        }

        .feature-card {
            border: none;
            border-radius: 1rem;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
            height: 100%;
        }

        .feature-card:hover {
            transform: translateY(-5px);
        }

        .feature-icon {
            font-size: 2.5rem;
            color: var(--primary-color);
            margin-bottom: 1rem;
        }

        .btn-primary {
            background-color: var(--primary-color);
            border-color: var(--primary-color);
        }

        .btn-primary:hover {
            background-color: #4a7a98;
            border-color: #4a7a98;
        }

        .footer {
            background-color: var(--primary-color);
            color: var(--light-text);
            padding: 2rem 0;
            margin-top: 3rem;
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
                    <a class="nav-link active" href="<%= request.getContextPath() %>">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>">Manage Tasks</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Hero Section -->
<section class="hero-section text-center">
    <div class="container">
        <h1 class="display-4 fw-bold mb-4">Simplify Your Task Management</h1>
        <p class="lead mb-5">Organize, track, and complete your tasks efficiently with our easy-to-use task management system.</p>
        <a href="<%= request.getContextPath() %>/addTask" class="btn btn-light btn-lg px-5 py-3 fw-bold">Get Started</a>
    </div>
</section>

<!-- Features Section -->
<section class="container mb-5">
    <div class="row g-4">
        <div class="col-md-4">
            <div class="card feature-card p-4">
                <div class="card-body text-center">
                    <div class="feature-icon">📝</div>
                    <h3 class="card-title">Create Tasks</h3>
                    <p class="card-text">Easily create new tasks with titles, descriptions, due dates, and assign them to users.</p>
                    <a href="<%= request.getContextPath() %>/addTask" class="btn btn-primary">Create Task</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card feature-card p-4">
                <div class="card-body text-center">
                    <div class="feature-icon">📋</div>
                    <h3 class="card-title">Manage Tasks</h3>
                    <p class="card-text">View all your tasks in one place, update their status, and track progress.</p>
                    <a href="<%= request.getContextPath() %>/addTask" class="btn btn-primary">View Tasks</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card feature-card p-4">
                <div class="card-body text-center">
                    <div class="feature-icon">👥</div>
                    <h3 class="card-title">Employees</h3>
                    <p class="card-text">Create and manage employees.</p>
                    <a href="<%= request.getContextPath() %>/addEmployee" class="btn btn-primary">Manage Employees</a>
                </div>
            </div>
        </div>
    </div>
</section>

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