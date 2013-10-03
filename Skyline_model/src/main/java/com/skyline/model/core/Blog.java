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

    private final IPostContainer postContainer = PostContainer.newInstance();
    private final IMembersContainer membersContainer = MemberContainer.newInstance();

    public Blog() {
    }

    @Override
    public IPostContainer getPostContainer() {
        return postContainer;
    }

    @Override
    public IMembersContainer getMemberContainer() {
        return membersContainer;
    }
}
