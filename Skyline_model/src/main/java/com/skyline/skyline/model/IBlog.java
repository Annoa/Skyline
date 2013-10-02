/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.skyline.model;

/**
 * public interface for the Blog
 * @author tomassellden
 */
public interface IBlog {
    /**
     * 
     * @return a member
     */
    public IMembersContainer getMember();
    
    public IPostContainer getPostContainer();

    
}
