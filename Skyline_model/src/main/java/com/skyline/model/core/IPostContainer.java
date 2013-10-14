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
public interface IPostContainer extends IDAO<Post, Long> {
    
//    public Member getMemberByPost(Post post);
//        
//    
//    public List<Post> getAllPostByMember(Post post);
    
    public List<Post> getPostsByVotes(int amount);
    
    public List<Post> getPostsByTime(int amount);
    
}