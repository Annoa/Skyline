package com.skyline.model.core;

import com.skyline.model.utils.IDAO;
import java.util.List;

/**
 * public interface for the CommentContainer
 * 
 * @author Gabriel and Anno
 */
public interface ICommentContainer extends IDAO<Comment, Long> {

    public List<Comment> getAllCommentsOnPost(Post post);

    public List<Comment> getChildComments(Comment parent);

    public List<Comment> getRootCommentsForPost(Post post);

    public Member getAuthor(Comment comment);
}
