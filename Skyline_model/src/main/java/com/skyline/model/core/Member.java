package com.skyline.model.core;

import com.skyline.model.utils.AbstractEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * A class representing a member
 *
 * @author tomassellden and Anno (JPA tags)
 */
@Entity
@NamedQuery(name = "Member.search", query = "SELECT m FROM Member m WHERE m.name LIKE :string")
public class Member extends AbstractEntity implements Serializable {

    private String name;
    @NotNull
    private String password;
    @Temporal(TemporalType.DATE)
    private Date signUpDate;
    /*
     * Using a bi-directional ManyToMany-relationship here since it is the best 
     * solution since the entity is referencing another entity of the same type
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "FAV_MEM",
            joinColumns = {
        @JoinColumn(name = "MEM_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "FAVMEM_ID", referencedColumnName = "ID")})
    private Set<Member> favoriteMembers;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favoriteMembers")
    private Set<Member> favoritedByMembers;
    @OneToMany(orphanRemoval = true, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER")
    private Set<Post> posts;
    @OneToMany(orphanRemoval = true, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER")
    private Set<Comment> comments;

    public Member() {
    }

    /**
     * Used to create a new member in database
     */
    public Member(String name, String password) {
        this.password = password;
        this.signUpDate = new Date();
        this.name = name;
        this.posts = new HashSet<Post>();
        this.comments = new HashSet<Comment>();
        this.favoriteMembers = new HashSet<Member>();
        this.favoritedByMembers = new HashSet<Member>();
    }

    /**
     *
     * Used to update a member in database
     */
    public Member(Long id, Date date, String name, Set<Post> posts,
            Set<Comment> comments, Set<Member> favoriteMembers) {
        super(id);
        this.signUpDate = date;
        this.name = name;
        this.posts = posts;
        this.comments = comments;
        this.favoriteMembers = favoriteMembers;
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

    public boolean addComment(Comment comment) {
        return this.comments.add(comment);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
    }

    public Set<Member> getFavoriteMembers() {
        return this.favoriteMembers;
    }

    public boolean addFavoriteMember(Member member) {
        member.favoritedByMembers.add(this);
        return this.favoriteMembers.add(member);
    }

    public boolean removeFavoriteMember(Member member) {
        member.favoritedByMembers.remove(this);
        return this.favoriteMembers.remove(member);
    }

    public Set<Member> getFavoritedByMembers() {
        return favoritedByMembers;
    }

    @PreRemove
    private void removeFavoritesFromMembers() {
        for (Member member : favoritedByMembers) {
            member.removeFavoriteMember(this);
        }
    }

    @Override
    public String toString() {
        return "Member = { id = " + getId() + " name = " + name + "}";
    }
}
