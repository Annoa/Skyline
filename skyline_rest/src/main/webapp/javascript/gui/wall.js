/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    //Clear the table
    //    $('#posts tbody').remove();
    console.log("walls javascript körs");
    skyline.getPostBox().getAll().done(renderTable);
    
    //Eventhandling when clicking on a post
    
    
    //Button
    $("#write-post")
            .button()
            .click(function() {
        createWritePostDialog();
    });
    
    /**********************************************
     *   
     *   Function for redering comments of a post
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
    
    /**********************************************
     *   
     *   Function for redering table of all wall posts
     */
    function renderTable(post) {
        console.log(post[0]);
        $("#postlist").contents().remove();
        var htmlText = '';
        for(var i=0; i<post.length; i++){
            //            htmlText += '<div id="div'+ i +'" />'
            //            <li>
            //                    <h2>post 1</h2>
            //                    <p>lorem ipsum lol</p>
            //                    <div class="comments" hidden>
            //                        <ol>
            //                            <li>
            //                                kommmentar 1
            //                            </li>
            //                            <li>
            //                                kommmentar 2
            //                            </li>
            //                        </ol>
            //                    </div>
            var d = new Date(post[i].date);
            htmlText += '<li id="post#' + post[i].id + '">'
            
                    + '<h2>Title: ' + post[i].title + '</h2>' 
                    + '<p>Date: ' + d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate() + '   ' + 
                    (d.getHours() < 10 ? '0' + d.getHours() : d.getHours()) + ':' + 
                    (d.getMinutes() < 10 ? '0' + d.getMinutes() : d.getMinutes()) + '</p>' 
                    + '<p>Text: ' + post[i].bodyText + '</p>' 
                    + '<p>Video link: ' + post[i].postVideo + '</p>' 
                    + '<p>Up Votes = ' + post[i].upVotes + '</p>'
                    + '<p>Down Votes = ' + post[i].downVotes + '</p>'
                    + '<br>'
                    + '<p>Post ID: ' + post[i].id + '</p>'
                    + '<button id="comment-button#'+ post[i].id +'" class="btn">Show comments</button>'
                    + '<div id="comments-post#'+post[i].id+'" class="commentbox"></div>'
                    + '</li>';
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
    
    function createWritePostDialog() {
        // Use JQueryUI dialog
        console.log("createAddDialog");
        //        clearFormDialogData();
        console.log("Formdata cleared");
        //        $("#dialog-form")
        var myDialog = $("#add-edit-post").dialog({
            autoOpen: false,
            modal: true,
            stack: true,
            title: "Write new post",
            buttons: {
                Save: function() {
                    var newPost = getFormDialogData();
                    console.log(newPost);
                    skyline.getPostBox().add(newPost);
                    $(this).dialog("close");
                    skyline.getPostBox().getAll().done(renderTable);
                },
                Cancel: function() {
                    $(this).dialog("close");
                }
            }
        });
        // Show it
        myDialog.dialog("open");
    }
    
    function getFormDialogData() {
        var post = {};
        post.title = $("#add-edit-post #ptitle").val();
        post.bodyText = $("#add-edit-post #ptext").val();
        post.postVideo = $("#add-edit-post #pvideo").val();
        return post;
    }
});