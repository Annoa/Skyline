/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.skyline_rest;

import com.skyline.model.core.Comment;
import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel
 */
@XmlRootElement(name = "comment")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CommentProxy {
    
    private Comment comment;
    
    public CommentProxy(){        
    }
    
    public CommentProxy(Comment comment){
        this.comment=comment;
    }
    
    @XmlElement(required = true)
    public Long getId(){
        return comment.getId();
    }
    
    @XmlElement(required = true)
    public Post getPost() {
        return comment.getPost();
    }
    
    @XmlElement(required = true)
    public Date getCommentDate(){
        return comment.getCommentDate();
    }
    
    @XmlElement(required = true)
    public Member getAuthor() {
        return comment.getAuthor();
    }

    //TODO: default value?
    @XmlElement(required = false)
    public Comment getParentComment() {
        return comment.getParentComment();
    }
    
    @XmlElement(name = "commentText")
    public String getCommentText(){
        return comment.getCommentText();
    }

    @XmlElement(name = "upVotes")
    public int getUpVotes(){
        return comment.getVotes().getUpVote();
    }
    
    @XmlElement(name = "downVotes")
    public int getDownVotes(){
        return comment.getVotes().getDownVote();
    }
}
