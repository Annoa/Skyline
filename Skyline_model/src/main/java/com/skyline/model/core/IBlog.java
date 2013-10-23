package com.skyline.model.core;

/**
 * public interface for the Blog
 *
 * @author tomassellden
 */
public interface IBlog {

    public IMemberRegistry getMemberRegistry();

    public IPostContainer getPostContainer();

    public ICommentContainer getCommentContainer();
}
