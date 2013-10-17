/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.about;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;

/**
 *
 * @author Gabriel
 */
@Singleton
public class Authors implements Serializable{
  
    private final List<Author> authorRange = authorFactory();

    private Authors(){
        ;
    }
    
    private List<Author> authorFactory(){
        List<Author> l = new ArrayList();
        String aST = "this is the short version about Anton";
        String aLT = "this is the looong version about Anton";
        String gST = "this is the short version about Gabriel";
        String gLT = "this is the looong version about Gabriel";
        String mST = "this is the short version about Mike";
        String mLT = "this is the looong version about Mike";
        String tST = "this is the short version about Tomas";
        String tLT = "this is the looong version about Tomas";
        String aN = "Anton Palmqvist";
        String gN = "Gabriel Andersson";
        String mN = "Mike Phoohad";
        String tN = "Tomas Selldén";
        l.add(new Author(0, aN, aST, aLT));
        l.add(new Author(1, gN, gST, gLT));
        l.add(new Author(2, mN, mST, mLT));
        l.add(new Author(3, tN, tST, tLT));
        return l;
    }
    
    public List<Author> getAuthors(){
        return authorRange;
    }
    
}
