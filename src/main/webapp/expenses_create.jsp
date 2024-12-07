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

        <!-- Add Expense Form -->
        <div id="addExpenseForm" class="form-container">
            <h3>Add or Edit an Expense</h3>
            <form id="expenseForm" method="POST" action="expenses_create">
                <label for="category">Category:</label>
                <input type="text" id="category" name="category" placeholder="Enter category" required>
                
                <label for="amount">Amount:</label>
                <input type="number" id="amount" name="amount" placeholder="Enter amount" required>
                
                <label for="comments">Comments:</label>
                <textarea id="comments" name="comments" placeholder="Optional comments"></textarea>
                
                <label for="date">Date:</label>
                <input type="date" id="date" name="createdAt" required>
                
                <button type="submit" id="saveExpenseBtn" class="btn">Save Expense</button>
                <button type="button" id="cancelBtn" class="btn">Cancel</button>
            </form>
        </div>

        <!-- Expense Table -->
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
                    <!-- Dynamic content from backend -->
                    <c:forEach var="expense" items="${expenses}">
                        <tr>
                            <td><c:out value="${expense.category}" /></td>
                            <td><c:out value="${expense.amount}" /></td>
                            <td><c:out value="${expense.createdAt}" /></td>
                            <td><c:out value="${expense.updatedAt}" /></td>
                            <td><c:out value="${expense.comments}" /></td>
                            <td>
                                <form action="expenses_update" method="post" style="display: inline;">
                                    <input type="hidden" name="expenseId" value="<c:out value='${expense.id}' />">
                                    <button type="submit" class="btn">Edit</button>
                                </form>
                                <form action="expenses_delete" method="post" style="display: inline;">
                                    <input type="hidden" name="expenseId" value="<c:out value='${expense.id}' />">
                                    <button type="submit" class="btn">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script>
        // Show/hide the Add Expense Form
        const addExpenseBtn = document.getElementById("addExpenseBtn");
        const viewExpensesBtn = document.getElementById("viewExpensesBtn");
        const addExpenseForm = document.getElementById("addExpenseForm");
        const expenseTableContainer = document.getElementById("expenseTableContainer");

        addExpenseBtn.addEventListener("click", () => {
            addExpenseForm.style.display = "block";
            expenseTableContainer.style.display = "none";
        });

        viewExpensesBtn.addEventListener("click", () => {
            addExpenseForm.style.display = "none";
            expenseTableContainer.style.display = "block";
        });

        document.getElementById("cancelBtn").addEventListener("click", () => {
            addExpenseForm.style.display = "none";
        });
    </script>

    <script src="ExpenceAplication.js"></script>
</body>
</html>
