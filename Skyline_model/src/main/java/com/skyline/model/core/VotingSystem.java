/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.core;

/**
 *
 * @author User
 */
public class VotingSystem {
    
    private int upVote;
    private int downVote;
    
    VotingSystem(){
        this(0,0);
    }
    
    VotingSystem(int up, int down){
        this.upVote = up;
        this.downVote = down;
    }
    
    public int getUpVote(){
        return upVote;
    }
    
    public int getDownVote(){
        return downVote;
    }
    
    @Override
    public String toString(){
        return "Votes = \n{ upVotes = " + upVote 
                + " \ndownVotes = " + downVote 
                + "\nvalue = " + upVote+downVote;
    }
    
}
