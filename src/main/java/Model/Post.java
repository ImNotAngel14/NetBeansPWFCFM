/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Base64;

/**
 *
 * @author ImNotAngel
 */
public class Post {
    /*
    
    CREATE TABLE IF NOT EXISTS gatito_blog.posts(
	postId INT AUTO_INCREMENT PRIMARY KEY,
    idUser INT NOT NULL,
    title VARCHAR(45) NOT NULL,
    postText VARCHAR(180),
    postImage LONGBLOB,
    uploadDate DATETIME NOT NULL DEFAULT NOW(),
    isSpoiler BOOLEAN NOT NULL,
    FOREIGN KEY(idUser)
		REFERENCES gatito_blog.users(id)
        ON DELETE CASCADE
    );
    */
    private boolean spoiler;
    private int postId;
    private int userId;
    private String title;
    private String postText;
    private String base64PostImage;
    private InputStream postImage;
    private Timestamp uploadDate;
    //public User author;
    private String username;
    private String base64ProfileImage;
    private InputStream profileImage;
    
    //Get post
    public Post(int postId, int userId, String title, String postText, InputStream postImage, boolean spoiler, Timestamp uploadDate, String username, InputStream userPhoto)
    {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.postText = postText;
        this.postImage = postImage;
        this.spoiler = spoiler;
        this.uploadDate = uploadDate;
        this.username = username;
        this.profileImage = userPhoto;
        //this.base64postImage = base64(postImage);
    }
    
    //Get post REAL
    public Post(int postId, int userId, String title, String postText, String postImage, boolean spoiler, Timestamp uploadDate, String username, String userPhoto)
    {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.postText = postText;
        this.base64PostImage = postImage;
        this.spoiler = spoiler;
        this.uploadDate = uploadDate;
        this.username = username;
        this.base64ProfileImage = userPhoto;
        //this.base64postImage = base64(postImage);
    }
    
    //Add post
    public Post(int userId, String postText, InputStream postImage, boolean spoiler)
    {
        this.userId = userId;
        //this.title = title;
        this.postText = postText;
        this.postImage = postImage;
        this.spoiler = spoiler;
    }

    public boolean isSpoiler() {
        return spoiler;
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getPostText() {
        return postText;
    }

    public InputStream getPostImage() {
        return postImage;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }
    
    
}
