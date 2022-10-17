/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ImNotAngel
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String birthdate;
    private String registerDate;
    
    //Obtain user
    public User(int userId, String username, String password, String firstName, String lastName, String email, String birthdate, String registerDate)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
        this.registerDate = registerDate;
    }
    //Register user
    public User(String username, String password, String firstName, String lastName, String email, String birthdate)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
    }
    //Check credentials
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getRegisterDate() {
        return registerDate;
    }
    
    
}
