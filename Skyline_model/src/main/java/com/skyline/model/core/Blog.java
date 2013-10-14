/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

/**
 * The main container for the blog.
 * Contains all other containers.
 * 
 * @author anno
 */
public class Blog implements IBlog {
    
    private IPostContainer postContainer;
    private IMemberRegistry memberRegistry;
    private ICommentContainer commentContainer;

    public Blog(String persistanceUnitName) {
        this.postContainer = new PostContainer(persistanceUnitName);
        this.commentContainer = new CommentContainer(persistanceUnitName);
        this.memberRegistry = new MemberRegistry(persistanceUnitName);
    }

    @Override
    public IPostContainer getPostContainer() {
        return postContainer;
    }

    @Override
    public IMemberRegistry getMemberContainer() {
        return memberRegistry;
    }
    
    @Override
    public ICommentContainer getCommentContainer(){
        return commentContainer;
    }
    
    
}
