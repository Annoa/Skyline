package com.skyline.rest.skyline_rest;

import com.skyline.model.core.Comment;
import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import com.skyline.model.utils.IDAO;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Gabriel
 */
@Path("posts/comments")
public class CommentResource {

    private final static Logger log = Logger.getAnonymousLogger();
    private IDAO<Comment, Long> comments = Blog.INSTANCE.getCommentContainer();
    private IDAO<Member, Long> members = Blog.INSTANCE.getMembersRegistry();
    private IDAO<Post, Long> posts = Blog.INSTANCE.getPostContainer();
    private UriInfo uriInfo;

    //TODO: Is it FormParam or PathParam?
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllOnPost(@FormParam("postId") Long postId) {
        Post p = posts.find(postId);
        List<Comment> cList = p.getComments();
        List<CommentProxy> wrappedComments = new ArrayList();
        try {
            for (Comment c : cList) {
                wrappedComments.add(new CommentProxy(c));
            }
            GenericEntity<List<CommentProxy>> ge =
                    new GenericEntity<List<CommentProxy>>(wrappedComments) {
            };
            return Response.ok(ge).build();
        } catch (IllegalArgumentException ie) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
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
    public Response addComment(@QueryParam("pcId") Long parentCommentId,
            @FormParam("text") String text) {
        Comment pC = comments.find(parentCommentId);
        Comment c = new Comment(pC, text);
        comments.add(c);
        try {
            URI uri = uriInfo.getAbsolutePathBuilder().path(c.getId().toString()).build();
            return Response.ok(uri).build();
        } catch (IllegalArgumentException ie) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
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

    //TODO: If we implement votingsystem, add a method or add params. (method preferred)
    @PUT
    @Path("{Id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(@PathParam("Id") Long commentId,
            @FormParam("text") String text) { 
        Comment c = comments.find(commentId);
        if (c != null) {
            try {
                comments.update(new Comment(commentId, c.getChildComment(), 
                        text, c.getCommentDate(), c.getVotes()));
                return Response.ok().build();
            } catch (IllegalArgumentException e) {
            }
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("range")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRange(@QueryParam("first") int first,
            @QueryParam("last") int last) {
        List<Comment> tmpC = comments.getRange(first, last);
        List<CommentProxy> cP = new ArrayList<CommentProxy>();
        for (Comment c : tmpC) {
            cP.add(new CommentProxy(c));
        }
        GenericEntity<List<CommentProxy>> ge =
                new GenericEntity<List<CommentProxy>>(cP) {
        };
        return Response.ok(ge).build();
    }

    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCount() {
        int i = comments.getCount();
        PrimitiveJSONWrapper<Integer> pj = new PrimitiveJSONWrapper<Integer>(i);
        return Response.ok(pj).build();
    }

    /**
     * @deprecated no need to get all comments. Use getAllOnPost(postId)
     * instead.
     * @return all comments in Skyline
     */
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
}