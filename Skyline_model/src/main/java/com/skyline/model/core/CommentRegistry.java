/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractDAO;
import java.util.List;

/**
 *
 * @author User
 */
public class CommentRegistry extends AbstractDAO<Comment, Long>
    implements ICommentRegistry {

    public List<Comment> getAllCommentsOnPost(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*
    public List<Post> getChildComments(Comment parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  */
}
