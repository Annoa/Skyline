/**
 * Js for index page, the tabs
 * 
 * @author annoa
 */

$(document).ready(function() {
    
    $("#wall").parent().addClass('active');
    
    // Adding extra navigation now that we're logged in
    var home_navigation = '<li><a class="menu-item" id="profile" href="#">Profile</a></li>\n\
                            <li><a class="menu-item" id="favorites" href="#">Favorites</a></li>';
    $("#navbar-main").append(home_navigation);
    
    // Change function of "Log in"-button to "Log out"
    var $login = $("#login");
    $login.html("Log out");
    $login.click(function() {
        $(".menu-item").parent().removeClass('active');
        $login.parent().addClass('active');
        $("#contents").load("/skyline_rest/authorize/logout.html");
    });
    
    $.when(loadWall()).then(function() {
        resetActive();
        $("#wall").parent().addClass("active");
    });
    
    // Adding dialog for writing posts
    function loadWall() {
        $("#controls").load("/skyline_rest/content/add_post.html", function() {
            $("#save-post").click(function() {
                skyline.getPostBox().getAll().done(function(posts) {
                    GUI.renderAllPosts(posts);
                });
            });
        });
        // Adding the main post wall that shows all posts
        $("#contents").load("/skyline_rest/content/wall.html", function() {
            skyline.getPostBox().getAll().done(function(posts) {
                $.when(GUI.renderAllPosts(posts)).then(function() {
                });
            });
        });
    }
    
    /**
     * Listeners and utilites
     */
    $("#tabs").tabs({
        beforeLoad: function(event, ui) {
            ui.jqXHR.error(function() {
                ui.panel.html(
                        "Couldn't load this tab. We'll try to fix this as soon as possible. " +
                        "If this wouldn't be a demo.");
            });
        }
    });
    
    // Loading wall when clicking "Wall"
    $("#wall").click(function(event) {
        loadWall();
    });
    
    // Loading a memberpage for the logged in user
    $("#profile").click(function(event) {
        resetActive();
        $("#profile").parent().addClass("active");
        event.preventDefault();
        
        $("#controls").load("/skyline_rest/content/profile.html", function() {
            skyline.getMemberRegistry().getUser().done(function(user) {
                renderProfileForMember(user); // Declared in profile.js (which is loaded in profile.html)
            });
        });
        
        $("#contents").load("/skyline_rest/content/wall.html", function() {
            skyline.getPostBox().getPostsOfUserByVotes().done(function(posts) {
                GUI.renderAllPosts(posts);
            });
        });
    });    
    
    // Loading a wall only for the favorites of the user
    $("#favorites").click(function(event) {
        $("#controls").contents().remove();
        resetActive();
        $("#members").parent().addClass("active");
        $("#contents").load("/skyline_rest/content/wall.html", function() {
            skyline.getPostBox().getPostByFavorites().done(function(posts) {
                $.when(GUI.renderAllPosts(posts)).then(function() {
                });
            });
        });
        event.preventDefault();
    });
    
    // Swapping the ID of the login/logout button
    $("#login").attr("id", "logout");
    
    // Show the logout dialog
    $("#logout").click(function(event) {
        $("#controls").contents().remove();
        $("#contents").load("/skyline_rest/authorize/logout.html");
        resetActive();
        $("#logout").parent().addClass("active");
        event.preventDefault();
    });
    
    // Resets the "clicked" style of all navigation buttons
    function resetActive() {
        $(".menu-item").parent().removeClass("active");
    };
});



