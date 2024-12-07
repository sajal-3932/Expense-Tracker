package etms_expenses;

import com.expensetracker.dao.ExpensesDao;
import com.expensetracker.entities.Expenses;
import com.expensetracker.helper.ConnectionProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ExpensesReadServlet")
public class ExpensesReadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Get the expense ID parameter from the request
	    String expenseIdStr = request.getParameter("expenseId");

	    // If the expense ID is null or empty, redirect to an error page
	    if (expenseIdStr == null || expenseIdStr.isEmpty()) {
	        response.sendRedirect("error.jsp?error=InvalidExpenseId");
	        return;
	    }

	    // Initialize required variables
	    ExpensesDao expensesDao = new ExpensesDao(ConnectionProvider.getConnection());
	    Expenses expense = null;

	    try {
	        // Parse the expense ID and fetch expense details
	        int expenseId = Integer.parseInt(expenseIdStr);
	        expense = expensesDao.getExpenseById(expenseId);

	        // If no record is found, redirect to an error page
	        if (expense == null) {
	            response.sendRedirect("error.jsp?error=ExpenseNotFound");
	            return;
	        }
	    } catch (NumberFormatException e) {
	        // Handle invalid number format for expense ID
	        e.printStackTrace();
	        response.sendRedirect("error.jsp?error=InvalidExpenseIdFormat");
	        return;
	    } catch (Exception e) {
	        // Handle other exceptions, e.g., database connection issues
	        e.printStackTrace();
	        response.sendRedirect("error.jsp?error=DatabaseError");
	        return;
	    }

	    // Set the expense object as an attribute in the request scope
	    request.setAttribute("expense", expense);

	    // Forward the request to the JSP for rendering the details
	    request.getRequestDispatcher("/expenses_read.jsp").forward(request, response);
	}

}
