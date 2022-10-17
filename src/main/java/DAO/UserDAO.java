/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Configuration.DbConnection;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author ImNotAngel
 */
public class UserDAO {
    DbConnection dbConnection = new DbConnection();
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;
    User user = null;
    
    public User identify(User credentials)
    {
        String sql = "SELECT * FROM gatito_blog.users WHERE username =? AND password =?";
        try
        {
            connection = dbConnection.getmysqlConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, credentials.getUsername());
            ps.setString(2, credentials.getPassword());
            rs = ps.executeQuery();
            if(rs.next() == true)
            {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("birthdate"),
                        rs.getString("registerDate")
                );
            }
        }
        catch(Exception ex)
        {
            System.out.println("error " + ex);
        }
        return this.user;
    }
    
    public boolean register(User newUser)
    {
        String sql = "INSERT INTO gatito_blog.users (username, password, firstName, lastName, email, birthdate) VALUES (?,?,?,?,?,?)";
        
        //INSERT INTO gatito_blog.users(username, password, firstName, lastName, email, birthdate) 
        //VALUES ('ImNot', 'HCLAFax8', 'Angel', 'Barbosa', 'barbosamane@hotmail.com', '2001-12-20');
        try
        {
            connection = dbConnection.getmysqlConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, newUser.getFirstName());
            ps.setString(4, newUser.getLastName());
            ps.setString(5, newUser.getEmail());
            ps.setString(6, newUser.getBirthdate());
            int result = ps.executeUpdate();
            if(result > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error " + ex);
            return false;
        }
    }
}
