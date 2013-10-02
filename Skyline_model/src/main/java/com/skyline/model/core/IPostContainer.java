/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.core.Member;
import java.util.List;

/**
 * interface to PostContainer
 * @author tomassellden
 */
public interface IPostContainer {
    
    public List<Member> getPostByMember(Member member);
    
    public List<Member> getPostByFavoriteMember(Member member);
}
