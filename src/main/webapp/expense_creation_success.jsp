<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Expense Saved</title>
    <link rel="stylesheet" href="ExpenceAplication.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar">
        <div class="logo">
            <h1>Expense Tracker</h1>
        </div>
        <div class="nav-right">
            <!-- Logout button -->
            <a href="index.jsp" id="logoutBtn" class="btn back-to-home-btn">Logout</a>
        </div>
    </nav>

    <!-- Success Message -->
    <div class="container">
        <section>
            <h2>Expense Saved Successfully!</h2>
            <p>Your expense has been added to the system.</p>
        </section>

        <!-- Navigation Options -->
        <div class="menu">
            <a href="expenses_create.jsp" class="btn">Go to Dashboard</a>
            <a href="expenses_read.jsp" class="btn">View All Expenses</a>
        </div>
    </div>

    <script src="ExpenceAplication.js"></script>
</body>
</html>
