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
        BodyText b2 = new BodyText("ooooh yeeeah");
        BodyText b3 = new BodyText("Annnoa did this");
        BodyText b4 = new BodyText("Antsiee did this");
        
        Member tomas = new Member("Tomas");
        Member anno = new Member("Anno");
        Member anton = new Member("Anton");
        Member krabban = new Member("Krabban");
        
        tomas.addFavoriteMembers(anno);
        tomas.addFavoriteMembers(krabban);
        tomas.addFavoriteMembers(anton);
        anton.addFavoriteMembers(krabban);
        
        blog.getPostContainer().add(new Post(tomas, "First Post", b, null, null));
        blog.getPostContainer().add(new Post(tomas, "Second Post", b2, null, null));
        blog.getPostContainer().add(new Post(anno, "Third Post", b2, null, null));
        blog.getPostContainer().add(new Post(anton, "Fouth Post", b3, null, null));
        
        blog.getMemberContainer().add(tomas);
        blog.getMemberContainer().add(anno);
        blog.getMemberContainer().add(krabban);
        blog.getMemberContainer().add(anton);
        
        
    }

}
