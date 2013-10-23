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
 * A filter that will be invoked every time we do an url invoke conatining 
 * urlpatters /rs/posts/*, /rs/comments/*" but we only check if session attribute
 * USER is not null if it is a POST invoke.
 */
@WebFilter(filterName = "AuthentificationFilter", urlPatterns = {"/rs/posts/*", "/rs/comments/*"})
public class AuthentificationFilter implements Filter {

    
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

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        if(req.getMethod().equalsIgnoreCase("POST")){
            
            Member member = (Member) req.getSession().getAttribute("USER");
            if (member != null) {
                req.getSession().setAttribute("USER", member);
                
            }
            if (member == null) {
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