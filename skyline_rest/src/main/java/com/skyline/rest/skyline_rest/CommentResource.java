package com.skyline.rest.skyline_rest;

import com.skyline.model.core.Comment;
import com.skyline.model.core.ICommentContainer;
import com.skyline.model.core.IMemberRegistry;
import com.skyline.model.core.IPostContainer;
import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import com.skyline.model.core.VotingSystem;
import com.skyline.model.utils.IDAO;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;

/**
 * Wrapping the blog-model and handling comment-related calls towards it.
 * 
 * @author Gabriel and Anno
 */
@Path("comments/post")
public class CommentResource {

    private ICommentContainer comments = BlogAccess.INSTANCE.getCommentContainer();
    private IMemberRegistry members = BlogAccess.INSTANCE.getMembersRegistry();
    private IPostContainer posts = BlogAccess.INSTANCE.getPostContainer();
    @Context
    private UriInfo uriInfo;

    @GET
    @Path("all")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllOnPost(@QueryParam("postId") Long postId) {
        Post p = posts.find(postId);
        List<Comment> cList = p.getComments();
        List<CommentProxy> wrappedComments = new ArrayList();
        try {
            for (Comment parent : cList) {
                wrappedComments.add(new CommentProxy(parent));
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
    @Path("{postId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRootComments(@PathParam("postId") Long postId) {
        Post p = posts.find(postId);
        List<Comment> rootList = new ArrayList<Comment>();
        rootList = comments.getRootCommentsForPost(p);
        List<CommentProxy> wrappedComments = new ArrayList<CommentProxy>();
        try {
            for (Object c : rootList) {
                wrappedComments.add(new CommentProxy((Comment) c));
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getChildComments(@QueryParam("id") Long commentId) {
        Comment parent = comments.find(commentId);
        List<Comment> childList;
        childList = comments.getChildComments(parent);
        List<CommentProxy> wrappedComments = new ArrayList<CommentProxy>();
        try {
            for (Comment c : childList) {
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
    @Path("comment/{id}")
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
    public Response addComment(
            @Context HttpServletRequest req,
            @FormParam("postId") Long postId,
            @FormParam("parentId") Long parentCommentId,
            @FormParam("text") String text) {
        Member session = (Member) req.getSession().getAttribute("USER");
        Member author = members.find(session.getId());
        Post post = posts.find(postId);
        Comment c = new Comment(text);
        comments.add(c);
        if (parentCommentId != -1) {
            Comment parent = comments.find(parentCommentId);
            parent.addChildComment(c);
            comments.update(parent);
        }
        post.addComment(c);
        posts.update(post);
        author.addComment(c);
        members.update(author);
        try {
            return Response.created(uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(c.getId())).build()).build();
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

    @PUT
    @Path("{Id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(@PathParam("Id") Long commentId,
            @FormParam("text") String text) {
        Comment c = comments.find(commentId);
        if (c != null) {
            try {
                comments.update(new Comment(commentId, c.getChildComments(),
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
    
    @POST
    @Path("vote")
    public Response vote(@QueryParam("commentId") Long commentId, @QueryParam("positive") boolean positive) {
        try {
            Comment comment = comments.find(commentId);
            VotingSystem votes = comment.getVotes();
            if (positive) {
                votes.addUpVote();
            } else {
                votes.addDownVote();
            }
            comments.update(comment);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("author/{commentId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAuthor(@PathParam("commentId") Long commentId) {
        try {
            Comment comment = comments.find(commentId);
            MemberProxy member = new MemberProxy(comments.getAuthor(comment));
            return Response.ok(member).build();
        } catch (IllegalArgumentException ie) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}