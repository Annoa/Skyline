/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.tests;

import com.skyline.model.core.BlogFactory;
import com.skyline.model.core.IBlog;
import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tomassellden
 */
public class TestPostContainer {
    
    
    private IBlog post;
    
    @Before
    public void before() {
        post = BlogFactory.getBlog(true);
        
    }
    
    @Test
    public void getAllPost() {
        int count = post.getPostContainer().getCount();
        System.out.println("count = " + count);
        List<Post> allPost = post.getPostContainer().getRange(count);
        assertTrue(allPost.size() == 4);
        
    }
    @Test 
    public void getAllPostByTomas() {
        Post TomasPost = null;
        Member m = post.getMemberContainer().getMember("Tomas");
        int count = post.getPostContainer().getCount();
        List<Post> allPost = post.getPostContainer().getRange(count);
        for (Post p : allPost) {
            if (p.getMember().equals(m)) {
                TomasPost = p;
                break;
            }
        }
        List<Post> postFromTomas = post.getPostContainer().getAllPostByMember(TomasPost);
        System.out.println("postFromTomas= " + postFromTomas.size());
        assertTrue(postFromTomas.size() == 2);
    }
    
}
