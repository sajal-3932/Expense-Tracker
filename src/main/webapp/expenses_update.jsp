<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Failed</title>
    <link rel="stylesheet" href="ExpenceAplication.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar">
        <div class="logo">
            <h1>Expense Tracker</h1>
        </div>
        <div class="nav-right">
            <a href="Expenses_See_All" class="btn">All Expenses</a>
            <a href="index.jsp" class="btn">Logout</a>
        </div>
    </nav>

    <!-- Failure Message -->
    <div class="container">
        <h2>Update Failed</h2>
        <p>Sorry, there was an issue updating the expense. Please try again later.</p>
        <a href="expenses_update.jsp" class="btn">Try Again</a>
    </div>
</body>
</html>
