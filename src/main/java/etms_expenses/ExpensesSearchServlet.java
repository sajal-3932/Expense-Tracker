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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/expenses_search")
public class ExpensesSearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchType = request.getParameter("search_type");
        String searchTerm = request.getParameter("search_term");

        // Perform validation if needed
        if (searchTerm == null || searchTerm.isEmpty()) {
            request.setAttribute("errorMessage", "Please enter a search term.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // Call a method to search for expenses based on the selected search type and term
        List<Expenses> expenses;
        try {
            expenses = searchExpenses(searchType, searchTerm);

            // Set expenses attribute to be used in JSP for displaying search results
            request.setAttribute("expenses", expenses);

            // Forward to a JSP page to display search results
            request.getRequestDispatcher("expenses_search_results.jsp").forward(request, response);

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error fetching expenses.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private List<Expenses> searchExpenses(String searchType, String searchTerm) {
        // List to hold the expenses that match the search criteria
        List<Expenses> matchingExpenses = new ArrayList<>();
        Connection conn = null;
        ExpensesDao expensesDao = new ExpensesDao(ConnectionProvider.getConnection());

        try {
            // Establish database connection
            conn = expensesDao.getConnection();

            // Prepare SQL statement based on searchType
            String sql = "";
            if (searchType.equalsIgnoreCase("category")) {
                sql = "SELECT * FROM expenses WHERE category LIKE ?";
                searchTerm = "%" + searchTerm + "%";  // Add wildcards for partial search
            } else if (searchType.equalsIgnoreCase("amount")) {
                sql = "SELECT * FROM expenses WHERE amount = ?";
            } else if (searchType.equalsIgnoreCase("comments")) {
                sql = "SELECT * FROM expenses WHERE comments LIKE ?";
                searchTerm = "%" + searchTerm + "%";  // Add wildcards for partial search
            }

            // Prepare the query
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, searchTerm);  // Bind the search term

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Process ResultSet
            while (rs.next()) {
                // Create Expenses object from ResultSet
                Expenses expense = new Expenses();
                expense.setCategory(rs.getString("category"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setComments(rs.getString("comments"));
                expense.setCreatedAt(rs.getDate("createdAt"));
                expense.setUpdatedAt(rs.getDate("updatedAt"));

                // Add Expenses object to the list
                matchingExpenses.add(expense);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure proper resource cleanup
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return matchingExpenses;
    }
}
