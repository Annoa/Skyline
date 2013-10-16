/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    //Clear the table
    //    $('#posts tbody').remove();
    console.log("walls javascript körs");
    
    skyline.getPostBox().getAll().done(renderTable);
    
    //Eventhandling
//    $("#divid").delegate("tr", "click", function(){
        
    
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
});