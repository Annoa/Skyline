/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var skyline_comments = (function(){
    
    var baseUri = "http://localhost:8080/skyline_rest/rs/";  
    var comments = new CommentBox(baseUri + "posts/comments");
    
    
    // etc ...
    
    return {
      
        //getMember..
        getCommentBox: function() {
            return comments;
        },
                
        getBaseUri : function(){
            return baseUri;
        }
    };    
})();


