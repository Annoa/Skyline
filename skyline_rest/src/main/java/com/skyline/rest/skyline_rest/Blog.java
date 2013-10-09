/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.skyline_rest;

import com.skyline.model.core.BlogFactory;
import com.skyline.model.core.IBlog;
import com.skyline.model.core.ICommentContainer;
import com.skyline.model.core.IMembersRegistry;
import com.skyline.model.core.IPostRegistry;

/**
 *
 * @author hajo modified by tomassellden
 */
public enum Blog {

    INSTANCE;
    private final IBlog blog;

    private Blog() {
        blog = BlogFactory.getBlog(true);
    }

    public IMembersRegistry getMembersRegistry() {
        return blog.getMemberContainer();
    }

    public IPostRegistry getPostContainer() {
        return blog.getPostContainer();
    }
    
    public ICommentContainer getCommentContainer() {
        return blog.getCommentContainer();
    }
}
