/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.servlet.skyline_Authentication;

import com.skyline.model.core.Member;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tomassellden
 */
@WebFilter(filterName = "AuthentificationFilter", urlPatterns = {"/login/*"})
public class AuthentificationFilter implements Filter {

    
    
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthentificationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        Logger.getAnonymousLogger().log(Level.INFO, "doFilter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Member member = (Member) req.getSession().getAttribute("USER");
        if (member != null) {
            Logger.getAnonymousLogger().log(Level.INFO, "Filter: member != null");
            req.getSession().setAttribute("USER", member); // Login
            
            //chain.doFilter(request, response);//is this okay!!!!!
            //res.sendRedirect("index.xhtml");
            req.getRequestDispatcher("AddPost.html").forward(request, response);
        }
        if (member == null) {
            Logger.getAnonymousLogger().log(Level.INFO, "Filter: member == null");
            //res.sendRedirect("authorization.html");
            //chain.doFilter(request, response);
            req.getRequestDispatcher("../authorization.html").forward(request, response);
        }
            
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }
}