/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ImNotAngel
 */
public class DbConnection {
    private String hostname = "localhost";
    private String portNumber = "3306";
    private String databaseName = "test";
    private String url = "jdbc:mysql://"+hostname+":"+portNumber+"/"+databaseName;
    private String username = "root";
    private String password = "root";
    
    private Connection mysqlConnection;
    
    public DbConnection ()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            mysqlConnection = DriverManager.getConnection(url, username, password);
        } catch(Exception ex)
        {
            System.out.println("Error "+ ex);
        }
    }
    
    public Connection getmysqlConnection()
    {
        return mysqlConnection;
    }
}
