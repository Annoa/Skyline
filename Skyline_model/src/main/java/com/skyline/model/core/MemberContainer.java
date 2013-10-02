/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomassellden
 */
public class MemberContainer extends AbstractDAO<Member, Long> implements IMembersContainer{
    /**
     * 
     * @param name
     * @return member if found otherwise it returns null
     */
    public Member getMember(String name) {
        for (Member m : getRange(getCount())) {
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
