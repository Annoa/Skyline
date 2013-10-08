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
    
    private Post post;
    private Comment parentComment;
    private String commentText;
    private Date commentDate;
    private Member author;
    private VotingSystem votes;
    
    public Comment(){
        
    }
    
    /**
     * @param parentComment == null, if top-level 
     * @param Gabriel 
     */
    public Comment(Post post, Comment parentComment, String commentText, 
            Member author) {
        this.post = post;     
        this.parentComment = parentComment; 
        this.commentText = commentText;
        this.commentDate = new Date();
        this.author = author;
        this.votes = new VotingSystem();
    }
    
        public Comment(Long id, Post post, Comment parentComment, String commentText, 
                Date date, Member author, VotingSystem votes) {
        super(id);
        this.post = post;     
        this.parentComment = parentComment; 
        this.commentText = commentText;
        this.commentDate = date;
        this.author = author;
        this.votes = votes;
    }

    //TODO: Constructor with ID for database-constructing
    //TODO: getter for voting system 
    //TODO: Cleanup.
   
    
    public Post getPost() {
        return post;
    }
    
    public Comment getParentComment() {
        return parentComment;
    }
    
    public String getCommentText(){
        return commentText;
    }
    public Date getCommentDate(){
        return commentDate;
    }
    public Member getAuthor() {
        return author;
    }
    public VotingSystem getVotes(){
        return votes;
    }
    
    @Override
    public String toString(){
        return "Comment = \n{ @Post = " + post 
                + " \nDate = " + commentDate 
                + "\nText = " + commentText + 
                "\nwritten by = "+ author;
    }
}