/**
 * Wall.js is covering the view-part of everything that has to do 
 * with the wall. As you can see in the methods below this is for 
 * example things as rendering the posts and their comments.
 * 
 * @returns {undefined}
 */
$(function() {
    
    $("#aboutUrl2").remove();
    //Clear the table
    //    $('#posts tbody').remove();
    console.log("walls javascript körs");
    skyline.getPostBox().getAll().done(renderAllPosts);
    
    //Eventhandling when clicking on a post

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

    /**
     * Function rendering comments of the posts and comments of the comments.
     * 
     * @param {type} post
     * @returns {undefined}
     */
    function renderComments(post) {
        skyline_comments.getCommentBox().getRootCommentsForPost(post).done(commentDraw);
        
        function commentDraw(comments) {
            
            if ($('[id="comments-post#' + post + '"]').is(':empty')) {
                var even = true;
                var htm = '';
                $.each(comments, function(i, el) {
                    recurseChildren(i, el);
                });
                function recurseChildren(i, parent) {
                    var color = ((even) ? 'even' : 'uneven');
                    htm += '<div class="comment comment-' + color + '"><div>\n\
                    <span class="glyphicon glyphicon-arrow-up"></span>\n\
                    <span class="glyphicon glyphicon-arrow-down"></span></div><div><p>'+ parent.commentText + '</p></div>';
                    if (undefined !== parent.childComments){
                        even = !even;
                        $.each(parent.childComments, function(z, child) {
                            recurseChildren(z, child);
                        });
                        even = !even;
                    }
                    htm +='</div>';
                };
                $('[id="comments-post#' + post + '"]').append(htm);
            }
            return false;
        }
    };
    
    /**
     * Function rendering all posts on the wall.
     * 
     * @param {type} post
     * @returns {undefined}
     */
    function renderAllPosts(post) {
        console.log(post[0]);
        $("#postlist").contents().remove();
        var htmlText = '';
//            htmlText += '<li id="post#' + post[i].id + '">'
//                    + '<button id="comment-button#'+ post[i].id +'" class="btn">Show comments</button>'
//                    + '<div id="comments-post#'+post[i].id+'" class="commentbox"></div>'
        for(var i=0; i<post.length; i++){
            htmlText += convertPostToHTML(post[i]);
        }
        
        $('#postlist').append(htmlText);
        
        $("[id^=comment-button#]")
            .button()
            .click(function(){
                var targetId = $(this).attr('id');
                var target = targetId.substr(targetId.indexOf("#")+1);
                var targetDiv = $('[id="comments-post#' + target + '"]')
                if ($('[id="comments-post#' + target + '"]').is(':empty')) {
                    $(this).html("Hide comments");
                    renderComments(target);
                } else {
                    $(this).html("Show comments");
                    $(targetDiv).contents().remove();
                }

            });
    }   
    
    
    /**
     * Function rendering the added post at the bottom of the existing 
     * posts on the wall. That is, the post is added without re-rendering 
     * all the posts on the wall.
     * 
     * @param {type} post
     * @returns {undefined}
     */
    function renderAddedPost(post) {
        //        $('#postlist').append(htmlText);
        console.log("LOL");
        console.log(post);
        $('#postlist').append(convertPostToHTML(post));
    }
    /**
     * Function converting data from post into HTML-code.
     * 
     * @param {type} post
     * @returns {String}
     */
    function convertPostToHTML(post) {
        var d = new Date(post.date);
        return '<li id="post#' + post.id + '">'
                + '<h4 class="custom-title">The title of the day is ' + post.title + '</h2>' 
                + '<p class="custom-date">Date: ' + d.getFullYear() + '-' + (d.getMonth()+1) + 
                    '-' + d.getDate() + '   ' + d.getHours() + 
                    ':' + d.getMinutes() + '</p>' 
                + '<p class="custom-body-text">The text I write is  ' + post.bodyText + '</p>' 
                + '<p>Video link: ' + post.postVideo + '</p>' 
                + '<p><iframe width="420" height="345"'
                    + 'src="' + convertToYouTubeEmbedLink(post.postVideo) + '">'
                +' </iframe></p>'
                + '<p>Up Votes = ' + post.upVotes + '</p>'
                + '<p>Down Votes = ' + post.downVotes + '</p>'
                + '<br>'
                + '<p>Post ID: ' + post.id + '</p>'
                + '<button id="comment-button#'+ post.id +'" class="btn">Show comments</button>'
                + '<div id="comments-post#'+post.id+'" class="commentbox"></div>'
                + '</li>';
    }
    /**
     * Function converting a standard youtube watch-link into a youtube 
     * embedded link.
     * 
     * @param {type} link
     * @returns {String}
     */
    function convertToYouTubeEmbedLink (link) {
        console.log("convertToEmbed");
        var videoSuffix = link.substring(link.length - 11, link.length)
            console.log(link.search("youtube.com/watch?v=") + " <- Bad link if -1");
        if(link.search("youtube.com/watch")!==-1){
            console.log("Good link");
            return "http://www.youtube.com/embed/" + videoSuffix;
        }
        else{
            console.log("Bad link, retype as: www.youtube.com/watch?v=.....");
            return "";
            
        }
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