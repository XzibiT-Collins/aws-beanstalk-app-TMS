<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Error - Task Management System</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap Icons -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
  <!-- Custom CSS -->
  <style>
    :root {
      --primary-color: #5d8aa8;
      --secondary-color: #a8c5d8;
      --accent-color: #f0f7fa;
      --danger-color: #e76f51;
      --text-color: #2c3e50;
      --light-text: #f8f9fa;
    }

    body {
      background-color: var(--accent-color);
      color: var(--text-color);
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      min-height: 100vh;
      display: flex;
      flex-direction: column;
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

    .error-container {
      flex: 1;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      padding: 2rem;
    }

    .error-card {
      border: none;
      border-radius: 1rem;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
      max-width: 600px;
      width: 100%;
    }

    .error-icon {
      font-size: 4rem;
      color: var(--danger-color);
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
      padding: 1.5rem 0;
      margin-top: auto;
    }
  </style>
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark">
  <div class="container">
    <a class="navbar-brand" href="index.jsp">Task Manager</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" href="index.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="task-management.jsp">Manage Tasks</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<!-- Error Content -->
<div class="error-container">
  <div class="error-card card">
    <div class="card-body text-center p-5">
      <div class="error-icon">
        <i class="bi bi-exclamation-triangle"></i>
      </div>
      <h2 class="mb-4">Error Occurred</h2>
      <p class="lead mb-4">
        <%-- Display the error message if available --%>
        <%= request.getAttribute("errorMessage") != null ?
                request.getAttribute("errorMessage") :
                "Sorry, an unexpected error has occurred. Please try again later." %>
      </p>
      <div class="d-grid gap-2 d-md-block">
        <a href="javascript:history.back()" class="btn btn-secondary me-md-2">Go Back</a>
        <a href="index.jsp" class="btn btn-primary">Return to Home</a>
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