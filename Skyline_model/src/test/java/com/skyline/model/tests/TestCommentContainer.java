/////*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.skyline.model.tests;
//
//import com.skyline.model.core.BlogFactory;
//import com.skyline.model.core.Comment;
//import com.skyline.model.core.IBlog;
//import com.skyline.model.core.Member;
//import com.skyline.model.core.Post;
//import com.skyline.model.core.VotingSystem;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.Assert.assertTrue;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// *
// * @author User
// */
//public class TestCommentContainer {
//    
//    private IBlog blog;
//    
//    @Before
//    public void before() {
//        blog = BlogFactory.getBlog(true);
//    }
//    
//    @Test
//    public void getAllComments() {
//        int count = blog.getCommentContainer().getCount();
//        List<Comment> allComments = blog.getCommentContainer().getRange(0, count);
//        assertTrue(allComments.size() == 4);
//    }
//    
//    @Test
//    public void getAllCommentsByAnton() {
//        int count = blog.getCommentContainer().getCount();
//        List<Comment> allComments = blog.getCommentContainer().getRange(0, count);
//        List<Comment> antonComments = new ArrayList<Comment>();
//        Member m = blog.getMemberContainer().getMember("Anton");
//        for (Comment c : allComments)
//            if(c.getAuthor().equals(m))
//                antonComments.add(c);
//        assertTrue(antonComments.size() == 2);
//    }
//    
//    
//    @Test
//    public void getAllChildComments(){
//        int count = blog.getCommentContainer().getCount();
//        List<Comment> allComments = blog.getCommentContainer().getRange(0, count);
//        List<Comment> childComments = new ArrayList<Comment>();
//        for (Comment c : allComments)
//            if(c.getChildComment()==null)
//                childComments.add(c);
//        assertTrue(childComments.size() == 2);
//    }
//    
//    @Test
//    public void testGetters(){
//        //We're not testing post-functionality here.
//        Member m = new Member("jensa");
//        Post p = new Post(m, "post", null, null, null);
//        Comment c = new Comment(p, null, "parentComment", m);
//        String cT = "childComment";
//        Comment cC = new Comment(p, c, cT, m);
//        assertTrue(p.equals(cC.getPost()));
//        assertTrue(m.equals(cC.getAuthor()));
//        assertTrue(c.equals(cC.getChildComment()));
//        //Votingsystem. needs .compare and .equals
//        assertTrue(cC.getVotes().getUpVote()==0);
//        assertTrue(cC.getVotes().getDownVote()==0);
//        assertTrue(cC.getVotes().equals(new VotingSystem()));
//        
//        //String standardtesting. this is obvious.
//        assertTrue(cC.getCommentText().equals(cT));
//        assertTrue(!(cC.getCommentText()==new String(cT)));
//        
//        assertTrue(cC.getAuthor().equals(m));
//        
//        
//        
//    }
//    
//    
//}
