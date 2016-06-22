/**
 *
 */

$(function() {
	$('form#deleteComment').submit(function(event) {
		event.preventDefault();

		var form = $(this);
		var button = form.find('button');

		var postId = form.attr('value');
		console.log(postId);
		var data = JSON.stringify({
			value : parseInt(postId)
		});
//		alert(data);
		console.log("Data Destination : " + form.attr("action"));
		console.log("Method : " + form.attr('method'));

		sendData(form, button, data);

	});
});
