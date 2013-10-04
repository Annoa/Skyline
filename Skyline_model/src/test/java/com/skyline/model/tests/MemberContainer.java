/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.tests;

import com.skyline.model.core.BlogFactory;
import com.skyline.model.core.IBlog;
import com.skyline.model.core.Member;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomassellden
 */
public class MemberContainer {

    IBlog member;

    @Before
    public void before() {
        member = BlogFactory.getBlog(true);
    }

    @Test
    public void getAllMembers() {
        int count = member.getMemberContainer().getCount();
        List<Member> allMembers = member.getMemberContainer().getRange(count);
        assertTrue(allMembers.size() == 4);
    }
    
    @Test
    public void getFavoriteFriendsOfTomas() {
        Member m = member.getMemberContainer().getMember("Tomas");
        List<Member> tomasFavoriteFriends = m.getFavoriteMembers();
        assertTrue(tomasFavoriteFriends.size() == 2);
    }
}
