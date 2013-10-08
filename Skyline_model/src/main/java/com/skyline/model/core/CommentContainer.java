/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CommentContainer extends AbstractDAO<Comment, Long>
    implements ICommentContainer {

    
    
    public static CommentContainer newInstance(){
        return new CommentContainer();
    }
    
    
    
    
    public List<Comment> getAllCommentsOnPost(Post post) {
        List<Comment> comments = new ArrayList<Comment>();
        for (Comment c : getRange(0, getCount())){
            if(c.getPost().equals(post)){
                comments.add(c);
            }
        }
        return comments;
    }
/*
    public List<Post> getChildComments(Comment parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  */
}
