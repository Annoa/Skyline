package com.skyline.rest.skyline_rest;

import com.skyline.model.core.Post;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Need this because translation from XML to JSON
 * 
 * @author tomassellden
 */
@XmlRootElement(name = "Post")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PostProxy {

    private Post post;

    protected PostProxy() {
    }

    public PostProxy(Post post) {
        this.post = post;
    }

    @XmlElement(required = true)
    public Long getId() {
        return post.getId();
    }

    @XmlElement(required = true)
    public String getTitle() {
        return post.getTitle();
    }

    @XmlElement(required = true)
    public Date getDate() {
        return post.getDate();
    }

    @XmlElement(name = "bodyText")
    public String getBodyText() {
        return post.getBodyText();
    }

    @XmlElement(name = "postPicture")
    public String getPostPicture() {
        return post.getPostPicture();
    }

    @XmlElement(name = "postVideo")
    public String getPostVideo() {
        return post.getPostVideo();
    }

    @XmlElement(name = "upVotes")
    public int getUpVotes() {
        return post.getVotes().getUpVote();
    }

    @XmlElement(name = "downVotes")
    public int getDownVotes() {
        return post.getVotes().getDownVote();
    }
}
