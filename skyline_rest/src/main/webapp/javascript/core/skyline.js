/* 
 * The skyline model as a Singleton
 */
// Global
var skyline = (function(){
    
    var baseUri = "http://localhost:8080/skyline_rest/rs/";  
//    var members = new MemberRegistry(baseUri + "members");
    var posts = new PostBox(baseUri + "posts");
    var members = new MemberRegistry(baseUri + "members");
    var comments = new CommentBox(baseUri + "comments/post");
    
    // etc ...
    
    return {
        getPostBox : function(){
            console.log("inside getPostBox");
            return posts;
        },
                
        getCommentBox: function(){
            return comments;
        },
                
        getMemberRegistry: function() {
            return members;
        },
        
        getBaseUri : function(){
            return baseUri;
        }
    };    
})();
