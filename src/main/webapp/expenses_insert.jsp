<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Expense</title>
</head>
<body>
    <h2>Insert New Expense</h2>
    <form action="expenses_insert" method="post">
        <label for="category">Category:</label>
        <input type="text" id="category" name="category" required><br><br>

        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" required><br><br>

        <label for="comments">Comments:</label>
        <textarea id="comments" name="comments"></textarea><br><br>

        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required><br><br>

        <input type="submit" value="Insert Expense">
    </form>
</body>
</html>
