/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import com.skyline.model.utils.AbstractEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A class representing a member
 *
 * @author tomassellden
 */
@Entity
public class Member extends AbstractEntity {

    private String name;
    @Temporal(TemporalType.DATE)
    private Date signUpDate;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="FAV_MEM",
            joinColumns={@JoinColumn(name="MEM_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="FAVMEM_ID", referencedColumnName="ID")})
    private Set<Member> favoriteMembers;
    @OneToMany (orphanRemoval=true, cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    @JoinColumn
    private Set<Post> posts;
    @OneToMany (orphanRemoval=true, cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    @JoinColumn
    private Set<Comment> comments;

    public Member() {
    }
    
    public Member(String name) {
        this.signUpDate = new Date();
        this.name = name;
        this.posts = new HashSet<Post>();
        this.comments = new HashSet<Comment>();
        this.favoriteMembers = new HashSet<Member>();
    }

    public Member(Long id, String name) {
        super(id);
        this.signUpDate = new Date();
        this.name = name;
        this.posts = new HashSet<Post>();
        this.comments = new HashSet<Comment>();
        this.favoriteMembers = new HashSet<Member>();
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return signUpDate;
    }
    
    public Set<Post> getPosts() {
        return posts;
    }
    
    public Set<Comment> getComments() {
        return comments;
    }
    
    public void addPost(Post post) {
        this.posts.add(post);
    }
    
    public void removePost(Post post) {
        this.posts.remove(post);
    }
    
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
    
    public void removeComment(Comment comment) {
        this.comments.remove(comment);
    }

    public Set<Member> getFavoriteMembers() {
        return favoriteMembers;
    }

    public void addFavoriteMembers(Member member) {
        favoriteMembers.add(member);
    }

    public String toString() {
        return "Member = { id = " + getId() + " name = " + name + "}";
    }
}
