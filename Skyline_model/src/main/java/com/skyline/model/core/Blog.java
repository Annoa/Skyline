/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

/**
 *
 * @author tomassellden
 */
public class Blog implements IBlog {

    private final IPostRegistry postContainer = PostRegistry.newInstance();
    private final IMembersRegistry membersContainer = 
            MemberRegistry.newInstance();
    private final ICommentContainer commentContainer = 
            CommentContainer.newInstance();
    
    public Blog() {
    }

    @Override
    public IPostRegistry getPostContainer() {
        return postContainer;
    }

    @Override
    public IMembersRegistry getMemberContainer() {
        return membersContainer;
    }
}
