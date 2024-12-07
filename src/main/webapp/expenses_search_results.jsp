<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Results</title>
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
            <a href="index.jsp" class="btn">Logout</a>
        </div>
    </nav>

    <!-- Search Results -->
    <div class="container">
        <h2>Search Results</h2>

        <!-- Display a Message if No Results Are Found -->
        <c:if test="${empty expenses}">
            <p>No matching expenses found.</p>
        </c:if>

        <!-- Display Results in a Table -->
        <c:if test="${not empty expenses}">
            <table class="expense-results-table">
                <thead>
                    <tr>
                        <th>Category</th>
                        <th>Amount</th>
                        <th>Comments</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="expense" items="${expenses}">
                        <tr>
                            <td>${expense.category}</td>
                            <td>${expense.amount}</td>
                            <td>${expense.comments}</td>
                            <td>${expense.createdAt}</td>
                            <td>${expense.updatedAt}</td>
                            <td>
                                <a href="expenses_read.jsp?expense_id=${expense.id}" class="btn">View</a>
                                <a href="expenses_update.jsp?expense_id=${expense.id}" class="btn">Edit</a>
                                <a href="expenses_delete.jsp?expense_id=${expense.id}" class="btn" onclick="return confirm('Are you sure you want to delete this expense?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>
