package com.skyline.rest.skyline_rest;

import com.skyline.model.core.IMemberRegistry;
import com.skyline.model.core.IPostContainer;
import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import com.skyline.model.core.VotingSystem;
import com.skyline.model.utils.IDAO;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Epoxy
 */
@Path("posts")
public class PostBoxResource {

    private final static Logger log = Logger.getAnonymousLogger();
    private IPostContainer postBox = Blog.INSTANCE.getPostContainer();
    private IMemberRegistry memberRegistry = Blog.INSTANCE.getMembersRegistry();
    // Helper class used to build URI's. Injected by container
    @Context
    private UriInfo uriInfo;
    
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
            //@FormParam("PostPicture") byte[] postPicture,
            @FormParam("postVideo") String postVideo,
//            @FormParam("memberId") Long memberId) {
            @Context HttpServletRequest req) {
//        Member member = (Member) req.getAttribute("USER");
//        log.log(Level.INFO, member.getName() + ", id: " + member.getId());
       /* byte[] postPic;
        if (postPicture!=null) {
            postPic = postPicture;
        } else {
            postPic = new byte[0];
        }*/
        
        Member session = (Member) req.getSession().getAttribute("USER");
        Member author = memberRegistry.find(session.getId());
        
        String postVid = (postVideo!=null) ? postVideo : "No video";
        Post p = new Post(title, bodyText, null, postVid);
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
            log.log(Level.WARNING, e.getLocalizedMessage());
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
            @FormParam("title") String title,
            @FormParam("bodyText") String bodyText,
           // @FormParam("PostPicture") byte[] postPicture,
            @FormParam("postVideo") String postVideo) {
//        Member mWhoWroteThePost = memberBox.find(idMember);
        /*byte[] postPic;
        if (postPicture!=null) {
            postPic = postPicture;
        } else {
            postPic = new byte[0];
        }*/
        String postVid;
        if (postVideo!=null) {
            postVid = postVideo;
        } else {
            postVid = "No video";
        }
        Post tempPost = postBox.find(id);
        VotingSystem voteSys = tempPost.getVotes();
        try {
            
            postBox.update(new Post(id, tempPost.getDate(), title, bodyText, null, postVid, voteSys));
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
        PrimitiveJSONWrapper<Integer> pj = new PrimitiveJSONWrapper<Integer>(i);
        return Response.ok(pj).build();
    }
    /**
     * 
     * @param memberId id of a member
     * @return GenericEntity<List<PostProxy>> list of all post a member have done
     */
//    //TODO fixa så att man vet när en siffra betyder member och när den betyder post
//    @GET
//    @Path("postByMember")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Response getPostByMember(@QueryParam("memberId") Long memberId) {
//        Member m = memberBox.find(memberId);
//        //IPostRegistry postRegistry = new PostRegistry();
//        List<Post> tmpPost = postBox.getRange(0, postBox.getCount());
//        List<PostProxy> postList = new ArrayList<PostProxy>();
//        for (Post p : tmpPost) {
//            System.out.println(" " + p.toString());
//            System.out.println(" " + p.getMember().getId() +  " = " + memberId);
//            if (p.getMember().getId().equals(memberId)) {
//                System.out.println(" " + p.getMember().getId() +  " = " + memberId);
//                postList.add(new PostProxy(p));
//            }
//        }
//        
//        GenericEntity<List<PostProxy>> ge = new GenericEntity<List<PostProxy>>(postList) {
//        };
//        return Response.ok(ge).build();
//    }
    
    @GET
    @Path("author/{postId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAuthor(@PathParam("postId") Long postId) {
        try {
            log.log(Level.INFO, "Inside getAuthor");
            Post post = postBox.find(postId);
            log.log(Level.INFO, "After post");
            MemberProxy member = new MemberProxy(postBox.getAuthor(post));
            return Response.ok(member).build();
        } catch (IllegalArgumentException ie) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
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
