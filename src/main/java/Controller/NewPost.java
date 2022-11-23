/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.PostDAO;
import Model.Post;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author ImNotAngel
 */
@WebServlet(name = "NewPost", urlPatterns = {"/NewPost"})
@MultipartConfig(maxFileSize = 16177216)
public class NewPost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewPost</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewPost at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        HttpSession session = request.getSession();
        HashMap result = new HashMap();
        try
        {
//            String text = request.getParameter("Text1");
//            String text2 = request.getParameter("test");
//            String spoiler = request.getParameter("spoiler");
//            System.out.println("checkbox: " + spoiler);
//            System.out.println("Post text: " + text);
//            System.out.println("Post text2: " + text2);
            
            
            
            String text = request.getParameter("Text1");
            Part filePart = request.getPart("postImage");
            //String spoiler = request.getParameter("spoiler");
            Boolean spoiler = false;
            if(request.getParameter("spoiler") != null)
            {
                spoiler = true;
            }
            InputStream photo = null;
            photo = filePart.getInputStream();
            //Pasarle el id del usuario, postText, postImage, spoiler
            if(session.getAttribute("idUser") != null)
            {
                int id = Integer.parseInt(session.getAttribute("idUser").toString());
                Post post = new Post(id, text, photo, spoiler);
                PostDAO postDao = new PostDAO();
                result.put("posted", postDao.insertPost(post));
                //result.put("posted", true);
            }
            else
            {
                String json = new Gson().toJson(result);
                PrintWriter out = response.getWriter();
                out.print(json);
                out.flush();
                return;
            }
            
        }
        catch(Exception ex)
        {
            System.out.println("Error:" + ex);
            result.put("posted", false);
        }
        String json = new Gson().toJson(result);
        
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
