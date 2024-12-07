package etms_expenses;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expensetracker.dao.ExpensesDao;
import com.expensetracker.entities.Expenses;
import com.expensetracker.helper.ConnectionProvider;

@WebServlet("/expenses_insert")
public class ExpensesInsertServlet extends HttpServlet {
    
    // Handle the POST request to insert an expense
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request
        String category = request.getParameter("category");
        String amountStr = request.getParameter("amount");
        String comments = request.getParameter("comments");
        
        // Validate the parameters
        if (category == null || category.trim().isEmpty() || amountStr == null || amountStr.trim().isEmpty()) {
            response.sendRedirect("error.jsp");  // Redirect to an error page if validation fails
            return;
        }

        // Convert the amount to a double
        double amount = 0;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp");  // If the amount is not a valid number, redirect to an error page
            return;
        }

        // Create an Expenses object and set its properties
        Expenses expense = new Expenses();
        expense.setCategory(category);
        expense.setAmount(amount);
        expense.setComments(comments);

        // Get the current date for createdAt and updatedAt fields
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        expense.setCreatedAt(currentDate);
        expense.setUpdatedAt(currentDate);

        // Call the method to insert the expense into the database
        boolean success = insertExpense(expense);

        if (success) {
            // Redirect to a success page if the insertion is successful
            response.sendRedirect("expense_insert_success.jsp");
        } else {
            // Redirect to an error page if the insertion fails
            response.sendRedirect("expense_insert_failure.jsp");
        }
    }

    // Method to insert an expense into the database
    private boolean insertExpense(Expenses expense) {
        boolean flag = false;
        try {
            // Create an instance of ExpensesDao and insert the expense
            ExpensesDao expensesDao = new ExpensesDao(ConnectionProvider.getConnection());
            flag = expensesDao.saveUser(expense);  // Assuming saveUser() is used to insert the expense

        } catch (Exception e) {
            e.printStackTrace();  // Print stack trace if there is any exception
        }
        return flag;
    }
}
