/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * A container for accessing posts of different kinds
 *
 * @author Anno
 */
public class PostContainer extends AbstractDAO<Post, Long> implements IPostContainer {

    public static IPostContainer newInstance() {
        return new PostContainer();
    }

    @Override
    public List<Post> getPostByMember(Member member) {
        return member.getPostByMember();
        

    }

    @Override
    public List<Post> getPostByFavoriteMember(Member member) {
        List<Post> favoriteMembersPost = new ArrayList<Post>();
        for (Member m : member.getFavoriteMembers()) {
            for (Post p : m.getPostByMember())
            favoriteMembersPost.add(p);
        }
        return favoriteMembersPost;


    }
}
