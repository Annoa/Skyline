/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.about;

/**
 *
 * @author Gabriel
 */
public class Author {
    private int id;
    private String name;
    private String shortText;
    private String longText;
    
    public Author(int id, String name, String sT, String lT){
        this.id = id;
        this.name = name;
        this.shortText = sT;
        this.longText = lT;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortText() {
        return shortText;
    }

    public String getLongText() {
        return longText;
    }
    
    
    
}
