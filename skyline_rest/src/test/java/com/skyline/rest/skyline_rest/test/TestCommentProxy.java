package com.skyline.rest.skyline_rest.test;

import com.skyline.model.core.Comment;
import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import com.skyline.rest.skyline_rest.CommentProxy;
import com.skyline.rest.skyline_rest.MemberProxy;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.junit.Test;

/**
 *
 * @author hajo modified by tomassellden
 */
public class TestCommentProxy {
    
    @Test
    public void testMarchalCommentProxy() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(CommentProxy.class);
        Comment c = new Comment("I just commented myself");
        CommentProxy cp = new CommentProxy(c);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(cp, System.out);
    }
    
}
