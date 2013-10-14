/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.IDAO;
import java.util.Set;

/**
 *
 * @author tomassellden
 */
public interface IMemberRegistry extends IDAO<Member, Long> {
    
    public Member getMember(String name);
    
    public Set<Member> getMutualFavorites(Member memberOne, Member memberTwo);
    
    public Set<Member> getFavorites(Member member);
}
