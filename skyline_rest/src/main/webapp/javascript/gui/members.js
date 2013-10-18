/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    
    console.log("members running");
    
    skyline_member.getMemberRegistry().getAll().done(renderMembers);

    function renderMembers(members) {
        $("#contents").contents().remove();
        $(members).each(function() {
            $("#contents").append("<tr><td>" + this.id
                    + "</td><td>" + this.name
                    + "</td></tr>");
        });
    }
});