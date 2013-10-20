
var CommentBox = function(baseUri) {
    this.baseUri = baseUri;
};



CommentBox.prototype = (function() {

    return {
        vote: function(commentId, positive) {
            return $.ajax({
                type: 'POST',
                url: this.baseUri + "/vote?commentId=" + commentId + "&positive=" +positive
            });
        },
        
        getRootCommentsForPost: function(postId) {
            return $.getJSON(this.baseUri + "/" + postId);
        },
                
        getChildComments: function(commentId) {
            return $.getJSON(this.baseUri + "/comment/" + commentId);
        },
        
        getAllOnPost: function(postId) {
            return $.getJSON(this.baseUri + "/all?postId="+ postId);
        },
                
        getAuthor: function(commentId) {
            return $.getJSON(this.baseUri + "/author/" + commentId);
        },
        
        add: function(comment) {
            return $.ajax({
                type: 'POST',
                url: this.baseUri,
                data: comment
            });
        },
        remove: function(id) {
            return $.ajax({
                type: 'DELETE',
                url: this.baseUri + "/" + id
            });
        },
        find: function(id) {
            return $.getJSON(this.baseUri + "?id=" + id);
        },
        getRange: function(fst, max) {
            //TODO
            return $.getJSON(this.baseUri + "/range?fst=" + fst + "&max=" + max);
        },
        getCount: function() {
            return $.getJSON(this.baseUri + "/count");
        },
        update: function(post) {
            return $.ajax({
                type: 'PUT',
                url: this.baseUri + "/" + post.id,
                data: post
            });
        }
        //TODO
//        getPostByMember: function ( post ) {
//            
//        }
    };
}());
