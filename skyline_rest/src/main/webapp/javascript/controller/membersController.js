

$(function(){
    console.log("memberscontroller is running");
    
    skyline_member.getMemberRegistry().getAll().done(memberGUI.renderMembers);
    
    
});
