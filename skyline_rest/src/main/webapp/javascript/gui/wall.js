/**
 * Wall.js is covering the view-part of everything that has to do 
 * with the wall. As you can see in the methods below this is for 
 * example things as rendering the posts and their comments.
 * 
 * @param $ JQuery
 * @param root window
 * 
 * @returns {undefined}
 */
(function($,root){

    
    
    /**
     * Function rendering all posts on the wall.
     * 
     * @param {type} posts
     * @returns {undefined}
     */
    function renderAllPosts(posts) {

        $("#postlist").contents().remove();
        var htmlText = '';
        for(var i=0; i<posts.length; i++){
            htmlText += convertPostToHTML(posts[i]);
        }
        
        $('#postlist').append(htmlText);
        
        $("[id^=comment-button_]")
            .button()
            .click(function(){
                var targetId = $(this).attr('id');
                var target = targetId.substr(targetId.indexOf("_")+1);
                var targetDiv = $('[id="comments-post_' + target + '"]');
                if ($(targetDiv).is(':empty')) {
                    renderComments(target);
                } else {
                    $(this).html("Show comments");
                    $(targetDiv).contents().remove();
                    $.cookie('post-comment-show_'+target, false);
                }

            });
            
        $("[id^=comment-add-button_]")
            .button()
            .click(function() {
                var targetId = $(this).attr('id');
                var target = targetId.substr(targetId.indexOf("_")+1);
                var targetDiv = $('[id="add-commentbox-post_' + target + '"]');
                if ($(targetDiv).is(':empty')) {
                    var add = addCommentBox(target);
                    $(targetDiv).append(add);
                    // If no comments where gotten we don't change name on the toggle-comments button
                    var tar = "#add-commentbox-post_" + target;
                    $(tar).find(".comment-save-button")
                                .button()
                                .click(function() {
                            var comment = {};
                            
                            comment.text = $(tar).find(".comment-textarea").val();
                            comment.postId = target;
                            comment.parentId = -1;
                            
                            $(tar).find(".comment-textarea").val("");
                            $("#add-commentbox-post_" + target).hide();
                            
                            skyline.getCommentBox().add(comment).done(function() {
                                $.cookie('post-comment', true);
                                $("#comment-button_"+target).click().click();
                            });
                        });
                    $(tar).find(".comment-cancel-button")
                                .button()
                                .click(function() {
                            $(tar).find(".comment-textarea").val("");
                            $("#add-commentbox-post_" + target).hide();
                        });
                } else {
                    if ($(targetDiv).is(':hidden')) {
                        $(targetDiv).show();
                    } else {
                        $(targetDiv).hide();
                    }
                }
            });
            
        $("[id^=post_]").find('.vote-up').click(function() {
            var postIdentifier = $(this).parents('li').attr('id');
            var postId = postIdentifier.substr(postIdentifier.indexOf('_')+1);
            if ($(this).attr('class').indexOf('orangered') === -1) {
                $(this).addClass('orangered');
            }
            $(this).html($(this).html() * 1 + 1);
            skyline.getPostBox().vote(postId, true);
        });
        
        $("[id^=post_]").find('.vote-down').click(function() {
            var postIdentifier = $(this).parents('li').attr('id');
            var postId = postIdentifier.substr(postIdentifier.indexOf('_')+1);
            if ($(this).attr('class').indexOf('periwinkle') === -1) {
                $(this).addClass('periwinkle');
            }
            $(this).html($(this).html() * 1 + 1);
            skyline.getPostBox().vote(postId, false);
        });
        
        $("[id^=post_]").each(function() {
            var targetLi = $(this).attr('id');
            var postId = targetLi.substr(targetLi.indexOf("_")+1);
            skyline.getPostBox().getAuthor(postId).done(function(author) {
                $("#post_"+postId).find('.author').html('<a class="author-link" data-author-id="'+author.id+'" href="#">' + author.name + '</a>');
                $("#post_"+postId).find('.author').find('.author-link').click(function() {
                            memberPage($(this).data("author-id"));
                });
            });     
        });
        
        /*
         * This checks for cookies that say if 
         * we should show comments or not on page reload
         */
        $("[id^=post_]").each(function() {
            
            var targetLi = $(this).attr('id');
            var postId = targetLi.substr(targetLi.indexOf("_")+1);
            if ($.cookie('post-comment-show_'+postId) === "true") {
                $("#"+targetLi).find('[id=comment-button_'+postId+']').click();
            };
        });
        
        function addCommentBox(post) {
            var html = '<form id="add-commentbox-post_'+ post +'" class="write-post-form" >'
                + '<div class="input-group">'
                    +'<textarea class="form-control input-block-level comment-textarea" placeholder="Enter comment text here..."/>'
                +'</div>'
                +'<div class="btn-group-xs">'
                    +'<button class="btn btn-default btn-success comment-save-button" type="button" >Save</button>'
                    +'<button class="btn btn-default btn-danger comment-cancel-button" type="button" >Cancel</button>'
                +'</div>'
            +'</form>';
            return html;
        }
    }   
    
    /**
     * Function rendering comments of the posts and comments of the comments.
     * 
     * @param {type} post
     * @returns {undefined}
     */
    function renderComments(post) {
        
        skyline.getCommentBox().getRootCommentsForPost(post).done(commentDraw);
        
        function commentDraw(comments) {
            
            if ($(comments).is(':empty')) {
                $("[id=comment-button_"+post+"]").html("Hide comments");
                $.cookie('post-comment-show_'+post, true);
            } else {
                $("[id=comment-button_"+post+"]").html("Show comments");
                $.cookie('post-comment-show_'+post, false);
            }
            if ($('[id="comments-post_' + post + '"]').is(':empty')) {
                var even = true;
                var htm = '';
                $.each(comments, function(i, el) {
                    recurseChildren(i, el);
                });
                function recurseChildren(i, parent) {                 
                    var color = ((even) ? 'even' : 'uneven');
                    var bug = parent.votes === undefined;
                    var voteup = (bug) ? parent.upVotes : parent.votes.upVote;
                    var votedown = (bug) ? parent.downVotes : parent.votes.downVote;
                    htm += '<div class="comment comment-' + color + ' comment_'+ parent.id +'"><div>\n\
                    <span class="glyphicon glyphicon-arrow-up vote-up">'+ voteup +'</span>\n\
                    <span class="glyphicon glyphicon-arrow-down vote-down">'+ votedown +'</span>'
                    + '<span class="medium-distance author"></span></div><div>'
                    + '<p class="comment-text">'+ parent.commentText + '</p></div>'
                    + '<div class="btn-group-xs">'
                    + '<button class="btn btn-default comment-reply-button" type="button">Reply</button>'
                    + '</div>'
                    + '<div class="add-commentbox-post_' + post + '-comment_' + parent.id + '"></div>';
                    if (undefined !== parent.childComments){
                        even = !even;
                        $.each(parent.childComments, function(z, child) {
                            recurseChildren(z, child);
                        });
                        even = !even;
                    }
                    htm +='</div>';
                };
                var postDiv = $('[id="comments-post_' + post + '"]');
                $(postDiv).append(htm);
                
                $(postDiv)
                        .find(".comment-reply-button")
                        .button()
                        .click(function() {
                    var targetClasses = $(this).parent().parent().attr('class');
                    var targetId = targetClasses.substr(targetClasses.indexOf("_")+1);
                    
                    var targetDiv = $(this).parent().parent().find("[class^=add-commentbox-post_]").first();
                    if ($(targetDiv).is(":empty")) {
                        var htm = addCommentBoxWithParent(targetId);
                        $(targetDiv).append(htm);
                        
                        $(targetDiv).find(".comment-save-button")
                                .button()
                                .click(function() {
                            var comment = {};
                            
                            comment.text = $(targetDiv).find(".comment-textarea").val();
                            comment.postId = post;
                            comment.parentId = targetId;
                            
                            $(targetDiv).find(".comment-textarea").val("");
                            skyline.getCommentBox().add(comment).done(function() {
                                $("#comment-button_"+post).click().click();
                            });
                        });
                        $(targetDiv).find(".comment-cancel-button")
                                .button()
                                .click(function() {
                            $(targetDiv).hide();
                        });
                        
                    } else {
                        if ($(targetDiv).is(':hidden')) {
                            $(targetDiv).show();
                        } else {
                            $(targetDiv).hide();
                        }
                    }
                });
                
                $(postDiv).find('.vote-up').click(function() {
                    var commentIdentifier = $(this).parent().parent().attr('class');
                    var commentId = commentIdentifier.substr(commentIdentifier.indexOf('comment_')+7+1);
                    if ($(this).attr('class').indexOf('orangered') === -1) {
                        $(this).addClass('orangered');
                    }
                    $(this).html($(this).html() * 1 + 1);
                    skyline.getCommentBox().vote(commentId, true);
                });
                
                $(postDiv).find('.vote-down').click(function() {
                   var commentIdentifier = $(this).parent().parent().attr('class');
                    var commentId = commentIdentifier.substr(commentIdentifier.indexOf('comment_')+7+1);
                    if ($(this).attr('class').indexOf('periwinkle') === -1) {
                        $(this).addClass('periwinkle');
                    }
                    $(this).html($(this).html() * 1 + 1);
                    skyline.getCommentBox().vote(commentId, false); 
                });
                
                ($(postDiv).find('div.comment').each(function() {
                    var classText = $(this).attr('class');
                    var commentId = classText.substr(classText.indexOf("comment_")+8);
                    skyline.getCommentBox().getAuthor(commentId).done(function(author) {
                        // In order to find by the full class name we replace (all) spaces with dots
                        var realClassText = classText.replace(/ /g,'.');
                        $(postDiv).find("."+realClassText).find('.author').first().append('<a class="author-link" data-author-id="'+author.id+'" href="#">' + author.name + '</a>');
                        $(postDiv).find("."+realClassText).find('.author-link').click(function() {
                            memberPage($(this).data("author-id"));
                        });
                    });
                }));
                
                function addCommentBoxWithParent(parentComment) {
                    var commentHtml = '<form id="add-commentbox-post_'+ post +'-parent_' + parentComment + '" class="write-post-form" >'
                        + '<div class="input-group">'
                            +'<textarea class="form-control input-block-level comment-textarea" placeholder="Enter comment text here..."/>'
                        +'</div>'
                        +'<div class="btn-group-xs">'
                            +'<button class="btn btn-default btn-success comment-save-button" type="button" >Save</button>'
                            +'<button class="btn btn-default btn-danger comment-cancel-button" type="button" >Cancel</button>'
                        +'</div>'
                    +'</form>';
                    return commentHtml;
                };
            }
            return false;
        }
    };
    
    /**
     * Function rendering the added post at the bottom of the existing 
     * posts on the wall. That is, the post is added without re-rendering 
     * all the posts on the wall.
     * 
     * @param {type} post
     * @returns {undefined}
     */
    function renderAddedPost(post) {
        $('#postlist').prepend(convertPostToHTML(post));
    }
    /**
     * Function converting data from post into HTML-code.
     * 
     * @param {type} post
     * @returns {String}
     */
    function convertPostToHTML(post) {
        var d = new Date(post.date);
        var video = convertToYouTubeEmbedLink(post.postVideo);
        var videoLink = (video.length === 0) ? '' : '<p><iframe width="420" height="345" src="' + video + '">' +' </iframe>' + '</p>';
        var pictureLink = post.postPicture;
        return '<li id="post_' + post.id + '" class="post">'
                + '<h2 class="custom-title">' + post.title + '</h2>'
                + '<p class="author"></p>'
                + '<p class="custom-date">Posted on: ' + d.getFullYear() + '-' + (d.getMonth()+1) + 
                    '-' + d.getDate() + '   ' + (d.getHours() < 10 ? '0' : '') + d.getHours() + 
                    ':' + (d.getMinutes() < 10 ? '0' : '') + d.getMinutes() + '</p>' 
                + '<p class="custom-body-text">' + post.bodyText + '</p>' 
                + videoLink
                + '<br>'
                + '<img src="' + pictureLink + '">'
                + '<div class="btn-group btn-group-sm voting-div">'
                + '<button id="comment-button_'+ post.id +'" class="btn btn-default">Show comments</button>'
                + '<button id="comment-add-button_'+ post.id +'" class="btn btn-default">Reply</button>'
                + '<span class="voting-span">'
                + '<span class="glyphicon glyphicon-arrow-up vote-up">' + post.upVotes + '</span>'
                + '<span class="glyphicon glyphicon-arrow-down vote-down">' + post.downVotes + '</span>'
                + '</span>'
                + '</div>'
                + '<div id="add-commentbox-post_'+post.id+'" class="commentbox"></div>'
                + '<div id="comments-post_'+post.id+'" class="commentbox"></div>'
                + '</li>';
    }
    /**
     * Function converting a standard youtube watch-link into a youtube 
     * embedded link.
     * 
     * @param {type} link
     * @returns {String}
     */
    function convertToYouTubeEmbedLink (link) {
        var videoSuffix = link.substring(link.length - 11, link.length);
        if(link.search("youtube.com/watch")!==-1){
            return "http://www.youtube.com/embed/" + videoSuffix;
        }
        else{
            return "";
        }
    }
    
    root.GUI = {
        renderComments: renderComments,
        renderAllPosts: renderAllPosts,
        renderAddedPost: renderAddedPost,
        convertPostToHTML: convertPostToHTML,
        convertToYouTubeEmbedLink: convertToYouTubeEmbedLink
    };
    
})(jQuery, window);
