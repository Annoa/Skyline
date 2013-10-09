/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.skyline_rest;

import com.skyline.model.core.Comment;
import com.skyline.model.core.Member;
import com.skyline.model.utils.IDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
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
    public Response addComment(@FormParam("id") Long id,
            @FormParam("authorId") Long authorId,
            @FormParam("commentText") String text /*@FormParam("date") and more */) {
        //  Member author =  
        throw new UnsupportedOperationException("Jensa orkade "
                + "inte skriva klart det här");
        //return Response.ok().build();
    }
    /*
     @POST
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public Response addPost(@FormParam("id") Long idMember,
     @FormParam("title") String title,
     @FormParam("BodyText") String bt,
     @FormParam("PostVideo") String pv) {
     //@FormParam("PostPicture") byte[] char pp,
     Member mWhoWroteThePost = memberBox.find(idMember);
     BodyText btt = new BodyText(bt);
     PostVideo pvv;
     if (!pv.equals("null")) {
     pvv = new PostVideo(pv);
     } else {
     pvv = null;
     }
     Post p = new Post(mWhoWroteThePost, title, btt, null, pvv);
     try {
     postBox.add(p);
     URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf("title")).build(p);
     return Response.created(uri).build();
     } catch (IllegalArgumentException e) {
     return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
     }
     }

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

     @PUT
     @Path("{Id}")
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public Response update(@PathParam("Id") Long id,
     @FormParam("memberId") Long idMember,
     @FormParam("title") String title,
     @FormParam("BodyText") String bt,
     @FormParam("PostVideo") String pv) {
     //@FormParam("PostPicture") byte[] char pp,
     Member mWhoWroteThePost = memberBox.find(idMember);
     BodyText btt = new BodyText(bt);
     PostVideo pvv;
     if (!pv.equals("null")) {
     pvv = new PostVideo(pv);
     } else {
     pvv = null;
     }
     try {
     postBox.update(new Post(id, mWhoWroteThePost, title, btt, null, null, null));
     return Response.ok().build();
     } catch (IllegalArgumentException e) {
     return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
     }
     }

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
