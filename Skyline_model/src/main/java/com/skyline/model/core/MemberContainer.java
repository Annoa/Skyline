/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.core.Member;
import com.skyline.model.core.IMembersContainer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomassellden
 */
public class MemberContainer implements IMembersContainer{
    /**
     * 
     * @param name
     * @return member if found otherwise it returns null
     */
    public Member getMember(String name) {
        for (Member m : getRange(0, getCount())) {
            if(m.getName().equals(name))
                return m;
        }
        return null;
    }

    public List<Member> getFavoritesMemberByIntersection(Member memberOne, Member memberTwo) {
        List<Member> favoriteMember = new ArrayList<Member>();
        
        return null;
    }

    public List<Member> getFavoritesMember(Member member) {
       List<Member> favoriteMembers = new ArrayList<Member>();
       return null;
       
    }
    
}
