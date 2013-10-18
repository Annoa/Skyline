/* 
 * The skyline model as a Singleton
 */
// Global
var skyline = (function(){
    
    var baseUri = "http://localhost:8080/skyline_rest/rs/";  
//    var members = new MemberRegistry(baseUri + "members");
    var posts = new PostBox(baseUri + "posts");
    
    // etc ...
    
    return {
        //need to change this doing this because 
        //post returns exception!
        getPostBox : function(){
            console.log("inside getPostBox");
            return posts;
        },
        //getMember..
//        getMemberRegistry: function() {
//            return members;
//        },
        
        getBaseUri : function(){
            return baseUri;
        }
    };    
})();
