/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractDAO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * A container for accessing posts of different kinds
 *
 * @author Anno
 */
public class PostContainer extends AbstractDAO<Post, Long> implements IPostContainer {
    
    
    
    public PostContainer(String puName) {
        super(Post.class, puName);
    }

//    @Override
//    public Member getMemberByPost(Post post) {
//        return post.getMember();
//        
//
//    }

//    @Override
//    public List<Post> getAllPostByMember(Post post) {
//        List<Post> getAllPostByMember = new ArrayList<Post>();
//        Member m = post.getMember();
//        for (Post p : getRange(0, getCount())) {
//            if (p.getMember().equals(m)) {
//                getAllPostByMember.add(p);
//            }
//        }
//        return getAllPostByMember;
//    }

    public List<Post> getPostsByVotes(int amount) {
//        EntityManager em = super.getEntityManager();
//        TypedQuery query = em.c
//        
        return null;
    }

    public List<Post> getPostsByTime(int amount) {
        return null;
    }
}
