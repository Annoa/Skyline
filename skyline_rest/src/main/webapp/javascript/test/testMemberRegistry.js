/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

asyncTest("MemberRegistry.count", function() {
    var deferred = skyline.getMemberRegistry().getCount();
    console.log(deferred + "hih")
    deferred.done(function(nMembers) {
        ok(nMembers.value === 4, "Test passed");
        //ok( true, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});

asyncTest("MemberRegistry.getRange", function() {
    var deferred = skyline.getMemberRegistry().getRange(0,2);
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