/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractDAO;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PreRemove;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

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
        TypedQuery<Member> query = em.createQuery("select m from Member m where m.name = :name", Member.class);
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
        Set<Member> firstFavorites = memberOne.getFavoriteMembers();        
        firstFavorites.retainAll(memberTwo.getFavoriteMembers());
        return firstFavorites;
    }
}
