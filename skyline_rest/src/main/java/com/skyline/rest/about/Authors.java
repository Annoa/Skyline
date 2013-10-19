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
     * Add information about yourself in this method to be mentioned in the
     * about section if you have contributed to the project Skyline
     *
     * @return List of contributors
     */
    private List<Author> authorFactory() {
        List<Author> l = new ArrayList();
        String aST = "this is the short version about Anton";
        String aLT = "this is the long version about Anton. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pretium diam eget massa feugiat, a blandit velit rhoncus. Quisque tempus massa at magna suscipit, in mollis erat hendrerit. Fusce tincidunt ut tellus non sagittis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Sed id fringilla magna, quis blandit felis. Fusce libero quam, posuere ac justo nec, malesuada tincidunt orci. Morbi commodo sed urna vel vehicula.";
        String gST = "this is the short version about Gabriel";
        String gLT = "this is the long version about Gabriel. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pretium diam eget massa feugiat, a blandit velit rhoncus. Quisque tempus massa at magna suscipit, in mollis erat hendrerit. Fusce tincidunt ut tellus non sagittis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Sed id fringilla magna, quis blandit felis. Fusce libero quam, posuere ac justo nec, malesuada tincidunt orci. Morbi commodo sed urna vel vehicula.";
        String mST = "this is the short version about Mike";
        String mLT = "this is the long version about Mike. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pretium diam eget massa feugiat, a blandit velit rhoncus. Quisque tempus massa at magna suscipit, in mollis erat hendrerit. Fusce tincidunt ut tellus non sagittis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Sed id fringilla magna, quis blandit felis. Fusce libero quam, posuere ac justo nec, malesuada tincidunt orci. Morbi commodo sed urna vel vehicula.";
        String tST = "this is the short version about Tomas";
        String tLT = "this is the long version about Tomas. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pretium diam eget massa feugiat, a blandit velit rhoncus. Quisque tempus massa at magna suscipit, in mollis erat hendrerit. Fusce tincidunt ut tellus non sagittis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Sed id fringilla magna, quis blandit felis. Fusce libero quam, posuere ac justo nec, malesuada tincidunt orci. Morbi commodo sed urna vel vehicula.";
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
