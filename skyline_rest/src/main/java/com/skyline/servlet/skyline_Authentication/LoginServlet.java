/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.servlet.skyline_Authentication;

import com.skyline.model.core.Member;
import com.skyline.rest.skyline_rest.Blog;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tomassellden
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/authorization/*"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Logger.getAnonymousLogger().log(Level.INFO, "LoginServlet");
         
         String username = request.getParameter("username");
         String password = request.getParameter("password");
         
         
         Member member;
         try {
             member = Blog.INSTANCE.getMembersRegistry().getMember(username);
         } catch (NoResultException ex) {
             member = null;
         }
         
        // Logger.getAnonymousLogger().log(Level.INFO, "member: " + member.toString());
         
         
         if (member != null) {
             Logger.getAnonymousLogger().log(Level.INFO, "member: is not null");
             request.getSession().setAttribute("USER", member);
             //response.sendRedirect("");
             
             request.getRequestDispatcher("index.xhtml").forward(request, response);
         } else {
             response.sendRedirect("authorization.html");
         }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
