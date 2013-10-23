/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.skyline_rest;

import com.skyline.model.core.IBlog;
import com.skyline.model.core.IMemberRegistry;
import com.skyline.model.core.Member;
import com.skyline.model.utils.IDAO;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
 *
 * @author tomassellden
 */
@Path("members")
public class MemberResource {

    private final static Logger log = Logger.getAnonymousLogger();
    private IMemberRegistry memberRegistry = BlogAccess.INSTANCE.getMembersRegistry();
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<Member> tmpList = memberRegistry.getRange(0, memberRegistry.getCount());
        List<MemberProxy> memberList = new ArrayList<MemberProxy>();
        for (Member m : tmpList) {
            memberList.add(new MemberProxy(m));
        }
        GenericEntity<List<MemberProxy>> ge = new GenericEntity<List<MemberProxy>>(memberList) {
        };
        return Response.ok(ge).build();
    }
    
    @GET
    @Path("names")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllNamesOnly() {
        List<Member> tmpList = memberRegistry.getRange(0, memberRegistry.getCount());
        List<String> memberList = new ArrayList<String>();
        for (Member m : tmpList) {
            memberList.add(m.getName());
        }
        GenericEntity<List<String>> ge = new GenericEntity<List<String>>(memberList) {
        };
        return Response.ok(ge).build();
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        try {
            Member m = memberRegistry.find(id);
            MemberProxy mp = new MemberProxy(m);
            return Response.ok(mp).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addMember(@FormParam("name") String name,
                              @FormParam("password") String password) {
        Member member = new Member(name, password);
        try {
            memberRegistry.add(member);
            MemberProxy proxy = new MemberProxy(memberRegistry.find(member.getId()));
            return Response.ok(proxy).build();
        } catch (IllegalArgumentException e) {
            log.log(Level.INFO, e.getLocalizedMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        }

    }

    @DELETE
    @Path("{Id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("Id") Long Id) {
        try {
            memberRegistry.remove(Id);
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
            @FormParam("name") String name) {
        try {
            Member m = memberRegistry.find(id);
            memberRegistry.update(new Member(id, m.getDate(), name, m.getPosts(), 
                    m.getComments(), m.getFavoriteMembers()));
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
        List<Member> tmpList = memberRegistry.getRange(first, last);
        List<MemberProxy> memberList = new ArrayList<MemberProxy>();
        for (Member m : tmpList) {
            memberList.add(new MemberProxy(m));
        }
        GenericEntity<List<MemberProxy>> ge = new GenericEntity<List<MemberProxy>>(memberList) {
        };
        return Response.ok(ge).build();
    }

    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCount() {
        Integer i = new Integer(memberRegistry.getCount());
        PrimitiveJSONWrapper<Integer> pj = new PrimitiveJSONWrapper<Integer>(i);
        return Response.ok(pj).build();
    }

    @GET
    @Path("favoriteMembers")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getFavoriteMembers(@QueryParam("memberId") Long memberId) {
        Member member = memberRegistry.find(memberId);
        Set<Member> tmpList = member.getFavoriteMembers();
        List<MemberProxy> favoriteMembers = new ArrayList<MemberProxy>();
        for (Member m : tmpList) {
            favoriteMembers.add(new MemberProxy(m));
        }
        GenericEntity<List<MemberProxy>> ge = new GenericEntity<List<MemberProxy>>(favoriteMembers) {
        };
        return Response.ok(ge).build();
    }

    @GET
    @Path("commonFavoriteMembers")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCommonFavorites(@QueryParam("memberId") Long memberId,
            @QueryParam("memberId2") long memberId2) {
        Member member1 = memberRegistry.find(memberId);
        Member member2 = memberRegistry.find(memberId2);
        Set<Member> tmpCommonFriends = member1.getFavoriteMembers();
        tmpCommonFriends.retainAll(member2.getFavoriteMembers());
        List<MemberProxy> commonFriends = new ArrayList<MemberProxy>();
        for (Member m : tmpCommonFriends) {
            commonFriends.add(new MemberProxy(m));
        }
        GenericEntity<List<MemberProxy>> ge = new GenericEntity<List<MemberProxy>>(commonFriends) {
        };
        return Response.ok(ge).build();
    }
    
    @GET
    @Path("search")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response search(@QueryParam("string") String searchString) {
        List<Member> resultList = memberRegistry.search(searchString);
        List<MemberProxy> proxyList = new ArrayList<MemberProxy>();
        for (Member m : resultList) {
            proxyList.add(new MemberProxy(m));
        }
        GenericEntity<List<MemberProxy>> ge = new GenericEntity<List<MemberProxy>>(proxyList) {};
        return Response.ok(ge).build();
    }
    
    @GET
    @Path("searchByName")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response searchByName(@QueryParam("string") String searchString) {
        List<Member> resultList = memberRegistry.search(searchString);
        List<String> proxyList = new ArrayList<String>();
        for (Member m : resultList) {
            proxyList.add(m.getName());
        }
        GenericEntity<List<String>> ge = new GenericEntity<List<String>>(proxyList) {};
        return Response.ok(ge).build();
    }
    
    @GET
    @Path("user")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUser(@Context HttpServletRequest req) {
        try {
            Member session = (Member) req.getSession().getAttribute("USER");
            Member user = memberRegistry.find(session.getId());
            MemberProxy proxy = new MemberProxy(user);
            return Response.ok(proxy).build();
        } catch (IllegalArgumentException ie) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("name/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUserByName(@PathParam("name") String memberName) {
        try {
            Member member = memberRegistry.getMember(memberName);
            MemberProxy proxy = new MemberProxy(member);
            return Response.ok(proxy).build();
        } catch (IllegalArgumentException ie) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GET
    @Path("isFavorite")
    public Response isfavoriteMember(@QueryParam("memberId") Long memberId,
            @Context HttpServletRequest req) {
        try{
            Member member = memberRegistry.find(memberId);
            Member session = (Member) req.getSession().getAttribute("USER");
            Member user = memberRegistry.find(session.getId());
            if( user.getFavoriteMembers().contains(member))
                return Response.ok("true").build();
            return Response.ok("false").build();
            
        }catch(IllegalArgumentException ie){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Path("favorite")
    public Response favoriteMember(@QueryParam("memberId") Long memberId,
            @Context HttpServletRequest req) {
        try {
            Member member = memberRegistry.find(memberId);
            Member session = (Member) req.getSession().getAttribute("USER");
            Member user = memberRegistry.find(session.getId());
            user.addFavoriteMember(member);
            memberRegistry.update(user);
            return Response.ok().build();
        } catch (IllegalArgumentException ie) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Path("unfavorite")
    public Response unFavoriteMember(@QueryParam("memberId") Long memberId,
            @Context HttpServletRequest req) {
        try {
            Member member = memberRegistry.find(memberId);
            Member session = (Member) req.getSession().getAttribute("USER");
            Member user = memberRegistry.find(session.getId());
            user.removeFavoriteMember(member);
            memberRegistry.update(user);
            return Response.ok().build();
        } catch (IllegalArgumentException ie) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
