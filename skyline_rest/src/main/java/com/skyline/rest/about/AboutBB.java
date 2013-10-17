/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.about;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gabriel
 */
@Named("about")
@SessionScoped
public class AboutBB implements Serializable{
    
    private List<Author> authorRange;
    
    @Inject
    private Authors source;

    @PostConstruct
    public void post() {
        // We know all injection are done so shop not null (hopefully)
        Logger.getAnonymousLogger().log(Level.INFO, "post");
        authorRange = source.getAuthors();
    }
    
    public List<Author> getAuthorRange(){
        Logger.getAnonymousLogger().log(Level.INFO, "getArange");
        return authorRange;
        
    }
    
    public String navigate(String path) {
        Logger.getAnonymousLogger().log(Level.INFO, "navigate");
        return path;
    }
}
