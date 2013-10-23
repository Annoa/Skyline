package com.skyline.model.core;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Gabriel
 */
@Embeddable
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
    
    public void addUpVote() {
        upVote += 1;
    }

    public void addDownVote() {
        downVote += 1;
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
     * @return 1 if self's vote difference is greater, 0 if equals and -1 if lesser
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
