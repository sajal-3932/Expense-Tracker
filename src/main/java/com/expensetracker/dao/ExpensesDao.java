package com.expensetracker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.expensetracker.entities.Expenses;
import com.expensetracker.entities.Users;

public class ExpensesDao {

	private Connection con ;
	
	public ExpensesDao() { }
	
	public ExpensesDao(Connection con) {
		this.con = con ;
	}
	
	public Connection getConnection() {
		try {
			if(con == null) {
				
				//driver class load
				Class.forName("com.mysql.jdbc.Driver") ;
				
				//Create a connection
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensetracker", "root", "root") ;
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return con ;
	}
	
	public boolean saveUser(Expenses expenses)
	{
		boolean flag = false ;
		try {			
			//User --> database					
			String query = "INSERT INTO expenses(category,amount,comments,createdAt,updatedAt) VALUES(?, ?, ?, ?, ?)" ;
			PreparedStatement pstmt = this.con.prepareStatement(query) ;
			pstmt.setString(1, expenses.getCategory());
			pstmt.setDouble(2, expenses.getAmount());
			pstmt.setString(3, expenses.getComments());
			pstmt.setDate(4, expenses.getCreatedAt());
			pstmt.setDate(5, expenses.getUpdatedAt());
			
			pstmt.executeUpdate() ;
			flag = true ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag ;
	}
	
	// Method to delete an expense by its ID
    public boolean deleteExpenseById(int expenseId) throws SQLException {
        boolean flag = false;
        try {
            // SQL query to delete the expense record by ID
            String query = "DELETE FROM expenses WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, expenseId); // Set the expense ID in the query

            // Execute the delete operation
            int rowsAffected = pstmt.executeUpdate();
            
            // If at least one row was deleted, return true
            if (rowsAffected > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            // Print the stack trace in case of an exception
            e.printStackTrace();
            throw e; // Re-throw the exception to be handled by the servlet
        }
        return flag; // Return true if the deletion was successful, false otherwise
    }
    
 // Method to get all expenses from the database
    public List<Expenses> getAllExpenses() {
        List<Expenses> expensesList = new ArrayList<>();  // List to hold the expense objects
        try {
            // SQL query to fetch all expenses, ordered by createdAt in descending order (latest first)
            String query = "SELECT * FROM expenses ORDER BY createdAt DESC";
            
            // Prepare the statement
            PreparedStatement pstmt = con.prepareStatement(query);
            
            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Loop through the result set and create Expenses objects for each row
            while (rs.next()) {
                Expenses expense = new Expenses();
                expense.setCategory(rs.getString("category"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setComments(rs.getString("comments"));
                expense.setCreatedAt(rs.getDate("createdAt"));
                expense.setUpdatedAt(rs.getDate("updatedAt"));
                
                // Add the Expenses object to the list
                expensesList.add(expense);
            }

        } catch (Exception e) {
            e.printStackTrace();  // Handle exception and print stack trace
        }
        return expensesList;  // Return the list of expenses
    }
    
 // Method to get an expense by its ID
    public Expenses getExpenseById(int expenseId) {
        Expenses expense = null;
        String query = "SELECT * FROM expenses WHERE id = ?"; // Assuming 'id' is the primary key in the 'expenses' table
        
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            // Set the expense ID in the query
            pstmt.setInt(1, expenseId);
            
            // Execute the query and retrieve the result
            try (ResultSet rs = pstmt.executeQuery()) {
                // If the result set has a row, we populate the expense object
                if (rs.next()) {
                    expense = new Expenses();
                    expense.setCategory(rs.getString("category"));
                    expense.setAmount(rs.getDouble("amount"));
                    expense.setComments(rs.getString("comments"));
                    expense.setCreatedAt(rs.getDate("createdAt"));
                    expense.setUpdatedAt(rs.getDate("updatedAt"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return expense;
    }
    
 // Method to update an expense in the database
    public boolean updateExpense(int expenseId, Expenses expense) throws SQLException {
        String query = "UPDATE expenses SET category = ?, amount = ?, comments = ?, updatedAt = ? WHERE id = ?";
        
        // Prepare the SQL statement
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            // Set parameters for the PreparedStatement
            pstmt.setString(1, expense.getCategory());   // Set the category
            pstmt.setDouble(2, expense.getAmount());      // Set the amount
            pstmt.setString(3, expense.getComments());    // Set the comments
            pstmt.setDate(4, expense.getUpdatedAt());     // Set the updatedAt date
            pstmt.setInt(5, expenseId);                   // Set the expenseId to identify the record to update

            // Execute the update query and check if any rows were affected
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // Return true if at least one row was updated, else false
        }
    }
    
}
