/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
        console.log("Clicked save");
        var newPost = getFormDialogData();
        clearFormDialogData();
        $("#new-post").attr("hidden",'hidden');
        //Creating a new post of the entered values
        var def = skyline.getPostBox().add(newPost);
        def.done(function(addedPost){
            GUI.renderAddedPost(addedPost);
        });
    });

    //Button: Cancel post form
    $("#cancel-post").button().click(function(){
        $("#new-post").toggle();
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
