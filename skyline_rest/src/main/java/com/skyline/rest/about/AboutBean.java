package com.skyline.rest.about;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Main bean in about authors section. makes the objects accessible. 
 * 
 * @author Gabriel
 */
@Named("about")
@SessionScoped
public class AboutBean implements Serializable{
    
    private List<Author> authorRange;
    
    @Inject
    private Authors source;

    /**
     * Will run after the list of Authors has been constructed.
     */
    @PostConstruct
    public void post() {
        authorRange = source.getAuthors();
    }
    
    public List<Author> getAuthorRange(){
        return authorRange;
        
    }
}
