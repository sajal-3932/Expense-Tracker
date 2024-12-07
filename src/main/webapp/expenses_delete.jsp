<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Expense</title>
</head>
<body>
    <h2>Are you sure you want to delete this expense?</h2>
    <form action="expenses_delete" method="post">
        <input type="hidden" name="expense_id" value="${param.expense_id}">
        <input type="submit" value="Delete Expense">
    </form>
    <a href="expenses_list.jsp">Cancel</a>
</body>
</html>
