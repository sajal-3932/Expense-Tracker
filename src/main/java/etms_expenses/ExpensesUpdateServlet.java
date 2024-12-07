package etms_expenses;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expensetracker.dao.ExpensesDao;
import com.expensetracker.entities.Expenses;
import com.expensetracker.helper.ConnectionProvider;

@WebServlet("/expenses_update")
public class ExpensesUpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request
        String expenseIdStr = request.getParameter("expenseId");
        String category = request.getParameter("category");
        String amountStr = request.getParameter("amount");
        String comments = request.getParameter("comments");
        String updatedAtStr = request.getParameter("updatedAt");

        // Convert parameters to appropriate types
        int expenseId = Integer.parseInt(expenseIdStr);
        double amount = Double.parseDouble(amountStr);

        // Parse the updatedAt string to java.util.Date
        java.util.Date updatedAt = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            updatedAt = sdf.parse(updatedAtStr);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlUpdatedAt = new java.sql.Date(updatedAt.getTime());

        // Create an Expenses object with the updated data
        Expenses expense = new Expenses();
        expense.setCategory(category);
        expense.setAmount(amount);
        expense.setComments(comments);
        expense.setUpdatedAt(sqlUpdatedAt);

        // Initialize the ExpensesDao to update the expense
        ExpensesDao expensesDao = new ExpensesDao(ConnectionProvider.getConnection());

        boolean success = false;
        try {
            success = expensesDao.updateExpense(expenseId, expense);  // Update the expense in the database
            if (success) {
                // Redirect to a success page
                response.sendRedirect("expenses_update_success.jsp");
            } else {
                // Redirect to a failure page
                response.sendRedirect("expenses_update_failure.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle SQL exception
            // Redirect to a failure page
            response.sendRedirect("expenses_update_failure.jsp");
        }
    }
}
