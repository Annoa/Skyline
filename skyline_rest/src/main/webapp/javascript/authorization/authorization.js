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
        skyline.getMemberRegistry().add(newMember).done(function(member) {
            console.log(member);
        });
//        skyline_member.getMemberRegistry().getCount().done(function(nMembers) {
//            console.log(nMembers.value);
//        });
    }
    function getFormDialogData() {
        var member = {};
        member.name = $("#new-member").val();
        return member;
    }
});



