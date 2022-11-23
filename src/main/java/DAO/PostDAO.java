/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Configuration.DbConnection;
import Model.Post;
import Model.User;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author ImNotAngel
 */
public class PostDAO {
    DbConnection dbConnection = new DbConnection();
    private Connection connection;
    private PreparedStatement ps;
    private CallableStatement cs;
    private ResultSet rs;
    Post post = null;
    User user = null;
    
    public List getPostsByUser(int userId)
    {
        ArrayList<Post> list = new ArrayList();
        String store = "{CALL gatito_blog.getUserPosts(" + userId + ")}";
        try{
            
            connection = dbConnection.getmysqlConnection();
            cs = connection.prepareCall(store);
            rs = cs.executeQuery();
            //(int postId, int userId, String title, String postText, InputStream postImage, boolean spoiler, String uploadDate, String username, InputStream userPhoto
            while(rs.next())
            {
                String post = base64(rs.getBinaryStream("postImage"));
                String profileImage = base64(rs.getBinaryStream("profileImage"));
                Post tempPost = new Post(
                        rs.getInt("postId"),
                        rs.getInt("userID"),
                        rs.getString("title"),
                        rs.getString("postText"),
                        post,
                        rs.getBoolean("isSpoiler"),
                        rs.getTimestamp("uploadDate"),
                        rs.getString("username"),
                        profileImage
                );
                list.add(tempPost);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }finally{
            closeConnection();
        }
        return list;
    }
    
    public boolean insertPost(Post newPost)
    {
        
        
        //INSERT INTO gatito_blog.posts(userId, title, postText, postImage, isSpoiler) VALUES(?, ?, ?, ?, ?, ?);
        try
        {
//            boolean hasImage = false;
//            boolean hasText = false;
//            String sql = "INSERT INTO gatito_blog.users(userId, ";
//            String text = newPost.getPostText();
//            InputStream image = newPost.getPostImage();
//            if(text.length() >  0)
//            {
//                hasText = true;
//                sql+="postText, ";
//            }
//            if(image != null)
//            {
//                hasImage = true;
//                sql+="postImage, ";
//            }
//            if(hasText)
//            {
//                
//            }
//            sql += "isSpoiler) VALUES(?,?";
            String sql = "INSERT INTO gatito_blog.posts(userId, postText, postImage, isSpoiler) VALUES(?, ?, ?, ?);";
            connection = dbConnection.getmysqlConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, newPost.getUserId());
            ps.setString(2, newPost.getPostText());
            ps.setBlob(3, newPost.getPostImage());
            ps.setBoolean(4, newPost.isSpoiler());
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
    
    private void closeConnection() {
        try {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }

            if (cs != null && cs.isClosed() == false) {
                cs.close();
            }
            if (ps != null && ps.isClosed() == false) {
                ps.close();
            }

            if (connection != null && connection.isClosed() == false) {
                connection.close();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        dbConnection = null;
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
