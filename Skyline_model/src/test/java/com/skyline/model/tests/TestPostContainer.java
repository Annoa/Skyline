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
    
    private IBlog blog;
    private IBlog post;
    
    @Before
    public void before() {
        blog = BlogFactory.getBlog(true);
        post = BlogFactory.getBlog(true);
        
    }
    
    @Test
    public void getAllPost() {
        int count = blog.getPostContainer().getCount();
        System.out.println("count = " + count);
        List<Post> allPost = blog.getPostContainer().getRange(count);
        assertTrue(allPost.size() == 2);
        
    }
    @Test 
    public void getAllPostByTomas() {
        Member m = post.getMemberContainer().getMember("Tomas");
        List<Post> postByTomas = blog.getPostContainer().getPostByMember(m);
        System.out.println("count = " + postByTomas.size());
        assertTrue(postByTomas.size() == 1);
    }
    
}
