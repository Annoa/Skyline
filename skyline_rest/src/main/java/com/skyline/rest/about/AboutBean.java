package com.skyline.rest.about;

import java.io.Serializable;
import java.util.List;
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
public class AboutBean implements Serializable{
    
    private List<Author> authorRange;
    
    @Inject
    private Authors source;

    @PostConstruct
    public void post() {
        authorRange = source.getAuthors();
    }
    
    public List<Author> getAuthorRange(){
        return authorRange;
        
    }
}
