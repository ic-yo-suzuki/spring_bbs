/**
 *
 */

$(function() {
	$('form#deleteMessage').submit(function(event) {
		event.preventDefault();

		var form = $(this);
		var button = form.find('button');
		var postId = form.attr('value');	// 削除対象の投稿IDを取得(formのvalue属性に定義)
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
