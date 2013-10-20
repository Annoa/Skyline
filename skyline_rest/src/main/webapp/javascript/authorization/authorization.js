/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    $("#sign-in")
            .button()
            .click(function() {
        console.log("authorization javascript körs");
        addMember();
    });

    function addMember() {
        var newMember = getFormDialogData();
        console.log(newMember);
        skyline_member.getMemberRegistry().add(newMember);
        skyline_member.getMemberRegistry().getCount().done(function(nMembers) {
            console.log(nMembers.value);
        });
    }
    function getFormDialogData() {
        var member = {};
        member.name = $("#newMember").val();
        return member;
    }
});



