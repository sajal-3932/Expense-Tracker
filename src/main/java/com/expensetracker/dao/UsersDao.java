package com.expensetracker.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.expensetracker.entities.Users;

import etms_users.PasswordUtils;

public class UsersDao {

    private Connection con;

    public UsersDao() {}

    public UsersDao(Connection con) {
        this.con = con;
    }
    
 // Method to get user by email
    public Users getUserByEmail(String email) {
        Users user = null;
        try {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Creating a Users object and setting the fields
                user = new Users();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setConfirmPassword(rs.getString("confirmPassword"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean saveUser(Users users) {
        boolean flag = false;
        try {
            // Hash the password using SHA-256 or another algorithm
            String hashedPassword = PasswordUtils.hashPassword(users.getPassword());

            // Insert user into the database
            String query = "INSERT INTO users(name, email, password, confirmPassword) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setString(1, users.getName());
            pstmt.setString(2, users.getEmail());
            pstmt.setString(3, hashedPassword);
            pstmt.setString(4, hashedPassword); // Assuming you want to store the same password in 'confirmPassword'

            pstmt.executeUpdate();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
