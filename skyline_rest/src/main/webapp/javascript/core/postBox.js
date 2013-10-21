
var PostBox = function( baseUri ){
     this.baseUri = baseUri;
 };



PostBox.prototype = (function (){
    
    return {
        
        vote: function(postId, positive) {
            return $.ajax({
                type: 'POST',
                url: this.baseUri + "/vote?postId=" + postId + "&positive=" + positive
            });
        },
                
        getAuthor: function(postId) {
            return $.getJSON(this.baseUri + "/author/" + postId);
        },
        
        getAll: function() {
            console.log("getAll");
            return $.getJSON(this.baseUri);
        },

        add: function( post ){
            console.log("add Post");
            return $.ajax({
                type: 'POST',
                url: this.baseUri,
                data: post
            });
        },
                
        remove: function ( id ){
            return $.ajax({
                type: 'DELETE',
                url: this.baseUri + "/" + id
            });
        },
        
        find: function( id ){
            return $.getJSON(this.baseUri + "/" + id);
        },
        
        getRange: function ( fst, max ){
                            //TODO
                            console.log("HAJ");
            return $.getJSON(this.baseUri + "/range?first=" + fst + "&last=" + max);
        },

        getCount: function(){
            return $.getJSON(this.baseUri + "/count");
        },
        
        update: function ( post ){
            return $.ajax({
                type: 'PUT',
                url: this.baseUri + "/" + post.id,
                data: post
            });
        },
        getPostByFavorites: function ( ){
            return $.getJSON(this.baseUri + "/favorites");
        }
        //TODO
//        getPostByMember: function ( post ) {
//            
//        }
    };
}());
