<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Expense Details</title>
    <link rel="stylesheet" href="ExpenceAplication.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar">
        <div class="logo">
            <h1>Expense Tracker</h1>
        </div>
        <div class="nav-right">
            <a href="Expenses_See_All" class="btn">Back to All Expenses</a>
            <a href="index.jsp" id="logoutBtn" class="btn">Logout</a>
        </div>
    </nav>

    <!-- Expense Details -->
    <div class="container">
        <h2>Expense Details</h2>
        <table class="expense-details-table">
            <tr>
                <th>Category</th>
                <td><c:out value="${expense.category}" /></td>
            </tr>
            <tr>
                <th>Amount</th>
                <td><c:out value="${expense.amount}" /></td>
            </tr>
            <tr>
                <th>Comments</th>
                <td><c:out value="${expense.comments}" /></td>
            </tr>
            <tr>
                <th>Date</th>
                <td><c:out value="${expense.date}" /></td>
            </tr>
        </table>

        <!-- Action Buttons -->
        <div class="action-buttons">
            <a href="expenses_update.jsp?expense_id=<c:out value='${expense.id}' />" class="btn">Edit</a>
            <a href="expenses_delete.jsp?expense_id=<c:out value='${expense.id}' />" class="btn" onclick="return confirm('Are you sure you want to delete this expense?');">Delete</a>
        </div>
    </div>

    <script src="ExpenceAplication.js"></script>
</body>
</html>
