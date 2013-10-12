/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractEntity;
import java.awt.Image;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private Date date;
    @Column(nullable=false)
    private String title;
    @Column(nullable=true)
    private String bodyText;
    @Basic(fetch=FetchType.LAZY)
    @Lob
    private byte[] postPicture;
    @Column(nullable=true)
    private String postVideo;
    @Embedded
    private VotingSystem votes;
//    private Member member;
    @OneToMany(fetch=FetchType.LAZY,cascade= CascadeType.ALL, mappedBy="post")
    private List<Comment> comments;

    public Post() {
    }
    //TODO Är dåligt för databasen att ha med nulls?

    public Post(String title, String b, byte[] picture, 
            String video) {
        this.date = new Date();
        this.title = title;
        this.bodyText = b;
        this.postPicture = picture;
        this.postVideo = video;
        this.votes = new VotingSystem();
    }

    public Post(Long id, String title, String bodyText, 
            byte[] picture, String video, VotingSystem votingSystem) {
        super(id);
        this.date = new Date();
        this.title = title;
        this.bodyText = bodyText;
        this.postPicture = picture;
        this.postVideo = video;
        this.votes = votingSystem;
    }

    public Date getDate() {
        return date;
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
    
    public boolean addComment() {
        return false;
    }

    @Override
    public String toString() {
        return "Post = { id = " + getId() + " date = " + date.toString()
                + " breadtext = " + bodyText + " "+votes;
    }
}
