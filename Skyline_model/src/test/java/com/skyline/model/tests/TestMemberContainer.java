/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.tests;

import com.skyline.model.core.BlogFactory;
import com.skyline.model.core.IBlog;
import com.skyline.model.core.IMemberRegistry;
import com.skyline.model.core.Member;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author tomassellden
 */
public class TestMemberContainer {

    static IBlog blog;
    private final static String PU = "skyline_pu";
    private final static String TEST_PU = "test_skyline_pu";

    @BeforeClass
    public static void beforeClass() {
        blog = BlogFactory.getBlog(TEST_PU);
    }
    
    @Test
    public void testAddUpdateAndRemoveMember() {
        IMemberRegistry mr = blog.getMemberRegistry();

        Member m1 = new Member("Anno");
        mr.add(m1);
        assertTrue(mr.getCount() == 1);

        Member m2 = mr.find(m1.getId());
        // Not same transaction
        assertTrue(m2 != m1);
        // Equal by value
        assertTrue(m2.equals(m1));

        Member m = new Member(m1.getId(), m1.getDate(), "updated"
                , m1.getPosts(), m1.getComments(), m1.getFavoriteMembers());
        m1 = mr.update(m);
        /*
         * Id NOT changed here we have two Products with
         * same id but different state! 
         * Seems to be no single simple solution to this..?!
         */
        assertTrue(m2.equals(m1));
        assertFalse(m2.getName().equals(m1.getName()));
        assertTrue(mr.getCount() == 1);

        mr.remove(m1.getId());
        assertTrue(mr.getCount() == 0);
        assertTrue(mr.find(m1.getId()) == null);

        // No change in program (but deleted from database)
        assertTrue(m2.equals(m1));
        assertFalse(m2.getName().equals(m1.getName()));
    }
    
    @Test
    public void testAdd() {
        IMemberRegistry mr = blog.getMemberRegistry();
        Member tomas = new Member("Tomas");
        mr.add(tomas);
        assertTrue(mr.getCount() == 1);
        mr.remove(tomas.getId());
        assertTrue(mr.getCount() == 0);
    }
    
    @Test
    public void testGetByName() {
        IMemberRegistry mr = blog.getMemberRegistry();
        Member tomas = new Member("Tomas");
        mr.add(tomas);
        assertTrue(mr.getCount() == 1);
        mr.remove(mr.getMember("Tomas").getId());
        assertTrue(mr.getCount() == 0);
    }
    
    @Test
    public void testFavorites() {
        IMemberRegistry mr = blog.getMemberRegistry();
        Member tomas = new Member("Tomas");
        Member anton = new Member("Anton");
        Member anno = new Member("Anno");
        mr.add(tomas);
        mr.add(anton);
        mr.add(anno);
        
        anno.addFavoriteMember(tomas);
        anno.addFavoriteMember(anton);
        anno = mr.update(anno);
        tomas.addFavoriteMember(anton);
        tomas = mr.update(tomas);
        
        assertEquals(2, anno.getFavoriteMembers().size());
        assertEquals(1, tomas.getFavoriteMembers().size());
        
        Set<Member> result = mr.getMutualFavorites(anno, tomas);
        assertEquals(1, result.size());
        assertEquals(true, result.contains(anton));
        
        mr.remove(tomas.getId());
        mr.remove(anton.getId());
        mr.remove(anno.getId());
        assertEquals(null, mr.find(tomas.getId()));
        assertEquals(null, mr.find(anno.getId()));
        assertEquals(null, mr.find(anton.getId()));
        
    }
    
    @Test
    public void testSearch() {
        IMemberRegistry mr = blog.getMemberRegistry();
        
        Member mem = new Member("Anno");
        mr.add(mem);
        
        String searchString = "An";
        List<Member> result = mr.search(searchString);
        assertTrue(result.contains(mem));
        
        searchString = "nn";
        List<Member> result2 = mr.search(searchString);
        assertTrue(result2.contains(mem));
        
        searchString = "ZZ";
        List<Member> result3 = mr.search(searchString);
        assertFalse(result3.contains(mem));
        
        mr.remove(mem.getId());
        assertTrue(mr.getCount() == 0);
    }

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
