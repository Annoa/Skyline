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
@WebFilter(filterName = "AuthentificationFilter", urlPatterns = {"/rs/posts/*", "/rs/comments/*"})
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
        if(req.getMethod().equalsIgnoreCase("POST")){
            String s = req.getRequestURI();
            Logger.getAnonymousLogger().log(Level.INFO, "s= " + s);
            if ("/skyline_rest/rs/members".equals(s)) {
                Logger.getAnonymousLogger().log(Level.INFO, "break");
                chain.doFilter(request, response);
                
                Logger.getAnonymousLogger().log(Level.INFO, "this should not be shown");
            }
            Logger.getAnonymousLogger().log(Level.INFO, "url is: 3: " + req.getRequestURL());
            Member member = (Member) req.getSession().getAttribute("USER");
            if (member != null) {
                Logger.getAnonymousLogger().log(Level.INFO, "Filter: member != null");
                req.getSession().setAttribute("USER", member);
                //chain.doFilter(request, response);
            }
            if (member == null) {
                Logger.getAnonymousLogger().log(Level.INFO, "Filter: member == null");
                //res.sendRedirect("/skyline_rest/index.xhtml");
                //req.getRequestURI().
                //req.getRequestDispatcher("/skyline_rest/index.xhtml");
                res.sendRedirect("http://localhost:8080/skyline_rest/");
                return;
            }
        }
        chain.doFilter(request, response);
    }  

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }
}