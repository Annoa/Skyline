/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractDAO;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author tomassellden
 */
public class MemberRegistry extends AbstractDAO<Member, Long> implements IMemberRegistry {

    public MemberRegistry(String puName) {
        super(Member.class, puName);
    }

    /**
     *
     * @param name
     * @return member if found otherwise it returns null
     */
    public Member getMember(String name) {
        EntityManager em = super.getEntityManager();
        TypedQuery<Member> query = em.createQuery
                ("select m from Member m where m.name = :name", Member.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    /**
     *
     * @param memberOne
     * @param memberTwo
     * @return a List containing common favoritesmembers or null if they don´t
     * have any common favoritemembers
     */
    public Set<Member> getMutualFavorites(Member memberOne, Member memberTwo) {
//        EntityManager em = super.getEntityManager();
//        TypedQuery<Member> query = em.createQuery
//                ("select m from Member m", Member.class);
//        
//        
//        Set<Member> commonFavoritesMember = memberOne.getFavoriteMembers();
//        commonFavoritesMember.retainAll(memberTwo.getFavoriteMembers());
//        return commonFavoritesMember;
        return null;
    }

    public Set<Member> getFavorites(Member member) {
        return member.getFavoriteMembers();

    }
}
