/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractEntity;
import java.util.Date;

/**
 * Class representing a post containing one or many 
 * contents such as body text, picture and video.
 * 
 * @author Epoxy
 */
//@Entity
public class Post extends AbstractEntity{
    private Date date;
    private String title;
    private BodyText bodyText;
    private PostPicture postPicture;
    private PostVideo postVideo;
    private int upVotes;
    private int downVotes;
    private Member member;
    public Post(){
    }
    //TODO Är dåligt för databasen att ha med nulls?
    public Post(Member member, String title, BodyText b, PostPicture pP, PostVideo pV){
        this.member = member;
        date = new Date();
        this.title = title;
        if(b!=null){
            bodyText = b;
        }
        if(pP!=null){
            postPicture = pP;
        }
        if(pV!=null){
            postVideo = pV;
        }
        upVotes = 0;
        downVotes = 0;
    }
    public Date getDate(){
        return date;
    }
    public String getTitle(){
        return title;
    }
    public BodyText getBodyText(){
        return bodyText;
    }
    public PostPicture getPostPicture(){
        return postPicture;
    }
    public PostVideo getPostVideo(){
        return postVideo;
    }
    public int getUpVotes(){
        return upVotes;
    }
    public int getDownVotes(){
        return downVotes;
    }
    public Member getMember() {
        return member;
    }
}
