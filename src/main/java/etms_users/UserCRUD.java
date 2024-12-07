package etms_users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.expensetracker.entities.Users;

public class UserCRUD {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{	
		Class.forName("com.mysql.cj.jdbc.Driver") ;
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensetracker", "root", "root") ;
		
		return connection ;
	}
	
	public boolean createUser(Users users) throws SQLException, ClassNotFoundException {
	    Connection conn = getConnection();
	    String sql = "INSERT INTO users VALUES(?, ?, ?, ?)";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, users.getName());
	    stmt.setString(2, users.getEmail());
	    stmt.setString(3, users.getPassword());
	    stmt.setString(4, users.getConfirmPassword());
	    int res = stmt.executeUpdate();
	    conn.close();
	    
	    if(res != 0)
	    {
	    	return true ;
	    }
	    else {
	    	return false ;
	    }
	}

}
