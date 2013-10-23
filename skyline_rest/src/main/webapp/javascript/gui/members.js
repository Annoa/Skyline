/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
(function($,memberroot){

    function renderFavoritesPosts(posts){
        $("#fav-postlist").contents().remove();
        var html = '';
        for(var j=0;j<posts.length;j++){
            html += '';
        }
        $("#fav-postlist").append(html);
    }

    function renderMembers(members) {
        $("#contents").contents().remove();
        $(members).each(function() {
            $("#contents").append("<tr><td><a href='/skyline_rest/rs/members/"+this.id+"'>" + this.id
                    +": "+ this.name
                    + "</a></td></tr>");        
        });
    }
    memberroot.memberGUI = {
        renderFavoritesPosts:renderFavoritesPosts,
        renderMembers:renderMembers
    };
    
})(jQuery, window);