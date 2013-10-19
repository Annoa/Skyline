/**
 * Js for index page, the tabs
 */
$(document).ready(function() {
    
    $("#contents").load("content/wall.html");
    
    $("#tabs").tabs({
        beforeLoad: function(event, ui) {
            ui.jqXHR.error(function() {
                ui.panel.html(
                        "Couldn't load this tab. We'll try to fix this as soon as possible. " +
                        "If this wouldn't be a demo.");
            });
        }
    });
    
    $("#wall").click(function(event) {
        $("#contents").load("content/wall.html");
        resetActive();
        $("#wall").parent().addClass("active");
        
        event.preventDefault();
    });
    
    $("#myPage").click(function(event) {
        $("#contents").load("content/myPage.html");
        resetActive();
        $("#myPage").parent().addClass("active");
        
        event.preventDefault();
    });
    
    $("#members").click(function(event) {
        $("#contents").load("content/members.html");
        resetActive();
        $("#members").parent().addClass("active");
        
        event.preventDefault();
    });
    $("#login").click(function(event) {
        $("#contents").load("author/pageLogin.html");
        resetActive();
        $("#logout").parent().addClass("active");
        
        
        event.preventDefault();
    });
     $("#logout").click(function(event) {
        $("#contents").load("author/logout.html");
        resetActive();
        $("#logout").parent().addClass("active");
        
        
        event.preventDefault();
    });
    
    
    function resetActive() {
        $(".menuItem").parent().removeClass("active");
    };
});



