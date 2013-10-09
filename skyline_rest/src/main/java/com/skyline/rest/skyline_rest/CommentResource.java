/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.skyline_rest;

import com.skyline.model.core.Comment;
import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import com.skyline.model.core.VotingSystem;
import com.skyline.model.utils.IDAO;
import com.sun.jndi.toolkit.url.Uri;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Gabriel
 */
@Path("comments")
public class CommentResource {

    private final static Logger log = Logger.getAnonymousLogger();
    private IDAO<Comment, Long> comments = Blog.INSTANCE.getCommentContainer();
    private IDAO<Member, Long> members = Blog.INSTANCE.getMembersRegistry();
    private IDAO<Post, Long> posts = Blog.INSTANCE.getPostContainer();
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<Comment> commentList = comments.getRange(0, comments.getCount());
        List<CommentProxy> wrappedComments = new ArrayList();
        for (Comment c : commentList) {
            wrappedComments.add(new CommentProxy(c));
        }
        GenericEntity<List<CommentProxy>> ge =
                new GenericEntity<List<CommentProxy>>(wrappedComments) {
        };

        return Response.ok(ge).build();
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        try {
            CommentProxy cp = new CommentProxy(comments.find(id));
            return Response.ok(cp).build();
        } catch (IllegalArgumentException ie) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addComment(@FormParam("pId") Long postId,
            @FormParam("aId") Long authorId,
            @FormParam("pcId") Long parentCommentId,
            @FormParam("text") String text) {
        Post p = posts.find(postId);
        Member m = members.find(authorId);
        Comment pC = comments.find(parentCommentId);
        comments.add(new Comment(p, pC, text, m));
        return Response.ok().build();

    }

    @DELETE
    @Path("{Id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("Id") Long commentId) {
        try {
            comments.remove(commentId);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{Id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(@PathParam("Id") Long id,
            @FormParam("postId") Long postId,
            @FormParam("parentId") Long parentId,
            @FormParam("memberId") Long memberId,
            @FormParam("date") Long date,
            @FormParam("upVote") int upVote,
            @FormParam("downVote") int downVote,
            @FormParam("text") String text) {
        Date d = new Date(date);
        Member postAuthor = members.find(memberId);
        Post p = posts.find(postId);
        Comment cp = comments.find(parentId);
        try {
            comments.update(new Comment(id, p, cp, text, d, postAuthor, new VotingSystem(upVote, downVote)));
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    /*
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

     @GET
     @Path("count")
     @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public Response getCount() {
     Integer i = new Integer(postBox.getCount());
     PrimitiveJSONWrapper<Integer> pj= new PrimitiveJSONWrapper<Integer>(i);
     return Response.ok(pj).build();
     }
     */
}
