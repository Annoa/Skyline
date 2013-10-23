package com.skyline.rest.skyline_rest;

import com.skyline.model.core.Blog;
import com.skyline.model.core.IBlog;
import com.skyline.model.core.ICommentContainer;
import com.skyline.model.core.IMemberRegistry;
import com.skyline.model.core.IPostContainer;

/**
 * Class to give the web application access to the model.
 * 
 * @author hajo modified by tomassellden
 */
public enum BlogAccess {

    INSTANCE;
    private final IBlog blog;

    private BlogAccess() {
        blog = new Blog("skyline_pu");
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
