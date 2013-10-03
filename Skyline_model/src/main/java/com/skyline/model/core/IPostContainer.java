/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.IDAO;
import java.util.List;

/**
 * interface to PostContainer
 * @author tomassellden
 */
public interface IPostContainer extends IDAO<Post, Long>{
    
    public List<Post> getPostByMember(Member member);
    
    public List<Post> getPostByFavoriteMember(Member member);
}
