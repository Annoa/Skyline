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
 * @author hajo modified by AntonPalmqvist
 */
public class PostProxtTest {
    
    @Test
    public void testMarchalPostProxy() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(PostProxy.class);
        Post testPost = new Post("A nice little text", 
                "This is a text I wrote when I was travelling in Australia.", 
                "http://www.hdwallpapersinn.com/wp-content/uploads/2012/06/sunset.jpg", 
                "http://www.youtube.com/watch?v=RnqAXuLZlaE");
        PostProxy pp = new PostProxy(testPost);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(pp, System.out);
    }
}
