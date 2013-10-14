/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.rest.skyline_rest.test;

import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import com.skyline.rest.skyline_rest.PostProxy;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.junit.Test;

/**
 *
 * @author hajo modified by tomassellden
 */
public class PostProxtTest {
    
//    @Test
//    public void testMarchalPostProxy() throws JAXBException {
//        JAXBContext jc = JAXBContext.newInstance(PostProxy.class);
//        Member m = new Member("member");
//        BodyText bt = new BodyText("test post");
//        PostProxy pp = new PostProxy(new Post(m, "testTitle", "test text", null, null));
//        Marshaller marshaller = jc.createMarshaller();
//        marshaller.marshal(pp, System.out);
//    }
}
