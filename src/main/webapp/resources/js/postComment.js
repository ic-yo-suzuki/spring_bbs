/**
 *
 */
jq("postComment").onclick = (function($) {
	var button = $(this);
	button.attr("disabled", true);

	var comment = {
		userId : $("#userId").val(),
		postId : $("#postId").val(),
		text : $("#text").val(),
		insertDate : "1970-01-01 00:00:00"
	};

	console.log(comment);

});
