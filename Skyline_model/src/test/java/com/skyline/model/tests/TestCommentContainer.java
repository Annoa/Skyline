/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.tests;

import com.skyline.model.core.BlogFactory;
import com.skyline.model.core.Comment;
import com.skyline.model.core.IBlog;
import com.skyline.model.core.ICommentContainer;
import com.skyline.model.core.IMemberRegistry;
import com.skyline.model.core.IPostContainer;
import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import com.skyline.model.core.VotingSystem;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author tomassellden
 */
public class TestCommentContainer {

    static IBlog blog;
    private final static String PU = "skyline_pu";
    private final static String TEST_PU = "test_skyline_pu";

    @BeforeClass
    public static void beforeClass() {
        blog = BlogFactory.getBlog(TEST_PU);
    }

    @Test
    public void testGetAllPostCommentsAndAllChildComments() {
        ICommentContainer cc = blog.getCommentContainer();
        IPostContainer pc = blog.getPostContainer();
        IMemberRegistry mr = blog.getMemberRegistry();
        
        Member mem1 = new Member("Krobbe");
        mr.add(mem1);

        Comment com1 = new Comment("This is the first comment");
        Comment com2 = new Comment("This is the second comment");
        Comment com3 = new Comment("This is the third comment");

        Post post1 = new Post("Post", "Tester", null, null);
        pc.add(post1);
        cc.add(com1);
        cc.add(com2);
        cc.add(com3);

        post1.addComment(com1);
        post1.addComment(com2);
        post1.addComment(com3);
        
        mem1.addPost(post1);
        mem1.addComment(com1);
        mem1.addComment(com2);
        mem1.addComment(com3);
        
        assertTrue(post1.getComments().size() == 3);
        
        mr.update(mem1);

        pc.update(post1);

        assertTrue(cc.getCount() == 3);
        assertTrue(pc.getCount() == 1);
        
        com1.addChildComment(com2);
        com1.addChildComment(com3);
        cc.update(com1);
        

        cc.update(new Comment(com1.getId(), com1.getChildComments(), 
                com1.getCommentText(), com1.getCommentDate(), 
                new VotingSystem(150, 0)));
        cc.update(new Comment(com2.getId(), com2.getChildComments(), 
                com2.getCommentText(), com2.getCommentDate(), 
                new VotingSystem(20, 20)));
        cc.update(new Comment(com3.getId(), com3.getChildComments(), 
                com3.getCommentText(), com3.getCommentDate(), 
                new VotingSystem(20, 0)));
        
        List<Comment> result = cc.getAllCommentsOnPost(post1);
        assertEquals(3, result.size());
        
        assertTrue(result.get(0).getId() == com1.getId());
        assertTrue(result.get(1).getId() == com3.getId());
        assertTrue(result.get(2).getId() == com2.getId());

        result = cc.getChildComments(com1);
        assertTrue(result.get(0).getId() == com3.getId());
        assertTrue(result.get(1).getId() == com2.getId());
        
        result = cc.getRootCommentsForPost(post1);
        assertTrue(result.size() == 1);
        
        com1.removeChildComment(com2);
        com1.removeChildComment(com3);
        cc.update(com1);
        
        assertTrue(cc.getCount() == 1);
        
        mr.remove(mem1.getId());
        assertTrue(pc.getCount() == 0);
        assertTrue(cc.getCount() == 0);

    }

}
