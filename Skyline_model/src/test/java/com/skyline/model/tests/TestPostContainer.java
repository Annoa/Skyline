/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.tests;

import com.skyline.model.core.BlogFactory;
import com.skyline.model.core.IBlog;
import com.skyline.model.core.IMemberRegistry;
import com.skyline.model.core.IPostContainer;
import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import com.skyline.model.core.VotingSystem;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author tomassellden
 */
public class TestPostContainer {

    static IBlog blog;
    private final static String PU = "skyline_pu";
    private final static String TEST_PU = "test_skyline_pu";

    @BeforeClass
    public static void beforeClass() {
        blog = BlogFactory.getBlog(TEST_PU);
    }
    
    @Test
    public void testGetRange() {
        IPostContainer pc = blog.getPostContainer();
        
        Post post1 = new Post("Post", "Tester", null, null);
        Post post2 = new Post("Post2", "Allla", null, null);
        Post post3 = new Post("Post3", "allaa", null, null);
        pc.add(post1);
        pc.add(post2);
        pc.add(post3);
        assertTrue(pc.getCount() == 3);

        post1 = pc.update(new Post(post1.getId(), post1.getDate(),
                post1.getTitle(),
                post1.getBodyText(), post1.getPostPicture(), 
                post1.getPostVideo(), new VotingSystem(50, 50)));
        post2 = pc.update(new Post(post2.getId(), post2.getDate(),
                post2.getTitle(),
                post2.getBodyText(), post2.getPostPicture(), 
                post2.getPostVideo(), new VotingSystem(150, 0)));
        post3 = pc.update(new Post(post3.getId(), post3.getDate(),
                post3.getTitle(),
                post3.getBodyText(), post3.getPostPicture(), 
                post3.getPostVideo(), new VotingSystem(50, 0)));
        List<Post> result = pc.getRangeByVotes(0, 50);
        assertTrue(result.get(0).getId() == post2.getId());
        assertTrue(result.get(1).getId() == post3.getId());
        assertTrue(result.get(2).getId() == post1.getId());
        
        result = pc.getRangeByTime(0, 50);
        assertTrue(result.get(0).getId() == post3.getId());
        assertTrue(result.get(1).getId() == post2.getId());
        assertTrue(result.get(2).getId() == post1.getId());
        
        pc.remove(post1.getId());
        pc.remove(post2.getId());
        pc.remove(post3.getId());
        assertTrue(pc.getCount() == 0);
        
    }
    
    @Test
    public void testGetAuthor() {
        IPostContainer pc = blog.getPostContainer();
        IMemberRegistry mr = blog.getMemberRegistry();
        
        Post post1 = new Post("Post", "Tester", null, null);
        pc.add(post1);
        
        Member mem = new Member("Tomas");
        mr.add(mem);
        mem.addPost(post1);
        mr.update(mem);
        
        assertTrue(mem.getPosts().contains(post1));
        assertTrue(pc.getAuthor(post1).getId() == mem.getId());
        
        mr.remove(mem.getId());
        assertTrue(pc.getCount() == 0);
        assertTrue(mr.getCount() == 0);
        
    }
    
//    @Test
//    public void testAdd() {
//        IMemberRegistry mr = blog.getMemberRegistry();
//        Member tomas = new Member("Tomas");
//        mr.add(tomas);
//        assertTrue(mr.getCount() == 1);
//        mr.remove(tomas.getId());
//        assertTrue(mr.getCount() == 0);
//    }
//    
//    @Test
//    public void testGetByName() {
//        IMemberRegistry mr = blog.getMemberRegistry();
//        Member tomas = new Member("Tomas");
//        mr.add(tomas);
//        assertTrue(mr.getCount() == 1);
//        mr.remove(mr.getMember("Tomas").getId());
//        assertTrue(mr.getCount() == 0);
//    }
//    
//    @Test
//    public void testFavorites() {
//        IMemberRegistry mr = blog.getMemberRegistry();
//        Member tomas = new Member("Tomas");
//        Member anton = new Member("Anton");
//        Member anno = new Member("Anno");
//        mr.add(tomas);
//        mr.add(anton);
//        mr.add(anno);
//        
//        anno.addFavoriteMember(tomas);
//        anno.addFavoriteMember(anton);
//        anno = mr.update(anno);
//        tomas.addFavoriteMember(anton);
//        tomas = mr.update(tomas);
//        
//        assertEquals(2, anno.getFavoriteMembers().size());
//        assertEquals(1, tomas.getFavoriteMembers().size());
//        
//        Set<Member> result = mr.getMutualFavorites(anno, tomas);
//        assertEquals(1, result.size());
//        assertEquals(true, result.contains(anton));
//        
//        mr.remove(tomas.getId());
//        mr.remove(anton.getId());
//        mr.remove(anno.getId());
//        assertEquals(null, mr.find(tomas.getId()));
//        assertEquals(null, mr.find(anno.getId()));
//        assertEquals(null, mr.find(anton.getId()));
//        
//    }

//    @Test
//    public void getAllMembers() {
//        int count = blog.getMemberRegistry().getCount();
//        List<Member> allMembers = blog.getMemberRegistry().getRange(0, count);
//        assertTrue(allMembers.size() == 4);
//    }

//    @Test
//    public void getFavoriteFriendsOfTomas() {
//        Member m = blog.getMemberRegistry().getMember("Tomas");
//        System.out.println("member m is equal to" + m.toString());
//        List<Member> tomasFavoriteFriends = m.getFavoriteMembers();
//        assertTrue(tomasFavoriteFriends.size() == 3);
//    }
//
//    @Test
//    public void getFavoriteFriendsByIntersection() {
//        Member anton = blog.getMemberRegistry().getMember("Anton");
//        Member tomas = blog.getMemberRegistry().getMember("Tomas");
//        List<Member> commonFriends = blog.getMemberRegistry().getMutualFriendsberByIntersection(tomas, anton);
//        assertTrue(commonFriends.size() == 1);
//        Member cf = commonFriends.get(0);
//        assertTrue(cf.getName().equals("Krabban"));
//
//    }
}
