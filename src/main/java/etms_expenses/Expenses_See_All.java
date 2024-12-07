package etms_expenses;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expensetracker.entities.Expenses;
import com.expensetracker.dao.ExpensesDao;
import com.expensetracker.helper.ConnectionProvider;

@WebServlet("/Expenses_See_All")
public class Expenses_See_All extends HttpServlet {

    // Handle GET request to fetch and display all expenses
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExpensesDao expensesDao = new ExpensesDao(ConnectionProvider.getConnection()); // Create an instance of ExpensesDao
        try {
            // Fetch all expenses using the getAllExpenses() method (which we will define in ExpensesDao)
            List<Expenses> expensesList = expensesDao.getAllExpenses();
            
            // Set the list of expenses as a request attribute
            req.setAttribute("expensesList", expensesList);
            
            // Forward the request to the JSP page for displaying the expenses
            req.getRequestDispatcher("/Expenses_See_All.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            // In case of an error, forward the error message to an error page
            req.setAttribute("errorMessage", "Error fetching expenses");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    // Handle POST request, which just calls doGet in this case
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
