package com.skyline.rest.skyline_rest;

import com.skyline.model.core.BodyText;
import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import com.skyline.model.core.PostPicture;
import com.skyline.model.core.PostVideo;
import com.skyline.model.utils.IDAO;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Wrapping the blog-model and handling calls towards it.
 *
 * @author Epoxy
 */
@Path("posts")
public class PostBoxResource {

    private final static Logger log = Logger.getAnonymousLogger();
    private IDAO<Post, Long> postBox = Blog.INSTANCE.getPostContainer();
    private IDAO<Member, Long> memberBox = Blog.INSTANCE.getMembersRegistry();
    // Helper class used to build URI's. Injected by container
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        List postList = postBox.getRange(0, postBox.getCount());
        List<PostProxy> wrappedPostList = new ArrayList();
        for (int i = 0; i < postList.size(); i++) {
            Post p = (Post) postList.get(i);
            PostProxy pP = new PostProxy(p);
            wrappedPostList.add(pP);
        }

        GenericEntity<List<PostProxy>> ge = new GenericEntity<List<PostProxy>>(wrappedPostList) {
        };
        //log.log(Level.INFO, "getAll", new Object[]{null, null});
//        return null;
        return Response.ok(ge).build();
    }

    //TODO
    //Byta ut ID mot annan unik identifierare (typ sträng av membernamn+datum)
    //Risk att det svämmar över av IDn annars
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        log.log(Level.INFO, "Find" + id);
        try {
            Post p = postBox.find(id);
            PostProxy pp = new PostProxy(p);
            return Response.ok(pp).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     *
     * @param idMember Long to find the member who wrote the post
     * @param title String
     * @param bt String the bodyText
     * @param pv String url to videoLink
     * @return
     */
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
}
