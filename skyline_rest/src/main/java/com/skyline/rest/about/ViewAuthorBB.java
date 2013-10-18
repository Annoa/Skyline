/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.about;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author User
 */
@Named("viewAuthor")
@ConversationScoped
public class ViewAuthorBB implements Serializable {

    
    private boolean isShort;
    
    
    private Author author;
    
    @Inject
    private Authors source;
    
    @Inject
    private Conversation conversation;
    
    public void view(String index){
        if(conversation.isTransient()){
            conversation.begin();
        }
        author = source.getAuthors().get(Integer.valueOf(index));
        isShort=true;
    }
    
    
    @PreDestroy
    public void destroy() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    public void switchBT(){
        isShort = !isShort;
    }
    
    public boolean getIsShort(){
        return isShort;
    }

    public Author getAuthor() {
        return author;
    }
    
}
