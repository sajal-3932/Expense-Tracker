<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>See All Expenses</title>
    <link rel="stylesheet" href="ExpenceAplication.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar">
        <div class="logo">
            <h1>Expense Tracker</h1>
        </div>
        <div class="nav-right">
            <a href="dashboard.jsp" class="btn">Back to Dashboard</a>
            <a href="index.jsp" id="logoutBtn" class="btn">Logout</a>
        </div>
    </nav>

    <!-- Page Content -->
    <div class="container">
        <h2>See All Expenses</h2>
        <table border="1" class="expenses-table">
            <thead>
                <tr>
                    <th>Category</th>
                    <th>Amount</th>
                    <th>Comments</th>
                    <th>Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Display each expense -->
                <c:forEach var="expense" items="${expensesList}">
                    <tr>
                        <td>${expense.category}</td>
                        <td>${expense.amount}</td>
                        <td>${expense.comments}</td>
                        <td>${expense.date}</td>
                        <td>
                            <a href="expenses_read.jsp?expense_id=${expense.id}" class="btn">View</a>
                            <a href="expenses_update.jsp?expense_id=${expense.id}" class="btn">Edit</a>
                            <a href="expenses_delete.jsp?expense_id=${expense.id}" class="btn" onclick="return confirm('Are you sure you want to delete this expense?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!-- Handle empty expenses list -->
        <c:if test="${empty expensesList}">
            <p>No expenses found. Please add some expenses to see them here.</p>
        </c:if>
    </div>

    <script src="ExpenceAplication.js"></script>
</body>
</html>
