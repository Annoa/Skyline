/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractEntity;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class representing a comment of a post.
 *
 * @author Gabriel
 */
@Entity
public class Comment extends AbstractEntity implements Comparable<Comment>{

//    private Post post;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Comment childComment;
    private String commentText;
    @Temporal(TemporalType.DATE)
    private Date commentDate;
//    private Member author;
    @Embedded
    private VotingSystem votes;

    public Comment() {
    }

    /**
     * @param parentComment == null, if top-level
     * @param Gabriel
     */
    public Comment(Comment parentComment, String commentText) {
        this.childComment = parentComment;
        this.commentText = commentText;
        this.commentDate = new Date();
        this.votes = new VotingSystem();
    }

    public Comment(Long id, Comment parentComment, String commentText,
            Date date, VotingSystem votes) {
        super(id);
        this.childComment = parentComment;
        this.commentText = commentText;
        this.commentDate = date;
        this.votes = votes;
    }

    //TODO: Constructor with ID for database-constructing
    //TODO: getter for voting system 
    //TODO: Cleanup.
//    public Post getPost() {
//        return post;
//    }
    public Comment getChildComment() {
        return childComment;
    }

    public String getCommentText() {
        return commentText;
    }

    public Date getCommentDate() {
        return commentDate;
    }
//    public Member getAuthor() {
//        return author;
//    }

    public VotingSystem getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "Comment = \n{ \nDate = " + commentDate
                + "\nText = " + commentText + "}";
    }
    
    @Override
    public int compareTo(Comment another) {
        return this.votes.compareTo(another.getVotes());
    }
}
