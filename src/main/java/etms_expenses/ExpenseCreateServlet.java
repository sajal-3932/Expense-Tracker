package etms_expenses;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expensetracker.entities.Expenses;
import com.expensetracker.dao.ExpensesDao;

@WebServlet("/expenses_create")
public class ExpenseCreateServlet extends HttpServlet {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{	
		Class.forName("com.mysql.cj.jdbc.Driver") ;
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensetracker", "root", "root") ;
		
		return connection ;
	}

	// Handling POST request to create a new expense
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Retrieving input values from the request
        String category = request.getParameter("category");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String comments = request.getParameter("comments");

        // Creating a new Expenses object
        Expenses expense = new Expenses();
        expense.setCategory(category);
        expense.setAmount(amount);
        expense.setComments(comments);

        // Getting the current date for createdAt and updatedAt fields
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        expense.setCreatedAt(currentDate);
        expense.setUpdatedAt(currentDate);

        // Creating an instance of ExpensesDao to interact with the database
        ExpensesDao expensesDao;
        boolean isExpenseCreated = false ;
		try {
			expensesDao = new ExpensesDao(getConnection());
			// Calling the DAO method to save the expense into the database
            isExpenseCreated = expensesDao.saveUser(expense);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
            

            // If the expense is successfully created, redirect to a success page
            if (isExpenseCreated) {
                response.sendRedirect("expense_creation_success.jsp");
            } else {
                // If something went wrong, redirect to a failure page
                response.sendRedirect("expense_creation_failure.jsp");
                System.out.println("Inside try else condition");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // If an error occurs, redirect to a failure page
            response.sendRedirect("expense_creation_failure.jsp");
        }
    }
	
}
