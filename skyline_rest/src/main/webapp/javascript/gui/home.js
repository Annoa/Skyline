/**
 * Js for index page, the tabs
 */

$(document).ready(function() {
    /**
     * On document ready running
     */
    
    $("#wall").parent().addClass('active');
    
    // Adding extra navigation now that we're logged in
    var home_navigation = '<li><a class="menu-item" id="profile" href="#">Profile</a></li>\n\
                            <li><a class="menu-item" id="favorites" href="#">Favorites</a></li>';
    $("#navbar-main").append(home_navigation);
    
    // Change function of "Log in"-button to "Log out"
    var $login = $("#login")
    $login.html("Log out");
    $login.click(function() {
        $(".menu-item").parent().removeClass('active');
        $login.parent().addClass('active');
        $("#contents").load("/skyline_rest/authorize/logout.html")
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
            })
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
    
    $("#wall").click(function(event) {
        loadWall();
    });
    
    $("#profile").click(function(event) {
        $("#contents").load("/skyline_rest/content/wall.html");
        resetActive();
        $("#wall").parent().addClass("active");
        
        event.preventDefault();
    });
    
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
    
    loggedInMemberPage = function(memberId) {
        $.when(memberPage(memberId)).then(function() {
            // ** Adding favorite button. 
            // Have to add here since it is hidden for non-logged in users
            $('.glyphicon-star').show();
            $('.glyphicon-star').hover(function() {
                    $(this).toggleClass('yellow');
                }, function() {
                    $(this).toggleClass('yellow');
            });

            $('.glyphicon-star').click(function() {
                $('.glyphicon-star').unbind('mouseenter mouseleave');
            });
        });
        
        
    };
    
    $("#login").click(function(event) {
        $("#controls").contents().remove();
        $("#contents").load("/skyline_rest/authorize/login.html");
        resetActive();
        $("#logout").parent().addClass("active");
        event.preventDefault();
    });
    
     $("#logout").click(function(event) {
        $("#controls").contents().remove();
        $("#contents").load("/skyline_rest/authorize/logout.html");
        resetActive();
        $("#logout").parent().addClass("active");
        event.preventDefault();
    });
    
    
    
    function resetActive() {
        $(".menu-item").parent().removeClass("active");
    };
});



