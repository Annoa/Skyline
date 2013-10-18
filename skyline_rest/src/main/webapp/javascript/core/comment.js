
var CommentBox = function(baseUri) {
    this.baseUri = baseUri;
};



CommentBox.prototype = (function() {

    return {
        getRootCommentsForPost: function(postId) {
            return $.getJSON(this.baseUri + "/" + postId);
        },
        
        getAllOnPost: function() {
            return $.getJSON(this.baseUri);
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
            return $.getJSON(this.baseUri + "/" + id);
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
