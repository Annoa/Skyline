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
import org.junit.Test;
import org.junit.BeforeClass;

/**
 * This is not a real test. It is just used to put testable values 
 * into the database before trying out methods depending on and 
 * calculating with many different values, such as the REST-method 
 * getPostsForFavoritesOfMember.
 * 
 * @author AntonPalmqvist
 */
public class TestDataForDemo {

    static IBlog blog;
    private final static String PU = "skyline_pu";
    private final static String TEST_PU = "test_skyline_pu";

    @BeforeClass
    public static void beforeClass() {
        blog = new Blog(PU);
    }
    
    @Test
    public void putUpTestValues() {
        IPostContainer pc = blog.getPostContainer();
        IMemberRegistry mr = blog.getMemberRegistry();
        
        //Create posts
        Post postT1 = new Post("Some plain text", "This post that I am writing is very good. The wind is blowing in the leaves and the grass is green.", null, null);
        Post postT2 = new Post("Another text", "Once upon a time", null, null);
        Post postK = new Post("A cool video I found", "This video is very funny!", null, "http://www.youtube.com/watch?v=RnqAXuLZlaE");
        Post postE = new Post("A nice picture", "This picture I took on one of my travels.", "http://www.hdwallpapersinn.com/wp-content/uploads/2012/06/sunset.jpg", null);
        Post postA = new Post("A nice little text", "This is a text I wrote when I was travelling in Australia.", "http://www.hdwallpapersinn.com/wp-content/uploads/2012/06/sunset.jpg", null);
        pc.add(postT1);
        pc.add(postT2);
        pc.add(postK);
        pc.add(postE);
        pc.add(postA);
        
        //Create members
        Member memT = new Member("Tomas", "xxx");
        Member memK = new Member("Krabban", "xxx");
        Member memE = new Member("Epoxy", "xxx");
        Member memA = new Member("Anno", "xxx");
        mr.add(memT);
        mr.add(memK);
        mr.add(memE);
        mr.add(memA);
        
        //Make members favourites of mem1
        memT.addFavoriteMember(memK);
        memT.addFavoriteMember(memE);
        mr.update(memT);
        
        //Chose which members wrote the posts
        memT.addPost(postT1);
        memT.addPost(postT2);
        memK.addPost(postK);
        memE.addPost(postE);
        memA.addPost(postA);
        mr.update(memT);
        mr.update(memK);
        mr.update(memE);
        mr.update(memA);

    }
}
