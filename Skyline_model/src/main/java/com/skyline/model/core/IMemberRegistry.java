package com.skyline.model.core;

import com.skyline.model.utils.IDAO;
import java.util.List;
import java.util.Set;

/**
 * public interface for the MemberRegistry
 * 
 * @author tomassellden and Anno
 */
public interface IMemberRegistry extends IDAO<Member, Long> {
    
    public Member getMember(String name);
    
    public Set<Member> getMutualFavorites(Member memberOne, Member memberTwo);
    
    public List<Member> search(String searchString);
    
    public boolean validMember(String name, String password);
}
