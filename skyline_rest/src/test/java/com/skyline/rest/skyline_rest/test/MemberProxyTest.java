/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.skyline_rest.test;

import com.skyline.model.core.Member;
import com.skyline.rest.skyline_rest.MemberProxy;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.junit.Test;

/**
 *
 * @author hajo modified by tomassellden
 */
public class MemberProxyTest {
    
    @Test
    public void testMarchalMemberProxy() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(MemberProxy.class);
        Member m = new Member("testMember");
        MemberProxy mp = new MemberProxy(m);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(mp, System.out);
    }
    
}
