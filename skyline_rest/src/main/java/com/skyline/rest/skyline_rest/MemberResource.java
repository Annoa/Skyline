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
    private IMemberRegistry memberBox = Blog.INSTANCE.getMembersRegistry();
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<Member> tmpList = memberBox.getRange(0, memberBox.getCount());
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
        List<Member> tmpList = memberBox.getRange(0, memberBox.getCount());
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
            Member m = memberBox.find(id);
            MemberProxy mp = new MemberProxy(m);
            return Response.ok(mp).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addMember(@FormParam("name") String name) {
        Member member = new Member(name);
        try {
            memberBox.add(member);
//            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf("name")).build(member);
            MemberProxy proxy = new MemberProxy(memberBox.find(member.getId()));
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
            memberBox.remove(Id);
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
            Member m = memberBox.find(id);
            memberBox.update(new Member(id, m.getDate(), name, m.getPosts(), 
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
        List<Member> tmpList = memberBox.getRange(first, last);
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
        Integer i = new Integer(memberBox.getCount());
        PrimitiveJSONWrapper<Integer> pj = new PrimitiveJSONWrapper<Integer>(i);
        return Response.ok(pj).build();
    }

    @GET
    @Path("favoriteMembers")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getFavoriteMembers(@QueryParam("memberId") Long memberId) {
        Member member = memberBox.find(memberId);
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
        Member member1 = memberBox.find(memberId);
        Member member2 = memberBox.find(memberId2);
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
        List<Member> resultList = memberBox.search(searchString);
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
        List<Member> resultList = memberBox.search(searchString);
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
            Member user = memberBox.find(session.getId());
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
            Member member = memberBox.getMember(memberName);
            MemberProxy proxy = new MemberProxy(member);
            return Response.ok(proxy).build();
        } catch (IllegalArgumentException ie) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
