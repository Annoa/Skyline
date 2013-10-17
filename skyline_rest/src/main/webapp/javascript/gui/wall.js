/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    //Clear the table
    //    $('#posts tbody').remove();
    console.log("walls javascript körs");
    
    skyline.getPostBox().getAll().done(renderTable);
    
    var postIsOpen = new Array();
//    var myArray2 : boolean[] = new boolean[10];
    
    
    //Eventhandling when clicking on a post
//    $("#posttable").delegate("tr", "click", function(){
//        var thisTr = this;
        $("#posttable").delegate("tr", "click", function(){
            var thisTr = this;
            //        var postOpen = new Boolean(0);
            console.log($(this).html());
            console.log($(thisTr).index());
            var rowIndex = ($(thisTr).index());
            if(postIsOpen[rowIndex]===false){
                $(thisTr).after("<td>Opening</td>");
                postIsOpen[rowIndex]=true;
                console.log(postIsOpen[rowIndex]);
            }
            else{
                $(thisTr).after("<td>Closing</td>");
                postIsOpen[rowIndex]=false;
                console.log(postIsOpen[rowIndex]);
            }
        });
//    });
    
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
            //            htmlText += '<div id="div'+ i +'" />';
            htmlText += '<tr><td>'
                    + 'Title: ' + post[i].title + '<br>' 
                    + 'Date: ' + post[i].date + '<br>' 
                    + 'Text: ' + post[i].bodyText + '<br>' 
                    + 'Video link: ' + post[i].postVideo + '<br>' 
                    + 'Up Votes = ' + post[i].upVotes + '<br>'
                    + 'Down Votes = ' + post[i].downVotes + '<br>'
                    + '<br>'
                    + 'Post ID: ' + post[i].id + '<br>' 
                    + '<br><br>\n\
                        </td></tr>';
            console.log(post[i]);
            postIsOpen[i]=false;
            console.log(postIsOpen[i]);
        }
        $('#posttable').append(htmlText);
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