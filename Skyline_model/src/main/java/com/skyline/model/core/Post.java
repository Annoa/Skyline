/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractEntity;
import java.util.Date;

/**
 * Class representing a post containing one or many contents such as body text,
 * picture and video.
 *
 * @author Epoxy
 */
//@Entity
public class Post extends AbstractEntity {

    private Date date;
    private String title;
    private BodyText bodyText;
    private PostPicture postPicture;
    private PostVideo postVideo;
    private VotingSystem votes;
    private Member member;

    public Post() {
    }
    //TODO Är dåligt för databasen att ha med nulls?

    public Post(Member member, String title, BodyText b, PostPicture pP, 
            PostVideo pV) {
        this.member = member;
        this.date = new Date();
        this.title = title;
        this.bodyText = b;
        this.postPicture = pP;
        this.postVideo = pV;
        this.votes = new VotingSystem();
    }

    public Post(Long id, Member member, String title, BodyText b, 
            PostPicture pP, PostVideo pV, VotingSystem vS) {
        super(id);
        this.member = member;
        this.date = new Date();
        this.title = title;
        this.bodyText = b;
        this.postPicture = pP;
        this.postVideo = pV;
        this.votes = vS;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public BodyText getBodyText() {
        return bodyText;
    }

    public PostPicture getPostPicture() {
        return postPicture;
    }

    public PostVideo getPostVideo() {
        return postVideo;
    }

    public VotingSystem getVotes() {
        return votes;
    }

    public Member getMember() {
        return member;
    }

    @Override
    public String toString() {
        return "Post = { id = " + getId() + " date = " + date.toString()
                + " breadtext = " + bodyText.getBreadText() + " "+votes;
    }
}
