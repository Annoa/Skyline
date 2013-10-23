package com.skyline.model.core;

import com.skyline.model.utils.IDAO;
import java.util.List;

/**
 * public interface for the PostContainer
 * @author tomassellden and Anno
 */
public interface IPostContainer extends IDAO<Post, Long> {
    
    public Member getAuthor(Post post);
    
    public List<Post> getPostsOfMemberByVotes(Member member, int start, int amount);
    
    public List<Post> getRangeByVotes(int start, int amount);
    
    public List<Post> getRangeByTime(int start, int amount);
    
    public List<Post> getPostsOfMemberFavorites(Member member, int start, int amount);
}
