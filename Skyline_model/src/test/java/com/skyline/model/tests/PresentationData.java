/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyline.model.tests;

import com.skyline.model.core.BlogFactory;
import com.skyline.model.core.Comment;
import com.skyline.model.core.IBlog;
import com.skyline.model.core.ICommentContainer;
import com.skyline.model.core.IMemberRegistry;
import com.skyline.model.core.IPostContainer;
import com.skyline.model.core.Member;
import com.skyline.model.core.Post;
import com.skyline.model.core.VotingSystem;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author tomassellden
 */
public class PresentationData {

    static IBlog blog;
    private final static String PU = "skyline_pu";
    private final static String TEST_PU = "test_skyline_pu";

    @BeforeClass
    public static void beforeClass() {
        blog = BlogFactory.getBlog(PU);
    }

    @Test
    public void testGetAllPostCommentsAndAllChildComments() {

        ICommentContainer cc = blog.getCommentContainer();
        IPostContainer pc = blog.getPostContainer();
        IMemberRegistry mr = blog.getMemberRegistry();

        //Members
        Member blondb = new Member("Blondinbella");
        Member kissie = new Member("Kissie");
        Member leifGW = new Member("Leif G.W Persson");
        
        mr.add(blondb);
        mr.add(kissie);
        mr.add(leifGW);
        
        //Posts
        //Post bbP1 = new Post("Long lasting", "Det är en sådan häftig känsla att gå in i duschen och ha sina egna produkter där. Mitt shampoo och balsam är verkligen otroligt bra. Jag har ju velat ta fram ett shampoo som får håret att kännas nytvättat länge och det gör min ”long lasting-serie”. Idéen kom ifrån ett shampoo mamma hade hemma när jag var liten från Jane Hellen som doftade otroligt fräscht och bibehöll just känslan av nytvättat hår. Mitt shampoo är väldigt skonsamt mot håret och är sulfatfritt. Det gör att håret tvättas milt och hårbotten behöver inte producera extra fett. Precis som om man torkar ut hyn så vill hyn producera ännu mer fett, så fungerar hårbotten likadant. Shampoot innehåller också bambuextrakt som gör att stråna får en bättre struktur samt granatäpple som är färgbeskyddande.Det är en sådan häftig känsla att gå in i duschen och ha sina egna produkter där. Mitt shampoo och balsam är verkligen otroligt bra. Jag har ju velat ta fram ett shampoo som får håret att kännas nytvättat länge och det gör min ”long lasting-serie”. Idéen kom ifrån ett shampoo mamma hade hemma när jag var liten från Jane Hellen som doftade otroligt fräscht och bibehöll just känslan av nytvättat hår. Mitt shampoo är väldigt skonsamt mot håret och är sulfatfritt. Det gör att håret tvättas milt och hårbotten behöver inte producera extra fett. Precis som om man torkar ut hyn så vill hyn producera ännu mer fett, så fungerar hårbotten likadant. Shampoot innehåller också bambuextrakt som gör att stråna får en bättre struktur samt granatäpple som är färgbeskyddande.", "http://blondinbella.se/files/2013/10/IMG_6663.jpg", null);
        Post bbP1 = new Post("Long lasting", "Det är en sådan häftig känsla att gå in i duschen och ha sina egna produkter där. Mitt shampoo och balsam är verkligen otroligt bra. Jag har ju velat ta fram ett shampoo som får håret att kännas nytvättat länge och det gör min ”long lasting-serie”. Idéen kom ifrån ett shampoo mamma hade hemma när jag var liten från Jane Hellen som doftade otroligt fräscht och bibehöll just känslan av nytvättat hår. Mitt shampoo är väldigt skonsamt mot håret och är sulfatfritt. Det gör att håret tvättas milt och hårbotten behöver inte producera extra fett. Precis som om man torkar ut hyn så vill hyn producera ännu mer fett, så fungerar hårbotten likadant. Shampoot innehåller också bambuextrakt som gör att stråna får en bättre struktur samt granatäpple som är färgbeskyddande.Det är en sådan häftig känsla att gå in i duschen och ha sina egna produkter där. Mitt shampoo och balsam är verkligen otroligt bra. Jag har ju velat ta fram ett shampoo som får håret att kännas nytvättat länge och det gör min ”long lasting-serie”. Idéen kom ifrån ett shampoo mamma hade hemma när jag var liten från Jane Hellen som doftade otroligt fräscht och bibehöll just känslan av nytvättat hår. Mitt shampoo är väldigt skonsamt mot håret och är sulfatfritt. Det gör att håret tvättas milt och hårbotten behöver inte producera extra fett. Precis som om man torkar ut hyn så vill hyn producera ännu mer fett, så fungerar hårbotten likadant. Shampoot innehåller också bambuextrakt som gör att stråna får en bättre struktur samt granatäpple som är färgbeskyddande.", null, "");
//        Post bbP2 = new Post("Mammaledig","Jag har precis ställt in min mail på mammaledig. Jag skriver att vi inom kort väntar barn och att jag inte kommer att svara på mina mail lika frekvent och under finns mina kollegor att kontakta. Det känns så märkligt. Att lämna över. Det är en liten pytte-skuldkänsla och dåligt samvete som dyker upp. En litet troll på ena axeln som säger: Men hallå Isabella, du driver företag då kan man inte pausa stressiga dagar utan jobba på tills vattnet går. Men min hjärna besvarar och säger: Att föda ett barn är ett jobb i sig och precis som med allt jag gör så vill jag vara förberedd och då krävs det en tid hemma innan då jag har möjlighet att gå in i det, återhämta mig och fokusera.", null, null);
//        Post bbP3 = new Post("The Cake", ".. Det blev en tårta från Thelins. Väldigt god med vit choklad, grädde och hallon inuti. Lite lik vår bröllopstårta i smaken. Min familj gick nyss, tiden går så snabbt. Strax ska jag spela in ett klipp till er och visa vad vi har packat ner vår bb-väska, såg nämligen att många av er hade frågat om det. Kommer upp imorgon. Alltid kul med tips till vad jag ska filma, har ni andra ideér så säg till! Blev endast två inlägg idag men ni får mer uppdateringar imorgon. Nu väntar en mysig söndagskväll.", null, null);
//        
//        Post kP1 = new Post("Podcast", "I detta avsnitt pratar vi bland annat om rumpor, jag avslöjar ifall jag har tänkt på att skaffa ”fejk rumpa” eller inte och vi börjar bråka lite ibörjan av avsnittet.. Haha oups! ;) Berätta gärna vad ni tycker om dagens avsnitt!", "", "");
//      //  Post kP1 = new Post("Podcast", "I detta avsnitt pratar vi bland annat om rumpor, jag avslöjar ifall jag har tänkt på att skaffa ”fejk rumpa” eller inte och vi börjar bråka lite ibörjan av avsnittet.. Haha oups! ;) Berätta gärna vad ni tycker om dagens avsnitt!", null, "http://www.youtube.com/watch?list=UUQaE17VGhJfi2MYaUhweeTg&v=uaVa5Lnzrhk");
//        Post kP2 = new Post("Fanbilder!", "Oj jag fick över 1000 bilder skickade till mig, alla är så himla fina/roliga på sina egna sätt. Det var löjligt svårt att välja ut några stycken bara, men jag gjorde så att jag först tog alla som hade gjort som jag sa, dvs skriva sin adress och eventuell bloggadress i mailet. Sen försökte jag välja ut bilder som såg ut att ha tagit lite extra lång tid, dom som jag tyckte mest om osv. :) Men hörrni, kan ni hjälpa mig att välja ut vilken ni tycka ska vinna? Och kom ihåg, jag kommer välja en som blir 2a också nu. Jag gjorde en till hemlig goodiebag som tröstpris ;)", null, null);
//        Post kP3 = new Post("Kissie Hilton på landet..?", "Så, under dagarna (wow, 2 haha) vi inte har uppdaterat så har det inte hänt så mycket.. Här finns det inte dusch eller toa, vi använder ett dass!! Haha så himla äckligt, tänk er dom som måste leva såhär..? UEW! Vi måste också pumpa vattnet från en pump ute i skogen, usch..? Hoppas verkligen vattnet är rent så vi inte kommer hem med svininfluensan eller något annat skit som folk bär runt på..", null, null);
//        
//        //Post lGW1 = new Post("Löjligt", "De här killarna. Måste vara kriminella. Jag känner det på mig.", null, "http://www.youtube.com/watch?v=btwG0E4xke0");
//        Post lGW1 = new Post("Löjligt", "De här killarna. Måste vara kriminella. Jag känner det på mig.", "", "");
//
//        
        pc.add(bbP1);
        blondb.addPost(bbP1);
        mr.update(blondb);
//        
//        pc.add(kP1);
//        kissie.addPost(kP1);
//        mr.update(kissie);
//        
//        pc.add(bbP2);
//        blondb.addPost(bbP2);
//        mr.update(blondb);
//        
//        pc.add(bbP3);
//        blondb.addPost(bbP3);
//        mr.update(blondb);
//        
//        pc.add(kP2);
//        kissie.addPost(kP2);
//        mr.update(kissie);
//        pc.add(kP3);
//        kissie.addPost(kP3);
//        mr.update(kissie);
//        
//        
//        pc.add(lGW1);
//        leifGW.addPost(lGW1);
//        mr.update(leifGW);
//        
//        //Comments
//        Comment bbc1 = new Comment("Herreguuud!"); 
//        cc.add(bbc1);
//        blondb.addComment(bbc1);
//        mr.update(blondb);
//        lGW1.addComment(bbc1);
//        pc.update(lGW1);
//        Comment bbc2 = new Comment("amenslutanudah."); 
//        cc.add(bbc2);
//        blondb.addComment(bbc1);
//        mr.update(blondb);
//        kP2.addComment(bbc1);
//        pc.update(kP2);
//        Comment bbc3 = new Comment("Du är så vis!"); 
//        cc.add(bbc3);
//        
//        Comment bbc4 = new Comment("Det hade jag aldrig klarat"); 
//        cc.add(bbc4);
//
//        
//        
//        Comment kc1 = new Comment("tiep"); 
//        Comment kc2 = new Comment("Grymt!"); 
//        Comment kc3 = new Comment("Snygga killar!"); 
//        Comment kc4 = new Comment("Du ska alltid vara i centrum"); 
//        
//        Comment gw1 = new Comment("Missa inte Efterlyst"); 
//        Comment gw2 = new Comment("The answer is to increase yourself."); 
//        Comment gw3 = new Comment("Hello, i read your blog from time to time and i own a similar one and i was just wondering if you get a lot of spam comments? If so how do you stop it, any plugin or anything you can recommend? I get so much lately it’s driving me crazy so any support is very much appreciated."); 
//        Comment gw4 = new Comment("Helt galet"); 
//        Comment gw5 = new Comment("Mitt bästa verk. Hittills."); 
//        
        
        
//        Member mem1 = new Member("Krobbe");
//        mr.add(mem1);
//
//        Comment com1 = new Comment("This is the first comment");
//        Comment com2 = new Comment("This is the second comment");
//        Comment com3 = new Comment("This is the third comment");
//        Comment com4 = new Comment("This is the fourth comment");
//        Comment com5 = new Comment("This is the fifth comment");
//        Comment com6 = new Comment("This is the sixth comment");
//
//        Post post1 = new Post("Post", "Tester", null, "http://www.youtube.com/watch?v=YYkVS9y5iP4");
//        pc.add(post1);
//        cc.add(com1);
//        cc.add(com2);
//        cc.add(com3);
//        cc.add(com4);
//        cc.add(com5);
//        cc.add(com6);
//
//        post1.addComment(com1);
//        post1.addComment(com2);
//        post1.addComment(com3);
//        post1.addComment(com4);
//        post1.addComment(com5);
//        post1.addComment(com6);
//        
//        mem1.addPost(post1);
//        mem1.addComment(com1);
//        mem1.addComment(com2);
//        mem1.addComment(com3);
//        mem1.addComment(com4);
//        mem1.addComment(com5);
//        mem1.addComment(com6);
//        
//        assertTrue(post1.getComments().size() == 6);
//        
//        mr.update(mem1);
//
//        pc.update(post1);
//
//        assertTrue(cc.getCount() == 6);
//        assertTrue(pc.getCount() == 1);
//        
//        com1.addChildComment(com2);
//        com1.addChildComment(com3);
//        com2.addChildComment(com4);
//        com4.addChildComment(com5);
//        cc.update(com1);
//        cc.update(com2);
//        cc.update(com4);
//        
//
//        cc.update(new Comment(com1.getId(), com1.getChildComments(), 
//                com1.getCommentText(), com1.getCommentDate(), 
//                new VotingSystem(150, 0)));
//        cc.update(new Comment(com2.getId(), com2.getChildComments(), 
//                com2.getCommentText(), com2.getCommentDate(), 
//                new VotingSystem(20, 20)));
//        cc.update(new Comment(com3.getId(), com3.getChildComments(), 
//                com3.getCommentText(), com3.getCommentDate(), 
//                new VotingSystem(20, 0)));
//        cc.update(new Comment(com4.getId(), com4.getChildComments(), 
//                com4.getCommentText(), com4.getCommentDate(), 
//                new VotingSystem(150, 0)));
//        cc.update(new Comment(com5.getId(), com5.getChildComments(), 
//                com5.getCommentText(), com5.getCommentDate(), 
//                new VotingSystem(20, 20)));
//        cc.update(new Comment(com6.getId(), com6.getChildComments(), 
//                com6.getCommentText(), com6.getCommentDate(), 
//                new VotingSystem(20, 0)));
//        
//        List<Comment> result = cc.getAllCommentsOnPost(post1);
//        assertEquals(6, result.size());
//        
//        result = cc.getRootCommentsForPost(post1);
//        assertTrue(result.size() == 2);
//        
//        Member xmem1 = new Member("Anno");
//        mr.add(xmem1);
//
//        Comment xcom1 = new Comment("Anno: This is the first comment");
//        Comment xcom2 = new Comment("Anno: This is the second comment");
//        Comment xcom3 = new Comment("Anno: This is the third comment");
//        Comment xcom4 = new Comment("Anno: This is the fourth comment");
//        Comment xcom5 = new Comment("Anno: This is the fifth comment");
//        Comment xcom6 = new Comment("Anno: This is the sixth comment");
//
//        Post xpost1 = new Post("Post", "Tester", null, "http://www.youtube.com/watch?v=YYkVS9y5iP4");
//        pc.add(xpost1);
//        cc.add(xcom1);
//        cc.add(xcom2);
//        cc.add(xcom3);
//        cc.add(xcom4);
//        cc.add(xcom5);
//        cc.add(xcom6);
//
//        xpost1.addComment(xcom1);
//        xpost1.addComment(xcom2);
//        xpost1.addComment(xcom3);
//        xpost1.addComment(xcom4);
//        xpost1.addComment(xcom5);
//        xpost1.addComment(xcom6);
//        
//        xmem1.addPost(xpost1);
//        xmem1.addComment(xcom1);
//        xmem1.addComment(xcom2);
//        xmem1.addComment(xcom3);
//        xmem1.addComment(xcom4);
//        xmem1.addComment(xcom5);
//        xmem1.addComment(xcom6);
//        
//        assertTrue(xpost1.getComments().size() == 6);
//        
//        mr.update(xmem1);
//
//        pc.update(xpost1);
//
//        assertTrue(cc.getCount() == 12);
//        assertTrue(pc.getCount() == 2);
//        
//        xcom1.addChildComment(xcom2);
//        xcom1.addChildComment(xcom3);
//        xcom2.addChildComment(xcom4);
//        xcom4.addChildComment(xcom5);
//        cc.update(xcom1);
//        cc.update(xcom2);
//        cc.update(xcom4);
//        
//
//        cc.update(new Comment(xcom1.getId(), xcom1.getChildComments(), 
//                xcom1.getCommentText(), xcom1.getCommentDate(), 
//                new VotingSystem(150, 0)));
//        cc.update(new Comment(xcom2.getId(), xcom2.getChildComments(), 
//                xcom2.getCommentText(), xcom2.getCommentDate(), 
//                new VotingSystem(20, 20)));
//        cc.update(new Comment(xcom3.getId(), xcom3.getChildComments(), 
//                xcom3.getCommentText(), xcom3.getCommentDate(), 
//                new VotingSystem(20, 0)));
//        cc.update(new Comment(xcom4.getId(), xcom4.getChildComments(), 
//                xcom4.getCommentText(), xcom4.getCommentDate(), 
//                new VotingSystem(150, 0)));
//        cc.update(new Comment(xcom5.getId(), xcom5.getChildComments(), 
//                xcom5.getCommentText(), xcom5.getCommentDate(), 
//                new VotingSystem(20, 20)));
//        cc.update(new Comment(xcom6.getId(), xcom6.getChildComments(), 
//                xcom6.getCommentText(), xcom6.getCommentDate(), 
//                new VotingSystem(20, 0)));
//        
//        List<Comment> xresult = cc.getAllCommentsOnPost(xpost1);
//        assertEquals(6, xresult.size());
//        
//        xresult = cc.getRootCommentsForPost(xpost1);
//        assertTrue(xresult.size() == 2);

    }
}
