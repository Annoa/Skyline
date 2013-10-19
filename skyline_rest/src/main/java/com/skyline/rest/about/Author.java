package com.skyline.rest.about;

/**
 * Author object is used in the About page as information holders. 
 * What the author has contributed, etc.
 * 
 * @author Gabriel
 */
public class Author {
    private int index;
    private String name;
    private String shortText;
    private String longText;
    
    /**
     * 
     * @param sT Short Text: A summary of what the author has written.
     * @param lT Long Text: A more extensive summary of what the author has written.
     */
    public Author(int index, String name, String sT, String lT){
        this.index = index;
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
