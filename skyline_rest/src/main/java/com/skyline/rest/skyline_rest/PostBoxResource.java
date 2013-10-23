package com.skyline.rest.skyline_rest;

import com.skyline.model.core.IMemberRegistry;
import com.skyline.model.core.IPostContainer;
import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import com.skyline.model.core.VotingSystem;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Wrapping the blog-model and handling calls towards it.
 *
 * @author AntonPalmqvist
 */
@Path("posts")
public class PostBoxResource {

    private IPostContainer postBox = Blog.INSTANCE.getPostContainer();
    private IMemberRegistry memberRegistry = Blog.INSTANCE.getMembersRegistry();
    // Helper class used to build URI's. Injected by container
    
    /**
     * Method getting all posts.
     * 
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        List postList = postBox.getRangeByVotes(0, postBox.getCount());
        List<PostProxy> wrappedPostList = new ArrayList();
        for (int i = 0; i < postList.size(); i++) {
            Post p = (Post) postList.get(i);
            PostProxy pP = new PostProxy(p);
            wrappedPostList.add(pP);
        }

        GenericEntity<List<PostProxy>> ge = new GenericEntity<List<PostProxy>>(wrappedPostList) {
        };
        return Response.ok(ge).build();
    }

    /**
     * Method getting a specific post by its id.
     * 
     * @param id
     * @return 
     */
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        try {
            Post p = postBox.find(id);
            PostProxy pp = new PostProxy(p);
            return Response.ok(pp).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Method to add a new post. To make the use of this method efficient 
     * it is returning the added post, which means that the javascript getting
     * the new post values can render it instantly.
     * This is the reason why this method is returning a 200 OK-message 
     * instead of a 201 Created-message.
     * 
     * @param idMember Long to find the member who wrote the post
     * @param title String
     * @param bodyText String the bodyText
     * @param postVideo String url to videoLink
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON})
    public Response addPost(
            @FormParam("title") String title,
            @FormParam("bodyText") String bodyText,
            @FormParam("postPicture") String postPicture,
            @FormParam("postVideo") String postVideo,
            @Context HttpServletRequest req) {
        
        Member author = (Member) req.getSession().getAttribute("USER");
        String postPic = (postPicture!=null) ? postPicture : "";
        String postVid = (postVideo!=null) ? postVideo : "No video";
        Post p = new Post(title, bodyText, postPic, postVid);
        try {
            postBox.add(p);
            author.addPost(p);
            memberRegistry.update(author);

            PostProxy pp = new PostProxy(p);
            return Response.ok(pp).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Method incrementing or decrementing the vote numbers of a post or 
     * a comment.
     * 
     * @param postId
     * @param positive
     * @return 
     */
    @POST
    @Path("vote")
    public Response vote(@QueryParam("postId") Long postId, @QueryParam("positive") boolean positive) {
        try {
            Post post = postBox.find(postId);
            VotingSystem votes = post.getVotes();
            if (positive) {
                votes.addUpVote();
            } else {
                votes.addDownVote();
            }
            postBox.update(post);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Method deleting a post by its id.
     * 
     * @param Id
     * @return 
     */
    @DELETE
    @Path("{Id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("Id") Long Id) {
        try {
            postBox.remove(Id);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Method updating a post's values.
     * 
     * @param req
     * @param id
     * @param title
     * @param bodyText
     * @param postVideo
     * @return 
     */
    @PUT
    @Path("{Id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(@Context HttpServletRequest req, @PathParam("Id") Long id,
            @FormParam("title") String title,
            @FormParam("bodyText") String bodyText,
            @FormParam("PostPicture") String postPicture,
            @FormParam("postVideo") String postVideo) {
        Member member = (Member) req.getSession().getAttribute("USER");
        if(member==null){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        String postPic = (postPicture!=null) ? postPicture : "";
        String postVid = (postVideo!=null) ? postVideo : "No video";
        Post tempPost = postBox.find(id);
        Member postAuthor = postBox.getAuthor(tempPost);
        VotingSystem voteSys = tempPost.getVotes();
        try {
            if(member.getId()==postAuthor.getId()){
                postBox.update(new Post(id, tempPost.getDate(), title, bodyText, postPic, postVid, voteSys));
                return Response.ok().build();
            }
            else{
                return Response.ok().build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Method returning a chosen range of posts.
     * 
     * @param first
     * @param last
     * @return 
     */
    @GET
    @Path("range")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRange(@QueryParam("first") int first,
            @QueryParam("last") int last) {
        List<Post> tmpPosts = postBox.getRange(first, last);
        List<PostProxy> postList = new ArrayList<PostProxy>();
        for (Post post : tmpPosts) {
            postList.add(new PostProxy(post));
        }
        GenericEntity<List<PostProxy>> ge = new GenericEntity<List<PostProxy>>(postList) {
        };
        return Response.ok(ge).build();
    }

    /**
     * Method returning the number of posts on the wall.
     * 
     * @return 
     */
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCount() {
        Integer i = new Integer(postBox.getCount());
        PrimitiveJSONWrapper<Integer> pj = new PrimitiveJSONWrapper<Integer>(i);
        return Response.ok(pj).build();
    }
    /**
     * 
     * @param memberId id of a member
     * @return GenericEntity<List<PostProxy>> list of all post a member have done
     */
    @GET
    @Path("member/{memberId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getPostsOfMemberByVotes(@PathParam("memberId") Long memberId) {
        Member member = memberRegistry.find(memberId);
        
        List<Post> posts = postBox.getPostsOfMemberByVotes(member, 0, postBox.getCount());
        List<PostProxy> postList = new ArrayList<PostProxy>();
        for (Post post : posts) {
            postList.add(new PostProxy(post));
        }
        GenericEntity<List<PostProxy>> ge = new GenericEntity<List<PostProxy>>(postList) {
        };
        return Response.ok(ge).build();
    }
    
    @GET
    @Path("member")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getPostsOfUserByVotes(@Context HttpServletRequest req) {
        Member session = (Member) req.getSession().getAttribute("USER");
        Member member = memberRegistry.find(session.getId());
        
        List<Post> posts = postBox.getPostsOfMemberByVotes(member, 0, postBox.getCount());
        List<PostProxy> postList = new ArrayList<PostProxy>();
        for (Post post : posts) {
            postList.add(new PostProxy(post));
        }
        GenericEntity<List<PostProxy>> ge = new GenericEntity<List<PostProxy>>(postList) {
        };
        return Response.ok(ge).build();
    }
    
    /**
     * Method returning all posts made by a chosen user.
     * 
     * @param postId
     * @return 
     */
    @GET
    @Path("author/{postId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAuthor(@PathParam("postId") Long postId) {
        try {
            Post post = postBox.find(postId);
            MemberProxy member = new MemberProxy(postBox.getAuthor(post));
            return Response.ok(member).build();
        } catch (IllegalArgumentException ie) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Method returning all posts made by the logged in user's favorite users.
     * 
     * @param req
     * @return 
     */
    @GET
    @Path("favorites")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getPostsForFavoritesOfMember(@Context HttpServletRequest req) {
        Member member = (Member) req.getSession().getAttribute("USER");
        if (member == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        List<Post> favoritesPosts = postBox.getPostsOfMemberFavorites(member, 0, 50);
        List<PostProxy> proxies = new ArrayList<PostProxy>();
        for (Post post : favoritesPosts) {
            proxies.add(new PostProxy(post));
        }
        return Response.ok(proxies).build();
    }
}
