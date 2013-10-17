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
    private int index;
    private String name;
    private String shortText;
    private String longText;
    
    public Author(int id, String name, String sT, String lT){
        this.index = id;
        this.name = name;
        this.shortText = sT;
        this.longText = lT;
    }

    public int getIndex() {
        return index;
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
