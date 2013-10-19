/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    //Clear the table
    //    $('#posts tbody').remove();
    console.log("walls javascript körs");
    skyline.getPostBox().getAll().done(renderAllPosts);
    
    //Eventhandling when clicking on a post
    $("#postlist").click(function(event){
//        var postId = 1;
//        var targetId = $(event.target).closest("li").attr('id');
//        var target = targetId.substr(targetId.indexOf("#")+1);
//        console.log(target);
//        renderComments(target);

    });

    //Button add new post
    $("#write-post")
            .button()
            .click(function() {
        $("#new-post").removeAttr('hidden');
    });
    
    //Button: Save post form
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

    /**********************************************
     *   
     *   Function for redering comments of a post
     */
    
    function renderComments(post) {
        skyline_comments.getCommentBox().getRootCommentsForPost(post).done(commentDraw);
        
        function commentDraw(comments) {
            console.log("Hoho");
            var htmlText = '';
            for(var i=0; i<comments.length; i++) {
                htmlText += '<p>' + i + '</p>'
            }
            $("#comments").append(htmlText);
        }
    };
    
    /**********************************************
     *   
     *   Function for redering table of all wall posts
     */
    function renderAllPosts(post) {
        console.log(post[0]);
        $("#postlist").contents().remove();
        var htmlText = '';
        for(var i=0; i<post.length; i++){
            console.log(post[i]);
            htmlText += convertPostToHTML(post[i]);
        }
        $('#postlist').append(htmlText);
    }
    
    function renderAddedPost(post) {
        //        $('#postlist').append(htmlText);
        console.log("LOL");
        console.log(post);
        $('#postlist').append(convertPostToHTML(post));
    }
    
    function convertPostToHTML(post) {
        console.log("convertToHTML");
        var d = new Date(post.date);
        return '<li>'
                + '<h2>Title: ' + post.title + '</h2>' 
                + '<p>Date: ' + d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate() + '   ' + d.getHours() + ':' + d.getMinutes() + '</p>' 
                + '<p>Text: ' + post.bodyText + '</p>' 
                + '<p>Video link: ' + post.postVideo + '</p>' 
                + '<iframe width="420" height="345"'
//                    + 'src="' + 'http://www.youtube.com/embed/XGSy3_Czz8k' + '">'
                    + 'src="' + convertToYouTubeEmbedLink(post.postVideo) + '">'
//                    + 'src="' + convertToYouTubeEmbedLink('http://www.youtube.com/embed/XGSy3_Czz8k') + '">'
//                    + 'src="' + convertToYouTubeEmbedLink('w') + '">'
                +' </iframe>'
                + '<p>Up Votes = ' + post.upVotes + '</p>'
                + '<p>Down Votes = ' + post.downVotes + '</p>'
                + '<br>'
                + '<p>Post ID: ' + post.id + '</p>' 
                + '</li>';
    }
    
    function convertToYouTubeEmbedLink (link) {
        console.log("convertToEmbed");
//        var videoUrl = link;
            console.log(link);
            console.log(link.charAt(0));
        if(link.charAt(0)==='w'){
//            return link.replace("www.youtube.com/watch?v=", "http://www.youtube.com/embed/");
            console.log("lika!!!!!!!!");
//            return 'http://www.youtube.com/embed/XGSy3_Czz8k';
            return link.replace("www.youtube.com/watch?v=", "http://www.youtube.com/embed/");
        }
        else if(link.charAt(0)==='h'){
            return link.replace("http://www.youtube.com/watch?v=", "http://www.youtube.com/embed/");
        }
        else if(link.charAt(0)==='y'){
            return link.replace("youtube.com/watch?v=", "http://www.youtube.com/embed/");
        }
        else{
            console.log("BAD LINK. Make sure your link starts with www och http")
            return null;
        }
//        if(true){
//        if(videoUrl.charAt(0)===videoUrl.charAt(0)){
    }
    
//    function createWritePostDialog() {
//
//        // Use JQueryUI dialog
//        
//        //        clearFormDialogData();
//        console.log("Formdata cleared");
//        //        $("#dialog-form")
//        var myDialog = $("#add-edit-post").dialog({
//            autoOpen: false,
//            modal: true,
//            stack: true,
//            title: "Write new post",
//            buttons: {
//                Save: function() {
//                    var newPost = getFormDialogData();
//                    console.log(newPost);
//                    skyline.getPostBox().add(newPost).then(renderAddedPost(newPost));
//                    $(this).dialog("close");
////                    renderAddedPost(newPost);
//                },
//                Cancel: function() {
//                    $(this).dialog("close");
//                }
//            }
//        });
//        // Show it
//        myDialog.dialog("open");
//    }
});