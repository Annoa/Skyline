/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

/**
 * Class containing a picture.
 * 
 * @author Epoxy
 */
public class PostPicture {
    private byte[] picture;
    PostPicture(byte[] picture){
        this.picture = picture;
    }
    public byte[] getPicture(){
        return picture;
    }
}
