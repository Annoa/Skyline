/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var MemberRegistry = function(baseUri) {
    this.baseUri = baseUri;
};

MemberRegistry.prototype = (function() {

    return {
        
        search: function(searchString) {
            return $.getJSON(this.baseUri + "/search?searchString=" + searchString)
        },
        
        find: function(id) {
            return $.getJSON(this.baseUri + "/" + id);
        },
        getAll: function() {
            return $.getJSON(this.baseUri);
        },
        add: function(member) {
            return $.ajax({
                type: 'POST',
                datatype: 'json',
                url: this.baseUri,
                data: member
            });
        },
        remove: function(id) {
            return $.ajax({
                type: 'DELETE',
                datatype: 'json',
                url: this.baseUri
            });
        },
        update: function(member) {
            return $.ajax({
                type: 'PUT',
                datatype: 'json',
                url: this.baseUri + "/" + member.id,
                data: member
            });
        },
        getRange: function(first, last) {
            return $.getJSON(this.baseUri + "/range?first=" + first + "&last=" + last);
        },
        getCount: function() {
            return $.getJSON(this.baseUri + "/count");
        }
    };

}());
