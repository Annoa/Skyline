package com.skyline.rest.skyline_rest;

import com.skyline.model.core.Post;
import com.skyline.model.utils.IDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    // Helper class used to build URI's. Injected by container
    @Context
    private UriInfo uriInfo;
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll(){
        List postList = postBox.getRange(postBox.getCount());
        List<PostProxy> wrappedPostList = new ArrayList();
        for(int i = 0; i<postList.size(); i++){
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
    public Response find(@PathParam("id") Long id){
        log.log(Level.INFO, "Find" + id);
        try {
            Post p = postBox.find(id);
            PostProxy pp = new PostProxy(p);
            return Response.ok(pp).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

