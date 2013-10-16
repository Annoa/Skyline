/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Gabriel
 */
public class CommentContainer extends AbstractDAO<Comment, Long>
implements ICommentContainer {
    
    public CommentContainer(String puName) {
        super(Comment.class, puName);
    }
    
    public List<Comment> getAllCommentsOnPost(Post post) {
        EntityManager em = super.getEntityManager();
        TypedQuery<Object[]> query = em.createQuery
                ("select c, c.votes.upVote - c.votes.downVote AS b "
                + "from Post p JOIN p.comments c where "
                + "p.id = :id order by b DESC", Object[].class);
        query.setParameter("id", post.getId());
        
        List<Object[]> result = query.getResultList();
        List<Comment> commentList = new ArrayList<Comment>();
        for (Object[] obj : result) {
            commentList.add((Comment) obj[0]);
        }
        return commentList;
    }
    
    public List<Comment> getChildComments(Comment parent) {
        EntityManager em = super.getEntityManager();
        TypedQuery<Object[]> query = em.createQuery
                ("select c, c.votes.upVote - c.votes.downVote AS b "
                + "from Comment d JOIN d.childComments c where "
                + "d.id = :id order by b DESC", Object[].class);
        query.setParameter("id", parent.getId());
        
        List<Object[]> result = query.getResultList();
        List<Comment> commentList = new ArrayList<Comment>();
        for (Object[] obj : result) {
            commentList.add((Comment) obj[0]);
        }
        
        return commentList;
        
    }
    
    public List<Comment> getRootCommentsForPost(Post post) {
        EntityManager em = super.getEntityManager();
        TypedQuery<Object[]> query = em.createQuery
                ("SELECT c, c.votes.upVote - c.votes.downVote AS b "
                + "FROM Post p JOIN p.comments c WHERE NOT EXISTS "
                + "(SELECT cC FROM Comment p JOIN p.childComments cC "
                + "WHERE c.id = cC.id) AND p.id = :id "
                + "ORDER BY b DESC", Object[].class);
        query.setParameter("id", post.getId());
        
        List<Object[]> result = query.getResultList();
        List<Comment> commentList = new ArrayList<Comment>();
        for (Object[] obj : result) {
            commentList.add((Comment) obj[0]);
        }
        
        return commentList;
    }
}
