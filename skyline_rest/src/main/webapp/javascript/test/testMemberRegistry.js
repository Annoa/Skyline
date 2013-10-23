/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * 
 * these methods doesn´ work anymore since we don´t have any hard coded datat
 * any more so hard to check if they are correct!
 */

asyncTest("MemberRegistry.count", function() {
    var deferred = skyline.getMemberRegistry().getCount();
    deferred.done(function(nMembers) {
        ok(nMembers.value === 1, "Test passed");
        //ok( true, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});

asyncTest("MemberRegistry.getRange", function() {
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
});