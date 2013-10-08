/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;


import com.skyline.model.utils.IDAO;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ICommentContainer extends IDAO<Comment,Long> {
   
    public List<Comment> getAllCommentsOnPost(Post post);
    
   // public List<Comment> getChildComments(Comment parent);
}
