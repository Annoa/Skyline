/* 
 * The skyline model as a Singleton
 */
// Global
var skyline = (function(){
    
    var baseUri = "http://localhost:8080/skyline_rest/rs/";  
    var posts = new PostBox(baseUri + "posts");
    //var members = new MemberResource(baseUri);
    // etc ...
    
    return {
        getPostBox : function(){
            return posts;
        },
        //getMember..
        getBaseUri : function(){
            return baseUri;
        }
    };    
})();
