package etms_users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expensetracker.dao.UsersDao;
import com.expensetracker.entities.Users;

@WebServlet("/sign_up")
public class SignUpServlet extends HttpServlet {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{	
		Class.forName("com.mysql.cj.jdbc.Driver") ;
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensetracker", "root", "root") ;
		
		return connection ;
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            response.sendRedirect("sign_up_failure.jsp");
            return;
        }

        // Create Users object
        Users users = new Users(name, email, password, confirmPassword);

        boolean success = false ;
        
        // Save user to database
        UsersDao usersDao;
		try {
			usersDao = new UsersDao(getConnection());
			success = usersDao.saveUser(users);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        if (success) {
            response.sendRedirect("sign_up_success.jsp");
        } else {
            response.sendRedirect("sign_up_failure.jsp");
            
        }
    }
}
