/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.skyline_rest;

import com.skyline.model.core.IBlog;
import com.skyline.model.core.ICommentContainer;
import com.skyline.model.core.IMemberRegistry;
import com.skyline.model.core.IPostContainer;

/**
 *
 * @author hajo modified by tomassellden
 */
public enum Blog {

    INSTANCE;
    private final IBlog blog;

    private Blog() {
        blog = new com.skyline.model.core.Blog("skyline_pu");
    }

    public IMemberRegistry getMembersRegistry() {
        return blog.getMemberRegistry();
    }

    public IPostContainer getPostContainer() {
        return blog.getPostContainer();
    }
    
    public ICommentContainer getCommentContainer() {
        return blog.getCommentContainer();
    }
}
