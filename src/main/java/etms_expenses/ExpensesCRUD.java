package etms_expenses;

import com.expensetracker.entities.Expenses;
import com.expensetracker.helper.ConnectionProvider;
import com.expensetracker.dao.ExpensesDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpensesCRUD {

	private Connection con;
    private ExpensesDao expensesDao;

    // Constructor to initialize the connection and ExpensesDao object
    public ExpensesCRUD() {
        con = ConnectionProvider.getConnection();
        expensesDao = new ExpensesDao(con);
    }

    // 1. Add a new expense
    public boolean addExpense(String category, double amount, String comments) {
        boolean success = false;
        try {
            Date currentDate = new Date(System.currentTimeMillis());
            Expenses expense = new Expenses(category, amount, comments, currentDate, currentDate);
            success = expensesDao.saveUser(expense);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    // 2. View expenses
    public List<Expenses> viewExpenses() {
        List<Expenses> expenseList = new ArrayList<>();
        try {
            String query = "SELECT * FROM expenses ORDER BY createdAt DESC";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String category = rs.getString("category");
                double amount = rs.getDouble("amount");
                String comments = rs.getString("comments");
                Date createdAt = rs.getDate("createdAt");
                Date updatedAt = rs.getDate("updatedAt");

                Expenses expense = new Expenses(category, amount, comments, createdAt, updatedAt);
                expenseList.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenseList;
    }

    // 3. Edit an existing expense
    public boolean editExpense(int expenseId, String category, double amount, String comments) {
        boolean success = false;
        try {
            String query = "UPDATE expenses SET category = ?, amount = ?, comments = ?, updatedAt = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, category);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, comments);
            pstmt.setDate(4, new Date(System.currentTimeMillis()));  // Set the current date for the updatedAt field
            pstmt.setInt(5, expenseId);

            int rowsUpdated = pstmt.executeUpdate();
            success = rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // 4. Delete an expense
    public boolean deleteExpense(int expenseId) {
        boolean success = false;
        try {
            String query = "DELETE FROM expenses WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, expenseId);

            int rowsDeleted = pstmt.executeUpdate();
            success = rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // Utility method to get expense by ID (for editing or viewing specific record)
    public Expenses getExpenseById(int expenseId) {
        Expenses expense = null;
        try {
            String query = "SELECT * FROM expenses WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, expenseId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String category = rs.getString("category");
                double amount = rs.getDouble("amount");
                String comments = rs.getString("comments");
                Date createdAt = rs.getDate("createdAt");
                Date updatedAt = rs.getDate("updatedAt");

                expense = new Expenses(category, amount, comments, createdAt, updatedAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expense;
    }
	
}
