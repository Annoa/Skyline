/**
 * @author annoa
 */


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
        $(".glyphicon-star").attr('id', "glyphicon_member_" + member.id);
    };
};

$(document).ready(function() {
   $('.glyphicon-star').hide();
});