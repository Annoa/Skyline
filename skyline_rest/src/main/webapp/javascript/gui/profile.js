/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    
    console.log("profile page running");
    
    skyline_member.getMemberRegistry().find(1).done(renderMember);
    
    function renderMember(member) {
        $("#member-contents #member-name").contents().remove();
        $("#member-contents #member-name").append(member.name);
        $("#member-contents #member-signUpDate").contents().remove();
        $("#member-contents #member-signUpDate").append(new Date(member.date));
    };
    
});
