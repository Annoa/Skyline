$(document).ready(function() {
    
    $("#sign-in")
            .button()
            .click(function() {
        console.log("authorization javascript körs");
        $("#add-a-member").show();
                //addMember();
    });
    
    $("#create-new-member").
            button()
            .click(function() {
        $("#become-a-member-message").contents().remove();
        var newMember = getFormDialogData();
        console.log(newMember);
        skyline.getMemberRegistry().getAll().done(function(members) {
            for (var i = 0; i<members.length; i++) {
                if (members[i].name === newMember.name) {
                    console.log("not a valid member");
                    var html = "<H2>" + newMember.name + "</H2>\n\
                                <br>\n\
                                <p> not a valid user name </p>";
                    $("#become-a-member-message").append(html);
                    return;
                    
                }
            }
            console.log("create new member");
            skyline.getMemberRegistry().add(newMember);
            var html = "<H3> Congratulations </H3><H1> " + newMember.name + "</H1> \n\
                        <br> \n\
                        <p> You are now a member of Skyline!</p>"
            $("#become-a-member-message").append(html);
        });
   
    });
    function getFormDialogData() {
        var member = {};
        member.name = $("#new-member").val();
        member.password = $("#member-password").val();    
        return member;
    };
});