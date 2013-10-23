package com.skyline.rest.about;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;

/**
 * A factory for the information used in aboutAuthors. The reason we have this
 * factory is because of the information will be reachable through a bean.
 *
 * @author Gabriel
 */
@Singleton
public class Authors implements Serializable {

    private final List<Author> authorRange = authorFactory();

    private Authors() {
    }

    /**
     * This data is considered a static part of the application. Could have been
     * loaded from file but that would just be an unnecessary process since these
     * Strings always are the same between patches.
     * Add information about yourself in this method to be mentioned in the
     * about section if you have contributed to the project Skyline
     * @return List of contributors
     */
    private List<Author> authorFactory() {
        List<Author> l = new ArrayList();
        String aST = "this is the short version about Anton";
        String aLT = "this is the long version about Anton. "+
                "As you can see the information has changed after pressing the 'Longer text'-button.";
        String gST = "this is the short version about Gabriel";
        String gLT = "this is the long version about Gabriel. "+
                "As you can see the information has changed after pressing the 'Longer text'-button.";
        String mST = "this is the short version about Mike";
        String mLT = "this is the long version about Mike. "+
                "As you can see the information has changed after pressing the 'Longer text'-button.";
        String tST = "this is the short version about Tomas";
        String tLT = "this is the long version about Tomas. "+
                "As you can see the information has changed after pressing the 'Longer text'-button.";
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

    public List<Author> getAuthors() {
        return authorRange;
    }
}
