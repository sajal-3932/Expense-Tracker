<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Expense Creation Failed</title>
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

    <!-- Error Message -->
    <div class="container">
        <section>
            <h2>Expense Creation Failed</h2>
            <p>There was an error while trying to save your expense. Please try again.</p>
        </section>

        <!-- Navigation Options -->
        <div class="menu">
            <a href="dashboard.jsp" class="btn">Go to Dashboard</a>
            <a href="add_expense_form.jsp" class="btn">Retry Adding Expense</a>
        </div>
    </div>

    <script src="ExpenceAplication.js"></script>
</body>
</html>
