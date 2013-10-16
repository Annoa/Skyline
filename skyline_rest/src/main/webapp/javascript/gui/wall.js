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
    $("#posttable").delegate("td", "click", function(){
        var thisTd = this;
        console.log($(this).html());
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
            //            htmlText += '<div id="div'+ i +'" />';
            htmlText += '<tr><td>'
                    + 'Title: ' + post[i].title + '<br>' 
                    + 'Date: ' + post[i].date + '<br>' 
                    + 'Up Votes = ' + post[i].upVotes + '<br>'
                    + 'Down Votes = ' + post[i].downVotes + '<br>'
                    + '<br>'
                    + 'Best regards<br>User Nr ' + post[i].id + '<br>' 
                    + '<br><br>\n\
                        </td></tr>';
            console.log(post[i]);
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
                    //Fixa autorendering av uppdaterad tabell här?
                       //nej eftersom vi inte vet var vi befinner oss. (Vi vet bara om vi ska så prev eller next.)
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
        return post;
    }
});