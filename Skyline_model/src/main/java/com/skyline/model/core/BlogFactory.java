/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

/**
 *
 * @author tomassellden
 */
public class BlogFactory {

    
    private BlogFactory() {
    }
    
    public static IBlog getBlog(boolean testdata) {
        Blog blog = new Blog();
        if (testdata) {
            addTestData(blog);
        }
        return blog;
    }
    
    private static void addTestData(Blog blog) {
        
        BodyText b = new BodyText("I am awesome!");
        
        Member tomas = new Member("Tomas");
        Member anno = new Member("Anno");
        Member anton = new Member("Anton");
        Member krabban = new Member("krabban");
        
        tomas.addFavoriteMembers(anno);
        tomas.addFavoriteMembers(krabban);
        
        Post post = new Post("First post", b, null, null);
        tomas.addPostToMember(post);
        
        blog.getPostContainer().add(post);
        blog.getPostContainer().add(new Post("Second Post", b, null, null));
        
        blog.getMemberContainer().add(tomas);
        blog.getMemberContainer().add(anno);
        blog.getMemberContainer().add(krabban);
        blog.getMemberContainer().add(anton);
        
        
    }

}
