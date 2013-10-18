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
    $("#postlist > li").on("click", function(evt){
        $(this).find(".comments").toggle();
    });
    
    //Button
    $("#write-post")
            .button()
            .click(function() {
        createWritePostDialog();
    });
    
    /**********************************************
     *   
     *   Function for redering table of all wall posts
     */
    function renderTable(post) {
        console.log(post[0]);
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
            var d = new Date(post[i].date);
            htmlText += '<li>'
                    + '<h2>Title: ' + post[i].title + '</h2>' 
                    + '<p>Date: ' + d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate() + '   ' + d.getHours() + ':' + d.getMinutes() + '</p>' 
                    + '<p>Text: ' + post[i].bodyText + '</p>' 
                    + '<p>Video link: ' + post[i].postVideo + '</p>' 
                    + '<iframe width="420" height="345"'
                        + 'src="' + convertToYouTubeEmbedLink(post[i].postVideo) + '">'
                    +' </iframe>'
                    + '<p>Up Votes = ' + post[i].upVotes + '</p>'
                    + '<p>Down Votes = ' + post[i].downVotes + '</p>'
                    + '<br>'
                    + '<p>Post ID: ' + post[i].id + '</p>' 
                    + '</li>';
        }
        $('#postlist').append(htmlText);
    }
    
    function convertToYouTubeEmbedLink (link) {
        return link.replace("http://www.youtube.com/watch?v=", "http://www.youtube.com/embed/");
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