/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    //    console.log("walls javascript körs");
    //    var postContainer = allPosts.getRange(0, 2).
    ////    console.log("walls javascript körs222222" + allPosts);
    //    console.log(allPosts);
    //    console.log(postContainer);
    //    
    skyline.getPostBox().getAll().done(function(post){
        console.log(post[0]);
        for(var i=0; i<post.length; i++){
            console.log(post[i]);
            $('#posts').append("<tr><td>" + post[i].id + "</td></tr>");
//                    + "<td>" + post[i].name + "</td>"
//                    + "<td>" + post[i].price + "</td></tr>");
        }
    });
    
    
    $('#posts tbody').remove();
    
    //Create the table of posts
    
});