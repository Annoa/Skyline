/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.tests;

import com.skyline.model.core.BlogFactory;
import com.skyline.model.core.Comment;
import com.skyline.model.core.IBlog;
import com.skyline.model.core.Post;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author User
 */
public class TestCommentContainer {
    
    private IBlog comment;
    
    @Before
    public void before() {
        comment = BlogFactory.getBlog(true);
    }
    
    @Test
    public void getAllComments() {
        
    }
    
    
    
}
