/**
 * @author annoa
 */

$(document).ready(function() {
    
    $(".menuItem").parent().removeClass("active");
    $("#about").parent().addClass("active");
    
    $(".menu-item").click(function() {
        window.location.replace("/skyline_rest/index.xhtml");
    });
});