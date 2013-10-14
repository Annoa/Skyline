/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var skyline_member = (function(){
    
    var baseUri = "http://localhost:8080/skyline_rest/rs/";  
    var members = new MemberRegistry(baseUri + "members");
    
    
    // etc ...
    
    return {
      
        //getMember..
        getMemberRegistry: function() {
            return members;
        },
                
        getBaseUri : function(){
            return baseUri;
        }
    };    
})();


