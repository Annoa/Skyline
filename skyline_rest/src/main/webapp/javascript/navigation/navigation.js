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
    $(".search-submit-button").click(function() {
        var memberName = $(".typeahead").val();
        skyline.getMemberRegistry().getMemberByName(memberName).done(function(member) {
           memberPage(member.id); 
        });
    });
    
    $("#wall").parent().addClass('active');
    renderMainWall();
    
    $("#wall").click(function(event) {
        $(".menu-item").parent().removeClass("active");
        $("#wall").parent().addClass('active');
        renderMainWall();
    });
    
    $("a.author-link").click(function(event) {
        
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
};
