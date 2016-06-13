/**
 *
 */
var jq = jQuery.noConflict();

jq(function() {
	jq('form#postComment').submit(function(event) {
		event.preventDefault();

		var form = jq(this);
		var button = form.find('button');

		var userId = jq('#postComment [id = userId]').val();

		var postId = jq('#postComment [id = postId]').val();
		var branchId = jq('#postComment [id = branchId]').val();
		var departmentId = jq('#postComment [id = departmentId]').val();
		var text = jq('#postComment [id = comment-box]').val();

		var data = JSON.stringify({
			userId : parseInt(userId),
			postId : parseInt(postId),
			branchId : parseInt(branchId),
			departmentId : parseInt(departmentId),
			text : text
		});
		alert("text:" + text);
		alert(data);
		console.log(form.attr("action"));

		jq.ajax({
			url : form.attr('action'),
			contentType: 'application/json; charset=utf-8',
			type : form.attr('method'),
			dataType: 'json',
			data : data,
			timeout : 10000,

			beforeSend : function(xhr, settings) {
				button.attr('disabled', true);
			},

			complete : function(xhr, settings) {
				button.attr('disabled', false);
			},

			success : function(result, textStatus, xhr) {
				form[0].reset();
				alert('OK');
			},

			error : function(xhr, textStatus, error) {
				alert("NG...");
				console.log("XHR:" + xhr);
				console.log("textStatus:" + textStatus);
				console.log("Error:" + error);
			}

		});

	});
});
