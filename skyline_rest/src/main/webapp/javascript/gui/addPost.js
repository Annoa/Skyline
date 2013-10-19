/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    //Clear the table
    //    $('#posts tbody').remove();
    console.log("walls javascript körs");
   
    //Button
    $("#write-post")
            .button()
            .click(function() {
        createWritePostDialog();
    }); 
    
});
