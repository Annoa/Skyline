/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

/**
 * public interface for the Blog
 * @author tomassellden
 */
public interface IBlog {
    /**
     * 
     * @return a member
     */
    public IMembersRegistry getMemberContainer();
    
    public IPostRegistry getPostContainer();

    
}
