/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import java.util.List;

/**
 *
 * @author tomassellden
 */
public interface IMembersContainer {
    
    public Member getMember(String name);
    
    public List<Member> getFavoritesMemberByIntersection(Member memberOne, Member memberTwo);
    
    public List<Member> getFavoritesMember(Member member);
}
