/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.skyline_rest;

import com.skyline.model.core.IBlog;
import com.skyline.model.core.Member;
import com.skyline.model.utils.IDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
