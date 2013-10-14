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
        blog = BlogFactory.getBlog(PU);
    }
    
    @Test
    public void testAddUpdateAndRemoveProduct() {
        IMemberRegistry mr = blog.getMemberContainer();

        Member m1 = new Member("Anno");
        mr.add(m1);
        assertTrue(mr.getCount() == 1);

        Member m2 = mr.find(m1.getId());
        // Not same transaction
        assertTrue(m2 != m1);
        // Equal by value
        assertTrue(m2.equals(m1));

        Member m = new Member(m1.getId(), "updated");
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
        IMemberRegistry mr = blog.getMemberContainer();
        Member tomas = new Member("Tomas");
        mr.add(tomas);
        assertTrue(mr.getCount() == 1);
        mr.remove(tomas.getId());
        assertTrue(mr.getCount() == 0);
    }
    
    @Test
    public void testGetByName() {
        IMemberRegistry mr = blog.getMemberContainer();
        Member tomas = new Member("Tomas");
        mr.add(tomas);
        assertTrue(mr.getCount() == 1);
        mr.remove(mr.getMember("Tomas").getId());
        assertTrue(mr.getCount() == 0);
    }

//    @Test
//    public void getAllMembers() {
//        int count = blog.getMemberContainer().getCount();
//        List<Member> allMembers = blog.getMemberContainer().getRange(0, count);
//        assertTrue(allMembers.size() == 4);
//    }

//    @Test
//    public void getFavoriteFriendsOfTomas() {
//        Member m = blog.getMemberContainer().getMember("Tomas");
//        System.out.println("member m is equal to" + m.toString());
//        List<Member> tomasFavoriteFriends = m.getFavoriteMembers();
//        assertTrue(tomasFavoriteFriends.size() == 3);
//    }
//
//    @Test
//    public void getFavoriteFriendsByIntersection() {
//        Member anton = blog.getMemberContainer().getMember("Anton");
//        Member tomas = blog.getMemberContainer().getMember("Tomas");
//        List<Member> commonFriends = blog.getMemberContainer().getMutualFriendsberByIntersection(tomas, anton);
//        assertTrue(commonFriends.size() == 1);
//        Member cf = commonFriends.get(0);
//        assertTrue(cf.getName().equals("Krabban"));
//
//    }
}
