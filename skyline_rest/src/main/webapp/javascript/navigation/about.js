/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
   console.log("about"); 
   console.log(window.location.href);
   $("#aboutUrl").remove();
   //aboutUrl2
});
$(document).ready(function() {
    
    $(".menuItem").parent().removeClass("active");
    
    $("#about").parent().addClass("active");
    
});
