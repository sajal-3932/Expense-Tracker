package etms_users;

import com.expensetracker.dao.UsersDao;
import com.expensetracker.entities.Users;
import etms_users.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // Helper method to get the database connection
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensetracker", "root", "root");
        return connection;
    }

    // Handle POST request for login
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the login form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Instantiate UsersDao to check for user in the database
        UsersDao usersDao;
        Users user = null;
        try {
            usersDao = new UsersDao(getConnection());
            // Get the user from the database using email
            user = usersDao.getUserByEmail(email);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Handle login logic
        try {
            if (user != null) {
                // Hash the entered password using SHA-256
                String hashedPassword = PasswordUtils.hashPassword(password); // Hash entered password
                
                // Compare the hashed passwords
                if (hashedPassword.equals(user.getPassword())) {
                    // If passwords match, login successful
                    // Store the user in the session
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    // Redirect to the dashboard
                    response.sendRedirect("dashboard.jsp"); // Redirect to the dashboard or wherever you want
                } else {
                    // If passwords don't match, show an error
                    request.setAttribute("errorMessage", "Invalid email or password.");
                    request.getRequestDispatcher("login.jsp").forward(request, response); // Forward back to login page
                }
            } else {
                // If user doesn't exist, show an error
                request.setAttribute("errorMessage", "User does not exist.");
                request.getRequestDispatcher("login.jsp").forward(request, response); // Forward back to login page
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle exceptions (e.g., database issues, algorithm errors)
            request.setAttribute("errorMessage", "An error occurred during login. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response); // Forward back to login page
        }
    }
}
