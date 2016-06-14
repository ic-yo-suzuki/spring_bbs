/**
 *
 */

jq(function() {
	jq('form#deleteMesssage').submit(function(event) {
		event.preventDefault();

		var form = jq(this);
		var button = form.find('button');

		var postId = $("deleteMessageId").value;
		console.log(postId);
		var data = JSON.stringify({
			id : parseInt(postId)
		});
		alert(data);
		console.log("Data Destination : " + form.attr("action"));
		console.log("Method : " + form.attr('method'));

		sendData(form, button, data);

	});
});
