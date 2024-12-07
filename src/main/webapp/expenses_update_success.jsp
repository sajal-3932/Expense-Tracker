<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Successful</title>
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

    <!-- Success Message -->
    <div class="container">
        <h2>Expense Updated Successfully!</h2>
        <p>Your expense has been successfully updated in the database.</p>
        <a href="Expenses_See_All" class="btn">Go to All Expenses</a>
    </div>
</body>
</html>
