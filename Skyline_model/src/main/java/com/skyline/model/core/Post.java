/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Class representing a post containing one or many contents such as body text,
 * picture and video.
 *
 * @author Epoxy and Anno
 */
@Entity
public class Post extends AbstractEntity implements Serializable {

    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date createDate;
    @Column(nullable=false)
    private String title;
    @Column(nullable=true)
    private String bodyText;
    @Basic(fetch=FetchType.LAZY)
    @Column(nullable=true)
    @Lob
    private byte[] postPicture;
    @Column(nullable=true)
    private String postVideo;
    @Embedded
    private VotingSystem votes;
    @OneToMany(fetch=FetchType.LAZY,cascade= CascadeType.ALL)
    @JoinColumn
    private List<Comment> comments;

    public Post() {
    }

    public Post(String title, String b, byte[] picture, 
            String video) {
        this.createDate = new Date();
        this.title = title;
        this.bodyText = b;
        this.postPicture = picture;
        this.postVideo = video;
        this.votes = new VotingSystem();
    }

    public Post(Long id, String title, String bodyText, 
            byte[] picture, String video, VotingSystem votingSystem) {
        super(id);
        this.createDate = new Date();
        this.title = title;
        this.bodyText = bodyText;
        this.postPicture = picture;
        this.postVideo = video;
        this.votes = votingSystem;
    }

    public Date getDate() {
        return createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getBodyText() {
        return bodyText;
    }

    public byte[] getPostPicture() {
        return postPicture;
    }

    public String getPostVideo() {
        return postVideo;
    }

    public VotingSystem getVotes() {
        return votes;
    }
    
    public List<Comment> getComments() {
        return comments;
    }
    
    public boolean addComment(Comment comment) {
        return this.comments.add(comment);
    }
    
    public boolean removeComment(Comment comment) {
        return this.comments.remove(comment);
    }

    @Override
    public String toString() {
        return "Post = { id = " + getId() + " date = " + createDate.toString()
                + " breadtext = " + bodyText + " "+votes;
    }
}
