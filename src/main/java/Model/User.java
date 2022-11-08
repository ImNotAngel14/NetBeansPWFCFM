/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

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
    private InputStream photo;
    private String base64Photo;
    
    //Obtain user
    public User(int userId, String username, String password, String firstName, String lastName, String email, String birthdate, String registerDate, InputStream photo)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
        this.registerDate = registerDate;
        this.photo = photo;
    }
    //Register user
    public User(String username, String password, String firstName, String lastName, String email, String birthdate, InputStream photo)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
        this.photo = photo;
    }
    //Check credentials
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    
    public User(String username, InputStream photo)
    {
        this.username = username;
        this.photo = photo;
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
    
    public InputStream getPhoto()
    {
        return photo;
    }
    
    public static byte[] toByteArray(InputStream in) throws IOException
    {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while((len = in.read(buffer)) != -1){
            os.write(buffer, 0, len);
        }
        return os.toByteArray();
    }
    
    public String base64(InputStream file) throws IOException
    {
        byte[] bytes = toByteArray(file);
        String imageStr = Base64.getEncoder().encodeToString(bytes);
        return imageStr;
    }
}
