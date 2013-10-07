/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.skyline_rest;

import com.skyline.model.core.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * * Need this because translation from XML to JSON
 *
 * @author tomassellden
 */
@XmlRootElement(name = "Member")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MemberProxy {

    private Member member;
    private List<MemberProxy> favoriteMembers;

    protected MemberProxy() {
    }

    public MemberProxy(Member member) {
        this.member = member;
        favoriteMembers = new ArrayList<MemberProxy>();
    }
    @XmlElement(required = true)
    public Long getId() {
        return member.getId();
    }
    @XmlElement(required = true)
    public Date getDate() {
        return member.getDate();
    }
    @XmlElement(required = true)
    public String getName() {
        return member.getName();
    }
    @XmlElement(name = "favoriteMembers")
    public List<MemberProxy> getFavoriteMembers() {
        return favoriteMembers;
    }
}
