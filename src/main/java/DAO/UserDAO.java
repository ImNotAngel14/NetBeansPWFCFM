/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Configuration.DbConnection;
import Model.User;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.Part;
/**
 *
 * @author ImNotAngel
 */
public class UserDAO {
    DbConnection dbConnection = new DbConnection();
    private Connection connection;
    private PreparedStatement ps;
    private CallableStatement cs;
    private ResultSet rs;
    User user = null;
    
    public User identify(User credentials)
    {
        String sql = "SELECT * FROM gatito_blog.users WHERE username =? AND pass =?";
        try
        {
            connection = dbConnection.getmysqlConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, credentials.getUsername());
            ps.setString(2, credentials.getPassword());
            rs = ps.executeQuery();
            if(rs.next() == true)
            {
                //Part filePart = request.getPart("file-input");
                //InputStream photo = filePart.getInputStream();
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("birthdate"),
                        rs.getString("registerDate"),
                        rs.getBinaryStream("profileImage")
                );
            }
        }
        catch(Exception ex)
        {
            System.out.println("error " + ex);
        }
        finally{
            //cerrarConexiones();
        }
        return this.user;
    }
    
    public User selectUserById(int id)
    {
        String store = "{CALL gatito_blog.selectUser(" + id + ")}";
        try{
            connection = dbConnection.getmysqlConnection();
            cs = connection.prepareCall(store);
            rs = cs.executeQuery();
            if(rs.next())
            {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("birthdate"),
                        rs.getString("registerDate"),
                        rs.getBinaryStream("profileImage")
                );
            }
        } catch(Exception ex)
        {
            System.out.println("error " + ex);
        }
        return user;
    }
    
    public boolean register(User newUser)
    {
        String sql = "INSERT INTO gatito_blog.users (username, pass, firstName, lastName, email, birthdate, profileImage) VALUES (?,?,?,?,?,?,?)";
        
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
            ps.setBlob(7, newUser.getPhoto());
            
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
