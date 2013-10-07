/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractEntity;
import java.util.Date;

/**
 * Class representing a comment of a post. 
 * 
 * @author Epoxy
 */
public class Comment extends AbstractEntity{
    
    private Post rootPost;
    private Comment rootComment;
    private BodyText commentText;
    private Date commentDate;
    
    public Comment(){
        
    }
    
    public Comment(Post rootPost, Comment previousComment, BodyText commentText) {
        this.rootPost = rootPost;
        if(previousComment != null) {
            this.rootComment = previousComment;
        }
        this.commentText = commentText;
        commentDate = new Date();
    }
    
    public Post getRootPost(){
        return rootPost;
    }
    
    public BodyText getBodyText(){
        return commentText;
    }
    
    public Date getCommentDate(){
        return commentDate;
    }
    
    public String toString(){
        return "Comment = \n{ RootPost = " + rootPost 
                + " \nDate = " + commentDate 
                + "\nText = " + commentText;
    }
}
