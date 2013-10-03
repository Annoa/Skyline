/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.skyline.model;

import com.skyline.model.core.BlogFactory;
import com.skyline.model.core.IBlog;
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
    
    @Before
    public void before() {
        blog = BlogFactory.getBlog(true);
    }
    
    @Test
    public void getAllMembers() {
        int count = blog.getPostContainer().getCount();
        System.out.println("count = " + count);
        List<Post> allPost = blog.getPostContainer().getRange(count);
        assertTrue(allPost.size() == 2);
        
    }
    
}
