package com.skyline.model.core;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Post p = new Post("titelText", new BodyText("bodytext"), null, null);
        System.out.println( "Post " + p.getDate());
    }
}
