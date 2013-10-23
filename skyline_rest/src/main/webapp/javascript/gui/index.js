/**
 * Js for index page
 * @author annoa
 */
$(document).ready(function() {
    
    $("#login").click(function() {
        $(".menu-item").parent().removeClass('active');
        $("#controls").contents().remove();
        $("#login").parent().addClass('active');
        $("#contents").load("/skyline_rest/authorize/login.html");
    });
    
    $("#wall").click(function() {
        $("#controls").contents().remove();
    });
});



