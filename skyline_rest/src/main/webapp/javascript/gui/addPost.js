/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $("#aboutUrl2").remove();
    $("#add-post-content").load("/skyline_rest/content/wall.html");
    resetActive();
    $("#addPost").parent().addClass("active");
    event.preventDefault();
    
    function resetActive() {
        $(".menuItem").parent().removeClass("active");
    };
});    
