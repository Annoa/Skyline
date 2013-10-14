/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

asyncTest("MemberRegistry.count", function() {
    var deferred = skyline_member.getMemberRegistry().getCount();
    console.log(deferred + "hih")
    deferred.done(function(nMembers) {
        console.log("nMember = " + nMembers.value);
        ok(nMembers.value === 1, "Test passed");
        //ok( true, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});

/*asyncTest("MemberRegistry.getRange", function() {
    var deferred = skyline_member.getMemberRegistry().
    deferred.done(function(members) {
        console.log(members.length);
        ok(members.length === 2, "Test passed");
        //ok( true, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});*/