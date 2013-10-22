/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
            $.when(GUI.renderAllPosts(posts)).then(function() {
                console.log("Rendered posts and I am done!");
            });
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
            skyline.getMemberRegistry().isFavorite(memberId).done(function(str) {
                
                $glyph.show();
                //I don't know... wat?...
                //TODO: Alter color on star
                if (str) {
                    $glyph.click(removing);
                }
                else {
                    $glyph.click(adding);
                }

            });

            function adding() {
                $glyph.hover(function() {
                    $(this).toggleClass('yellow');
                }, function() {
                    $(this).toggleClass('yellow');
                });
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
                //$glyph.unbind('mouseenter mouseleave');
                $glyph.attr('class', "glyphicon glyphicon-star yellow");
                var glyphId = $glyph.attr('id');
                var memberId = glyphId.substr(glyphId.indexOf("mber_") + 5);
                skyline.getMemberRegistry().unFavorite(memberId).done(function() {
                    $glyph.unbind("click");
                    $glyph.click(adding);
                });
            }
        }
    })
};
