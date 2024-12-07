<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link rel="stylesheet" href="ExpenceAplication.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar">
        <div class="logo">
            <h1>Expense Tracker</h1>
        </div>
        <div class="nav-right">
            <a href="index.jsp" class="btn">Home</a>
            <a href="Expenses_See_All" class="btn">All Expenses</a>
        </div>
    </nav>

    <!-- Error Message -->
    <div class="error-container">
        <h2>Error</h2>
        <p>${errorMessage}</p>
        <a href="expenses_search.jsp" class="btn">Back to Search</a>
    </div>
</body>
</html>
