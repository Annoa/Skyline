/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;


import java.util.List;

/**
 *
 * @author User
 */
public interface ICommentContainer {
   
    public List<Comment> getAllCommentsOnPost(Post post);
    
   // public List<Comment> getChildComments(Comment parent);
}