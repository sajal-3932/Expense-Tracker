<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Expenses</title>
</head>
<body>
    <h2>Expenses List</h2>
    <table border="1">
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
            <c:forEach var="expense" items="${expenses}">
                <tr>
                    <td>${expense.category}</td>
                    <td>${expense.amount}</td>
                    <td>${expense.comments}</td>
                    <td>${expense.date}</td>
                    <td>
                        <a href="expenses_read.jsp?expense_id=${expense.id}">View</a>
                        <a href="expenses_delete.jsp?expense_id=${expense.id}">Delete</a>
                        <a href="expenses_update.jsp?expense_id=${expense.id}">Update</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="expenses_create.jsp">Add New Expense</a>
</body>
</html>
