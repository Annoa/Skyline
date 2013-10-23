/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.tests;

import com.skyline.model.core.Blog;
import com.skyline.model.core.IBlog;
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
public class TestPostContainer {

    static IBlog blog;
    private final static String PU = "skyline_pu";
    private final static String TEST_PU = "test_skyline_pu";

    @BeforeClass
    public static void beforeClass() {
        blog = new Blog(TEST_PU);
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
        
        Member mem = new Member("Tomas", "xxx");
        mr.add(mem);
        mem.addPost(post1);
        mr.update(mem);
        
        assertTrue(mem.getPosts().contains(post1));
        assertTrue(pc.getAuthor(post1).getId() == mem.getId());
        
        mr.remove(mem.getId());
        assertTrue(pc.getCount() == 0);
        assertTrue(mr.getCount() == 0);
        
    }
    
    @Test
    public void testGetAuthorPostRange() {
        IPostContainer pc = blog.getPostContainer();
        IMemberRegistry mr = blog.getMemberRegistry();
        
        Post post1 = new Post("Post", "Tester", null, null);
        Post post2 = new Post("Post", "Tester", null, null);
        Post post3 = new Post("Post", "Tester", null, null);
        pc.add(post1);
        pc.add(post2);
        pc.add(post3);
        
        Member mem = new Member("Tomas", "xxx");
        mr.add(mem);
        mem.addPost(post1);
        mem.addPost(post2);
        mem.addPost(post3);
        mr.update(mem);
        
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
        
        List<Post> result = pc.getPostsOfMemberByVotes(mem, 0, 50);
        assertTrue(result.get(0).getId() == post2.getId());
        assertTrue(result.get(1).getId() == post3.getId());
        assertTrue(result.get(2).getId() == post1.getId());
        
        mr.remove(mem.getId());
        assertTrue(pc.getCount() == 0);
        assertTrue(mr.getCount() == 0);
        
    }
    
    @Test
    public void testGetPostsOfMemberFavorites() {
        IPostContainer pc = blog.getPostContainer();
        IMemberRegistry mr = blog.getMemberRegistry();
        
        Post post1 = new Post("Post", "Tester", null, null);
        Post post2 = new Post("Post", "Tester", null, null);
        Post post3 = new Post("Post", "Tester", null, null);
        pc.add(post1);
        pc.add(post2);
        pc.add(post3);
        
        Member mem = new Member("Tomas", "xxx");
        Member mem1 = new Member("Krabban", "xxx");
        mr.add(mem);
        mr.add(mem1);
        mem.addPost(post1);
        mem1.addPost(post2);
        mem1.addPost(post3);
        mr.update(mem);
        mr.update(mem1);
        
        mem.addFavoriteMember(mem1);
        mr.update(mem);
        
        List<Post> mem1Posts = pc.getPostsOfMemberFavorites(mem, 0, 2);
        assertTrue(mem1Posts.contains(post2));
        assertTrue(mem1Posts.contains(post3));
        
        mr.remove(mem.getId());
        mr.remove(mem1.getId());
        
        assertTrue(mr.getCount() == 0);
    }
}
