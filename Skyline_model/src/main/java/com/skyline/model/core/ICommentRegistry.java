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
public interface ICommentRegistry {
   
    public List<Comment> getAllCommentsOnPost(Post post);
    
   // public List<Post> getChildComments(Comment parent);
}
