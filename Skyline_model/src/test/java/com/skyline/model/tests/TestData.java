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
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author tomassellden
 */
public class TestData {

    static IBlog blog;
    private final static String PU = "skyline_pu";
    private final static String TEST_PU = "test_skyline_pu";

    @BeforeClass
    public static void beforeClass() {
        blog = BlogFactory.getBlog(PU);
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
        Comment com4 = new Comment("This is the fourth comment");
        Comment com5 = new Comment("This is the fifth comment");
        Comment com6 = new Comment("This is the sixth comment");

        Post post1 = new Post("Post", "Tester", null, "http://www.youtube.com/watch?v=YYkVS9y5iP4");
        pc.add(post1);
        cc.add(com1);
        cc.add(com2);
        cc.add(com3);
        cc.add(com4);
        cc.add(com5);
        cc.add(com6);

        post1.addComment(com1);
        post1.addComment(com2);
        post1.addComment(com3);
        post1.addComment(com4);
        post1.addComment(com5);
        post1.addComment(com6);
        
        mem1.addPost(post1);
        mem1.addComment(com1);
        mem1.addComment(com2);
        mem1.addComment(com3);
        mem1.addComment(com4);
        mem1.addComment(com5);
        mem1.addComment(com6);
        
        assertTrue(post1.getComments().size() == 6);
        
        mr.update(mem1);

        pc.update(post1);

        assertTrue(cc.getCount() == 6);
        assertTrue(pc.getCount() == 1);
        
        com1.addChildComment(com2);
        com1.addChildComment(com3);
        com2.addChildComment(com4);
        com4.addChildComment(com5);
        cc.update(com1);
        cc.update(com2);
        cc.update(com4);
        

        cc.update(new Comment(com1.getId(), com1.getChildComments(), 
                com1.getCommentText(), com1.getCommentDate(), 
                new VotingSystem(150, 0)));
        cc.update(new Comment(com2.getId(), com2.getChildComments(), 
                com2.getCommentText(), com2.getCommentDate(), 
                new VotingSystem(20, 20)));
        cc.update(new Comment(com3.getId(), com3.getChildComments(), 
                com3.getCommentText(), com3.getCommentDate(), 
                new VotingSystem(20, 0)));
        cc.update(new Comment(com4.getId(), com4.getChildComments(), 
                com4.getCommentText(), com4.getCommentDate(), 
                new VotingSystem(150, 0)));
        cc.update(new Comment(com5.getId(), com5.getChildComments(), 
                com5.getCommentText(), com5.getCommentDate(), 
                new VotingSystem(20, 20)));
        cc.update(new Comment(com6.getId(), com6.getChildComments(), 
                com6.getCommentText(), com6.getCommentDate(), 
                new VotingSystem(20, 0)));
        
        List<Comment> result = cc.getAllCommentsOnPost(post1);
        assertEquals(6, result.size());
        
        result = cc.getRootCommentsForPost(post1);
        assertTrue(result.size() == 2);
        
        Member xmem1 = new Member("Anno");
        mr.add(xmem1);

        Comment xcom1 = new Comment("Anno: This is the first comment");
        Comment xcom2 = new Comment("Anno: This is the second comment");
        Comment xcom3 = new Comment("Anno: This is the third comment");
        Comment xcom4 = new Comment("Anno: This is the fourth comment");
        Comment xcom5 = new Comment("Anno: This is the fifth comment");
        Comment xcom6 = new Comment("Anno: This is the sixth comment");

        Post xpost1 = new Post("Post", "Tester", null, "http://www.youtube.com/watch?v=YYkVS9y5iP4");
        pc.add(xpost1);
        cc.add(xcom1);
        cc.add(xcom2);
        cc.add(xcom3);
        cc.add(xcom4);
        cc.add(xcom5);
        cc.add(xcom6);

        xpost1.addComment(xcom1);
        xpost1.addComment(xcom2);
        xpost1.addComment(xcom3);
        xpost1.addComment(xcom4);
        xpost1.addComment(xcom5);
        xpost1.addComment(xcom6);
        
        xmem1.addPost(xpost1);
        xmem1.addComment(xcom1);
        xmem1.addComment(xcom2);
        xmem1.addComment(xcom3);
        xmem1.addComment(xcom4);
        xmem1.addComment(xcom5);
        xmem1.addComment(xcom6);
        
        assertTrue(xpost1.getComments().size() == 6);
        
        mr.update(xmem1);

        pc.update(xpost1);

        assertTrue(cc.getCount() == 12);
        assertTrue(pc.getCount() == 2);
        
        xcom1.addChildComment(xcom2);
        xcom1.addChildComment(xcom3);
        xcom2.addChildComment(xcom4);
        xcom4.addChildComment(xcom5);
        cc.update(xcom1);
        cc.update(xcom2);
        cc.update(xcom4);
        

        cc.update(new Comment(xcom1.getId(), xcom1.getChildComments(), 
                xcom1.getCommentText(), xcom1.getCommentDate(), 
                new VotingSystem(150, 0)));
        cc.update(new Comment(xcom2.getId(), xcom2.getChildComments(), 
                xcom2.getCommentText(), xcom2.getCommentDate(), 
                new VotingSystem(20, 20)));
        cc.update(new Comment(xcom3.getId(), xcom3.getChildComments(), 
                xcom3.getCommentText(), xcom3.getCommentDate(), 
                new VotingSystem(20, 0)));
        cc.update(new Comment(xcom4.getId(), xcom4.getChildComments(), 
                xcom4.getCommentText(), xcom4.getCommentDate(), 
                new VotingSystem(150, 0)));
        cc.update(new Comment(xcom5.getId(), xcom5.getChildComments(), 
                xcom5.getCommentText(), xcom5.getCommentDate(), 
                new VotingSystem(20, 20)));
        cc.update(new Comment(xcom6.getId(), xcom6.getChildComments(), 
                xcom6.getCommentText(), xcom6.getCommentDate(), 
                new VotingSystem(20, 0)));
        
        List<Comment> xresult = cc.getAllCommentsOnPost(xpost1);
        assertEquals(6, xresult.size());
        
        xresult = cc.getRootCommentsForPost(xpost1);
        assertTrue(xresult.size() == 2);
        
    }

}
