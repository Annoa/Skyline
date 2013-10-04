/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractDAO;
import java.util.List;

/**
 *
 * @author tomassellden
 */
public class MemberContainer extends AbstractDAO<Member, Long> implements IMembersContainer {

    public static IMembersContainer newInstance() {
        return new MemberContainer();
    }
    /**
     *
     * @param name
     * @return member if found otherwise it returns null
     */
    public Member getMember(String name) {
        for (Member m : getRange(getCount())) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    /**
     *
     * @param memberOne
     * @param memberTwo
     * @return a List containing common favoritesmembers or null if they don´t
     * have any common favoritemembers∫
     */
    public List<Member> getFavoritesMemberByIntersection(Member memberOne, Member memberTwo) {

        List<Member> commonFavoritesMember = memberOne.getFavoriteMembers();
        commonFavoritesMember.retainAll(memberTwo.getFavoriteMembers());
        return commonFavoritesMember;
    }

    public List<Member> getFavoritesMember(Member member) {
        return member.getFavoriteMembers();

    }
}
