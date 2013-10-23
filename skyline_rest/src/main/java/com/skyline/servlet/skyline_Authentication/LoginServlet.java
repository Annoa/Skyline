/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.servlet.skyline_Authentication;

import com.skyline.model.core.IMemberRegistry;
import com.skyline.model.core.Member;
import com.skyline.rest.skyline_rest.BlogAccess;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tomassellden
 * A servlet that check if username and password exist in database and if it does
 * set a session attribute "USER" so he will be enable to write comments and posts
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
         
         String username = request.getParameter("username");
         String password = request.getParameter("password");
         
         String logout = request.getParameter("logout");
         if (logout != null) {
             request.getSession().removeAttribute("USER");
             response.sendRedirect("index.xhtml");
             return;
         }
         
        IMemberRegistry memberBox = BlogAccess.INSTANCE.getMembersRegistry();
        if (memberBox.validMember(username, password)) {
            Member member = memberBox.getMember(username);
            request.getSession().setAttribute("USER", member);
             //request.getRequestDispatcher("login/home.xhtml").forward(request, response);
            response.sendRedirect("login/home.xhtml");
         } else {
             response.sendRedirect("index.xhtml");
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
