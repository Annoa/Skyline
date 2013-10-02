/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractDAO;
import java.util.List;

/**
 * A container for accessing posts of different kinds
 *
 * @author Anno
 */
public class PostContainer extends AbstractDAO
        <Post, Long> implements IPostContainer {

    public List<Member> getPostByMember(Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Member> getPostByFavoriteMember(Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
