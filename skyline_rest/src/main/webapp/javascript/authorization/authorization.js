$(document).ready(function() {
    
    $("#sign-in")
            .button()
            .click(function() {
        $("#add-a-member").show();
    });
    
    $("#create-new-member").
            button()
            .click(function() {
        $("#become-a-member-message").contents().remove();
        var newMember = getFormDialogData();
        skyline.getMemberRegistry().getAll().done(function(members) {
            for (var i = 0; i<members.length; i++) {
                if (members[i].name === newMember.name) {
                    var html = "<H2>" + newMember.name + "</H2>\n\
                                <br>\n\
                                <p> not a valid user name </p>";
                    $("#become-a-member-message").append(html);
                    return;
                    
                }
            }
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