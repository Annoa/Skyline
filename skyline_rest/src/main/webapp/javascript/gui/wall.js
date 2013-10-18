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
    $("#postlist").click(function(event){
        var postId = 1;
        var targetId = $(event.target).closest("li").attr('id');
        var target = targetId.substr(targetId.indexOf("#")+1);
        console.log(target);
        renderComments(target);
    });
    
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
            //                </li>;
            console.log('<li id="post#' + post[i].id + '">');
            htmlText += '<li id="post#' + post[i].id + '">'
                    + '<h2>Title: ' + post[i].title + '</h2>' 
                    + '<p>Date: ' + post[i].date + '</p>' 
                    + '<p>Text: ' + post[i].bodyText + '</p>' 
                    + '<p>Video link: ' + post[i].postVideo + '</p>' 
                    + '<p>Up Votes = ' + post[i].upVotes + '</p>'
                    + '<p>Down Votes = ' + post[i].downVotes + '</p>'
                    + '<br>'
                    + '<p>Post ID: ' + post[i].id + '</p>' 
                    + '</li>';
        }
        $('#postlist').append(htmlText);
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