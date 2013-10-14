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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 * A container for accessing posts of different kinds
 *
 * @author Anno
 */
public class PostContainer extends AbstractDAO<Post, Long> implements IPostContainer {
    
    
    
    public PostContainer(String puName) {
        super(Post.class, puName);
    }

    public List<Post> getRangeByVotes(int start, int amount) {
        EntityManager em = super.getEntityManager();
        TypedQuery<Object[]> query = em.createQuery
                ("select a, a.votes.upVote - a.votes.downVote AS b "
                + "from Post a order by b DESC", Object[].class);
        List<Object[]> result = query.getResultList();
        List<Post> postList = new ArrayList<Post>();
        for (Object[] obj : result) {
            postList.add((Post) obj[0]);
        }
        return postList.subList(start, amount > postList.size() 
                ? postList.size() : amount);
    }

    public List<Post> getRangeByTime(int start, int amount) {
        EntityManager em = super.getEntityManager();
        TypedQuery<Post> query = em.createQuery
                ("select p from Post p order by p.createDate DESC", Post.class);
        List<Post> result = query.getResultList();
        return result.subList(start, amount > result.size() 
                ? result.size() : amount);
    }

    public Member getAuthor(Post post) {
        EntityManager em = super.getEntityManager();
        TypedQuery<Member> query = em.createQuery
                ("select m from Member m where :post MEMBER OF m.posts", Member.class);
        query.setParameter("post", post);
        
        return query.getSingleResult();
    }

    public List<Post> getPostsOfMemberByVotes(Member member) {
        return null;
    }
}
