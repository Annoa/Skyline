

$(function(){
//    if(wallLoaderIdentifier==="favorites"){
        console.log("memberscontroller is running");
        console.log(skyline.getPostBox().getPostByFavorites());
        skyline.getPostBox().getPostByFavorites().done(GUI.renderAllPosts);
//    }
    
//    skyline_member.getMemberRegistry().getAll().done(GUI.renderAllPosts);
});
