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
    private Date date;
    private List<Member> favoriteMembers = new ArrayList<Member>();

    public Member() {
    }

    public Member(String name) { //Date date
        //this.date = date;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getDate(Date date) {
        return date;
    }

    public List<Member> getFavoriteMembers() {
        return favoriteMembers;
    }

    public void addFavoriteMembers(Member member) {
        favoriteMembers.add(member);
    }
}
