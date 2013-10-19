/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    
    $("#aboutUrl2").remove();
    //Clear the table
    //    $('#posts tbody').remove();
    console.log("walls javascript körs");
   
    //Button
   //Button add new post
    $("#write-post")
            .button()
            .click(function() {
        $("#new-post").removeAttr('hidden');
    });
     $("#save-post").button().click(function(){
        var newPost = getFormDialogData();
        console.log(newPost);
        clearFormDialogData();
        $("#new-post").attr("hidden",'hidden');
        skyline.getPostBox().add(newPost).then(renderAddedPost(newPost));
    });

    //Button: Cancel post form
    $("#cancel-post").button().click(function(){
        $("#new-post").attr("hidden",'hidden');
    });
    
    function getFormDialogData() {
        var post = {};
        post.title = $("#ptitle").val();
        post.bodyText = $("#ptext").val();
        post.postVideo = $("#pvideo").val();
        return post;
    }
    
    function clearFormDialogData() {
        $("#add-edit-post #ptitle").val("");
        $("#add-edit-post #ptext").val("");
        $("#add-edit-post #pvideo").val("");
    }

});
