<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Expense Tracker</title>
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

    <!-- Dashboard Content -->
    <div class="container">
        <!-- Welcome Message -->
        <section>
            <h2>Welcome, <c:out value="${sessionScope.user.name}" />!</h2>
            
        </section>

        <!-- Navigation Menu -->
        <div class="menu">
            <button id="addExpenseBtn" class="btn">Add Expense</button>
            <button id="viewExpensesBtn" class="btn">View Expenses</button>
        </div>

        <!-- Add Expense Form (Hidden by default) -->
        <div id="addExpenseForm" class="form-container">
            <h3>Add or Edit an Expense</h3>
            <form id="expenseForm" method="POST" action="add_expense">
                <input type="text" id="category" name="category" placeholder="Category" required>
                <input type="number" id="amount" name="amount" placeholder="Amount" required>
                <textarea id="comments" name="comments" placeholder="Comments (Optional)"></textarea>
                <button type="submit" id="saveExpenseBtn" class="btn">Save Expense</button>
                <button type="button" id="cancelBtn" class="btn">Cancel</button>
            </form>
        </div>

        <!-- Expense Table (Hidden by default) -->
        <div id="expenseTableContainer" class="table-container">
            <h3>Your Expenses</h3>
            <table id="expenseTable">
                <thead>
                    <tr>
                        <th>Category</th>
                        <th>Amount</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Comments</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Expenses will be dynamically listed here -->
                </tbody>
            </table>
        </div>
    </div>

	<script>
        document.getElementById("saveExpenseBtn").addEventListener("click", () => {
            // Display a message when the button is clicked
            alert("Expense Saved successfully. Now you can view your expenses.");
        });
    </script>

    <script src="ExpenceAplication.js"></script>
</body>
</html>
