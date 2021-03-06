package com.skyline.rest.skyline_rest;

import com.skyline.model.core.Comment;
import java.util.Date;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Need this because translation from XML to JSON
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
    public Date getCommentDate(){
        return comment.getCommentDate();
    }
    

    @XmlElement(required = false)
    public Set<Comment> getChildComments() {
        return comment.getChildComments();
    }
    
    
    @XmlElement(required = true, name = "commentText")
    public String getCommentText(){
        return comment.getCommentText();
    }

    @XmlElement(required = true, name = "upVotes")
    public int getUpVotes(){
        return comment.getVotes().getUpVote();
    }
    
    @XmlElement(required = true, name = "downVotes")
    public int getDownVotes(){
        return comment.getVotes().getDownVote();
    }
}
