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
public class PostRegistry extends AbstractDAO<Post, Long> implements IPostRegistry {

    public static IPostRegistry newInstance() {
        return new PostRegistry();
    }

    @Override
    public Member getMemberByPost(Post post) {
        return post.getMember();
        

    }

    @Override
    public List<Post> getAllPostByMember(Post post) {
        List<Post> getAllPostByMember = new ArrayList<Post>();
        Member m = post.getMember();
        for (Post p : getRange(getCount())) {
            if (p.getMember().equals(m)) {
                getAllPostByMember.add(p);
            }
        }
        return getAllPostByMember;
    }
}
