/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Gabriel
 */
public class VotingSystem implements Comparable<Object>, Serializable {
    
    private int upVote;
    private int downVote;

    public VotingSystem() {
        this(0, 0);
    }

    public VotingSystem(int up, int down) {
        this.upVote = up;
        this.downVote = down;
    }

    public int getUpVote() {
        return upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public int getValue() {
        return upVote - downVote;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof VotingSystem) {
            VotingSystem vS = (VotingSystem) o;
            return this.getValue() == vS.getValue();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Votes = \n{ upVotes = " + upVote
                + " \ndownVotes = " + downVote
                + "\nvalue = " + getValue();
    }

    /**
     *
     * @return 1 if self is greater
     */
    public int compareTo(Object o) {
        if (o instanceof VotingSystem) {
            if (this.equals(o)) {
                return 0;
            }
            VotingSystem vs = (VotingSystem) o;
            if (this.getValue() > vs.getValue()) {
                return 1;
            }
        }
        return -1;
    }
}
