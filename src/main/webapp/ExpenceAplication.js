// DOM Elements
const addExpenseBtn = document.getElementById("addExpenseBtn");
const viewExpensesBtn = document.getElementById("viewExpensesBtn");
const addExpenseForm = document.getElementById("addExpenseForm");
const cancelBtn = document.getElementById("cancelBtn");
const expenseForm = document.getElementById("expenseForm");
const expenseTableBody = document.getElementById("expenseTable").getElementsByTagName("tbody")[0];

// Data Array to store expenses
let expenses = [];
let editIndex = -1; // Keeps track of the expense being edited

// Show Add Expense Form
addExpenseBtn.addEventListener("click", () => {
    editIndex = -1; // Reset edit index when adding new expense
    addExpenseForm.style.display = "block";
    document.getElementById("expenseTableContainer").style.display = "none";
});

// Show Expenses Table
viewExpensesBtn.addEventListener("click", () => {
    updateExpenseTable();
    addExpenseForm.style.display = "none";
    document.getElementById("expenseTableContainer").style.display = "block";
});

// Cancel Add Expense Form
cancelBtn.addEventListener("click", () => {
    addExpenseForm.style.display = "none";
    expenseForm.reset();
    editIndex = -1; // Reset on cancel
});

// Add or Edit Expense Form Submit
expenseForm.addEventListener("submit", (e) => {
    e.preventDefault();

    const category = document.getElementById("category").value;
    const amount = parseFloat(document.getElementById("amount").value);
    const comments = document.getElementById("comments").value;

    const currentDate = new Date();
    const formattedDate = `${currentDate.toLocaleDateString()} ${currentDate.toLocaleTimeString()}`;

    // If editing an expense
    if (editIndex !== -1) {
        expenses[editIndex] = { category, amount, comments, createdAt: expenses[editIndex].createdAt, updatedAt: formattedDate };
    } else {
        // Add new expense
        const newExpense = { category, amount, comments, createdAt: formattedDate, updatedAt: formattedDate };
        expenses.push(newExpense);
    }

    updateExpenseTable();
    addExpenseForm.style.display = "none";
    expenseForm.reset();
    editIndex = -1; // Reset after save
});

// Update Expense Table
function updateExpenseTable() {
    expenseTableBody.innerHTML = ""; // Clear existing table rows

    expenses.forEach((expense, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${expense.category}</td>
            <td>${expense.amount}</td>
            <td>${expense.createdAt}</td>
            <td>${expense.updatedAt}</td>
            <td>${expense.comments || "N/A"}</td>
            <td>
                <button class="btn edit-btn" onclick="editExpense(${index})">Edit</button>
                <button class="btn delete-btn" onclick="deleteExpense(${index})">Delete</button>
            </td>
        `;

        expenseTableBody.appendChild(row);
    });
}

// Edit Expense
function editExpense(index) {
    const expense = expenses[index];
    document.getElementById("category").value = expense.category;
    document.getElementById("amount").value = expense.amount;
    document.getElementById("comments").value = expense.comments;

    editIndex = index;
    addExpenseForm.style.display = "block";
    document.getElementById("expenseTableContainer").style.display = "none";
}

// Delete Expense
function deleteExpense(index) {
    expenses.splice(index, 1);
    updateExpenseTable();
}

// Logout Button Logic
document.getElementById("logoutBtn").addEventListener("click", () => {
    alert("You have logged out.");
    // Implement actual logout logic, like redirecting or clearing session data
	
});
