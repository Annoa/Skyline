/**
 * Controller of the wall. Handles events related to the wall.
 * 
 * @returns {undefined}
 */
$(document).ready(function() {
    $("#aboutUrl2").remove();
    //Clear the table
    //    $('#posts tbody').remove();
        console.log("wallsController javascript körs");
        console.log(wallLoaderIdentifier);
        
    if(wallLoaderIdentifier==="wall"){
        console.log("Render all posts");
        skyline.getPostBox().getAll().done(GUI.renderAllPosts);
    }
    
//    if(wallLoaderIdentifier==="favorites"){
//        console.log("Render Favorite posts");
//        console.log(skyline.getPostBox().getPostByFavorites());
//        skyline.getPostBox().getPostByFavorites().done(GUI.renderAllPosts);
//    }

    
    console.log("write post");
    console.log($("#write-post"))
    //Button add new post
    $("#write-post")
            .button()
            .click(function() {
        $("#new-post").removeAttr('hidden');
    });
    
    //Button: Save post form
    $("#save-post").button().click(function(){
        var newPost = getFormDialogData();
        clearFormDialogData();
        $("#new-post").attr("hidden",'hidden');
        newPost.authorId = 1;
        //Creating a new post of the entered values
        var def = skyline.getPostBox().add(newPost);
        def.done(function(addedPost){
            GUI.renderAddedPost(addedPost);
        });
    });

    //Button: Cancel post form
    $("#cancel-post").button().click(function(){
        $("#new-post").attr("hidden",'hidden');
    });
    
    function getFormDialogData() {
        var post = {};
        post.title = $("#ptitle").val();
        // **
        // Preserves rowbreaks for saving to database
        // Also only allows two rowbreaks max in a row
        var text = $("#ptext").val();
        post.bodyText = text.replace(/\n/g, "<br />")
        // **
        post.postVideo = $("#pvideo").val();
        return post;
    }
    
    function clearFormDialogData() {
        $("#add-edit-post #ptitle").val("");
        $("#add-edit-post #ptext").val("");
        $("#add-edit-post #pvideo").val("");
    }
});