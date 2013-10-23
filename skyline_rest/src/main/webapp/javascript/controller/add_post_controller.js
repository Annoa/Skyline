$(document).ready(function() {
    $("#new-post").toggle();
    //Button add new post
    $("#add-post-toggle")
            .button()
            .click(function() {
        $("#new-post").toggle();
    });
    
    //Button: Save post form
    $("#save-post").button().click(function(){
        var newPost = getFormDialogData();
        clearFormDialogData();
        $("#new-post").toggle();
        //Creating a new post of the entered values
        var def = skyline.getPostBox().add(newPost);
        def.done(function(addedPost){
            GUI.renderAddedPost(addedPost);
        });
    });

    //Button: Cancel post form
    $("#cancel-post").button().click(function(){
        clearFormDialogData();
        $("#new-post").toggle();
    });
    
    function getFormDialogData() {
        var post = {};
        post.title = $("#ptitle").val();
        // **
        // Preserves rowbreaks for saving to database
        // Also only allows two rowbreaks max in a row
        var text = $("#ptext").val();
        post.bodyText = text.replace(/\n/g, "<br />");
        // **
        post.postVideo = $("#pvideo").val();
        post.postPicture = $("#ppicture").val();
        return post;
    }
    
    function clearFormDialogData() {
        $("#ptitle").val("");
        $("#ptext").val("");
        $("#pvideo").val("");
        $("#ppicture").val("");
    }
});
