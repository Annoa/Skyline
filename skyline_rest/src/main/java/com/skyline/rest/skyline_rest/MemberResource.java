/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.skyline_rest;

import com.skyline.model.core.IBlog;
import com.skyline.model.core.Member;
import com.skyline.model.utils.IDAO;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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

    private IDAO<Member, Long> memberBox = Blog.INSTANCE.getMembersRegistry();
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
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf("name")).build(member);
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
            memberBox.update(new Member(id, name));
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
        for ( Member m : tmpList) {
            memberList.add(new MemberProxy(m));
        }
        GenericEntity<List<MemberProxy>> ge = new GenericEntity<List<MemberProxy>>(memberList){};
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
}
