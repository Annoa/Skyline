/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class representing a member
 *
 * @author tomassellden
 */
public class Member extends AbstractEntity {

    private String name;
    private Date signUpDate;
    private List<Member> favoriteMembers = new ArrayList<Member>();

    public Member() {
    }

    public Member(String name) {
        signUpDate = new Date();
        this.name = name;
    }

    public Member(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return signUpDate;
    }

    public List<Member> getFavoriteMembers() {
        return favoriteMembers;
    }

    public void addFavoriteMembers(Member member) {
        favoriteMembers.add(member);
    }

    public String toString() {
        return "Member = { id = " + getId() + " name = " + name + "}";
    }
}
