/* 
 *  Test basic AJAX call using JQuery
 *  author hajo
 */

// Possible test with curl first 
    
asyncTest( "Ajax.get", function() {
    var baseUri = "http://localhost:8080/skyline_rest/rs/members";//localHost 8080?
    var deferred = $.get(baseUri);   
    deferred.done(function(xml){
        ok( true, "Test passed");
        start();
    }); 
    deferred.fail(function(){
        ok( false, "Test failed (is Server up??)");
        start();    
    });
});



