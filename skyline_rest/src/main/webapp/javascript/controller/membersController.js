

$(function(){
    console.log("membersController is running");
    skyline_member.getPostBox().getPostForFavoritesMembers().done(GUI.renderAllPosts);
});
