/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.IDAO;
import java.util.List;

/**
 *
 * @author tomassellden
 */
public interface IMemberRegistry extends IDAO<Member, Long> {
    
    public Member getMember(String name);
    
    public List<Member> getFavoritesMemberByIntersection(Member memberOne, Member memberTwo);
    
    public List<Member> getFavoritesMember(Member member);
}
