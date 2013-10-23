package com.skyline.model.core;

import com.skyline.model.utils.AbstractEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class representing a comment of a post.
 *
 * @author Gabriel and Anno (JPA tags)
 */
@Entity
public class Comment extends AbstractEntity implements Comparable<Comment>, Serializable{

    @OneToMany (orphanRemoval=true, cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinColumn(name="PARENTCOMMENT", referencedColumnName="ID")
    private Set<Comment> childComments;
    @Lob
    private String commentText;
    @Temporal(TemporalType.DATE)
    private Date commentDate;
    @Embedded
    private VotingSystem votes;

    public Comment() {
    }

    /**
     * Used to create a new comment in database
     */
    public Comment(String commentText) {
        this.childComments = new HashSet<Comment>();
        this.commentText = commentText;
        this.commentDate = new Date();
        this.votes = new VotingSystem();
    }

    /**
     * Used to update a comment in database
     * @param childComments is empty, if it has no children
     */
    public Comment(Long id, Set<Comment> childComments, String commentText,
            Date date, VotingSystem votes) {
        super(id);
        this.childComments = childComments;
        this.commentText = commentText;
        this.commentDate = date;
        this.votes = votes;
    }

    public Set<Comment> getChildComments() {
        return childComments;
    }

    public String getCommentText() {
        return commentText;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public boolean addChildComment(Comment comment) {
        return this.childComments.add(comment);
    }
    
    public boolean removeChildComment(Comment comment) {
        return this.childComments.remove(comment);
    }

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
