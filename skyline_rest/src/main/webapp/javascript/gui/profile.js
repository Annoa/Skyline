/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//$(function() {
//    
//    console.log("profile page running");
//    
//    var memberId = 1;
//    
//    skyline_member.getMemberRegistry().find(1).done(renderMember);
//    
//    function renderMember(member) {
//        $("#member-contents #member-name").contents().remove();
//        $("#member-contents #member-name").append(member.name);
//        $("#member-contents #member-signUpDate").contents().remove();
//        $("#member-contents #member-signUpDate").append(new Date(member.date));
//    };
//    
//});

window.renderProfileForMember = function(member) {
    
    skyline.getMemberRegistry().find(member.id).done(function(member) {
        renderMember(member);
    });
    
    function renderMember(member) {
        var joinText = "Joined on ";
        $("#member-contents #member-name").contents().remove();
        $("#member-contents #member-name").append(member.name);
        $("#member-contents #member-signUpDate").contents().remove();
        $("#member-contents #member-signUpDate").append(joinText + new Date(member.date));
    };
};