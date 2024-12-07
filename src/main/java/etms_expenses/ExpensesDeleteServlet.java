package etms_expenses;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expensetracker.dao.ExpensesDao;
import com.expensetracker.helper.ConnectionProvider;

@WebServlet("/expense_delete")
public class ExpensesDeleteServlet extends HttpServlet {

	// Handling the POST request to delete an expense by its ID
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ID of the expense to be deleted from the request
        int expenseId = Integer.parseInt(request.getParameter("expenseId"));
        
        // Initialize the DAO
        ExpensesDao expensesDAO = new ExpensesDao(ConnectionProvider.getConnection());
        
        try {
            // Attempt to delete the expense from the database
            boolean success = expensesDAO.deleteExpenseById(expenseId);
            
            if (success) {
                // If deletion was successful, redirect to a success page
                response.sendRedirect("expense_delete_success.jsp");
            } else {
                // If deletion failed, redirect to a failure page
                response.sendRedirect("expense_delete_failure.jsp");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
            // Redirect to a failure page
            response.sendRedirect("expense_delete_failure.jsp");
        }
    }
	
}
