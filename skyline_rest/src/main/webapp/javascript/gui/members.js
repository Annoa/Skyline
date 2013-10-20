/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
(function($,memberroot){
    
    console.log("members running");
    

    function renderMembers(members) {
        $("#contents").contents().remove();
        console.log("in rendermembers");
        $(members).each(function() {
            $("#contents").append("<tr><td><a href='/skyline_rest/rs/members/"+this.id+"'>" + this.id
                    +": "+ this.name
                    + "</a></td></tr>");        
        });
    }
    memberroot.memberGUI = {
        renderMembers:renderMembers
    };
    
})(jQuery, window);