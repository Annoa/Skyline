package com.skyline.rest.about;

import java.io.Serializable;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Let's the specific information of an user get interpreted in the about
 * section. ' Is at the moment very simple in design.
 *
 * @author Gabriel
 */
@Named("viewAuthor")
@ConversationScoped
public class ViewAuthorBB implements Serializable {

    private boolean isShort;
    private String shortT;
    private String longT;
    private String name;
    @Inject
    private Authors source;
    @Inject
    private Conversation conversation;

    /**
     * Assigning an author to be read about. Has short version as viewable
     * first.
     *
     * @param index which user that will be shown
     */
    public void view(String index) {

        if (conversation.isTransient()) {
            conversation.begin();
        }
        Author a = source.getAuthors().get(Integer.valueOf(index));
        name = a.getName();
        shortT = a.getShortText();
        longT = a.getLongText();
        isShort = true;
    }

    @PreDestroy
    public void destroy() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    /**
     * Switches between long and short version in the .xhtml-file
     */
    public void setIsShort() {
        isShort = !isShort;
    }

    public boolean getIsShort() {
        return isShort;
    }

    public String getShortT() {
        return shortT;
    }

    public String getLongT() {
        return longT;
    }

    public String getName() {
        return name;
    }
}
