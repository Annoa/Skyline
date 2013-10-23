/**
 * @author annoa
 */

// This sets the autocompletion in the searchfield to query using the AJAX uri.
$(".typeahead").typeahead([
    {
        name: 'members',
        remote: '/skyline_rest/rs/members/searchByName?string=%QUERY'
    }
]);

$(document).ready(function() {
    $(".search-submit-button").click(function(event) {
        var memberName = $(".typeahead").val();
        skyline.getMemberRegistry().getMemberByName(memberName).done(function(member) {
            memberPage(member.id);
        });
        event.preventDefault();
    });

    $("#wall").parent().addClass('active');
    renderMainWall();
    $("a.author-link").click(function(event) {
    });

    $("#wall").click(function(event) {
        $(".menu-item").parent().removeClass("active");
        $("#wall").parent().addClass('active');
        renderMainWall();

    });


});

renderMainWall = function() {
    $("#contents").load("/skyline_rest/content/wall.html", function() {
        skyline.getPostBox().getAll().done(function(posts) {
            GUI.renderAllPosts(posts)
        });
    });
};

memberPage = function(memberId) {
    $("#controls").load("/skyline_rest/content/profile.html", function() {
        // Declared in profile.js (which is loaded in profile.html
        skyline.getMemberRegistry().find(memberId).done(renderProfileForMember);
    });
    $("#contents").load("/skyline_rest/content/wall.html", function() {
        skyline.getPostBox().getPostsOfMemberByVotes(memberId).done(function(posts) {
            GUI.renderAllPosts(posts);
        });
    });
    skyline.getMemberRegistry().getUser().done(function(member) {
        if (member !== undefined) {
            var $glyph = $('.glyphicon-star');
            skyline.getMemberRegistry().isFavorite(memberId).done(function(isFavorite) {
                
                $glyph.show();
                if (isFavorite) { 
                    $glyph.unbind('mouseenter mouseleave');
                    $glyph.attr('class', "glyphicon glyphicon-star yellow");
                    $glyph.click(removing);
                }
                else {
                    $glyph.hover(function() {
                        $(this).attr('class', "glyphicon glyphicon-star yellow");
                    }, function() {
                        $(this).attr('class', "glyphicon glyphicon-star");
                    });
                    $glyph.attr('class', "glyphicon glyphicon-star");
                    $glyph.click(adding);
                }

            });

            function adding() {
                $glyph.unbind('mouseenter mouseleave');
                $glyph.attr('class', "glyphicon glyphicon-star yellow");
                var glyphId = $glyph.attr('id');
                var memberId = glyphId.substr(glyphId.indexOf("mber_") + 5);
                skyline.getMemberRegistry().addToFavorites(memberId).done(function() {
                    $glyph.unbind("click");
                    $glyph.click(removing);
                });
            }

            function removing() {
                $glyph.hover(function() {
                    $(this).toggleClass('yellow');
                }, function() {
                    $(this).toggleClass('yellow');
                });
                $glyph.attr('class', "glyphicon glyphicon-star yellow");
                var glyphId = $glyph.attr('id');
                var memberId = glyphId.substr(glyphId.indexOf("mber_") + 5);
                skyline.getMemberRegistry().unFavorite(memberId).done(function() {
                    $glyph.unbind("click");
                    $glyph.click(adding);
                });
            }
        }
    });
};
