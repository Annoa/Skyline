/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.skyline.model;

/**
 * A container for accessing posts of different kinds
 *
 * @author Anno
 */
public class PostContainer extends AbstractDAO
        <Post extends IEntity<Long>, Long> implements IPostContainer<Post 
        extends IEntity<Long>, Long>{
    
}
