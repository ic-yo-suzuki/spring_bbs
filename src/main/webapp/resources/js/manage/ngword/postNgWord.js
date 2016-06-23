/**
 *
 */

var jq = jQuery.noConflict();

jq(function($) {
	$("form#postNgWord").submit(function(event) {

		var valid = false;
		var form = $(this);
		var word = form.find("input#word").val();
		var messageArea = form.find("div#messageArea");
		var button = form.find("button#post");
		var data;

		valid = true;
		if(word.match(/\S/g) && word.length != 0){
			data = JSON.stringify({
				value : word
			});
			valid = true;
			console.log("ok");
		}else{

			valid = false;
			console.log("empty");
		}

		event.preventDefault();
		console.log("Data Destination : " + form.attr("action"));
		console.log("Method : " + form.attr('method'));
		console.log("Data : " + data);

		if (valid) {
			sendData(form, button, data);
		} else {
			messageArea.html("追加するNGワードを入力してください");
			form.find("input#word").css("background", "pink");
			return;
		}
	});
});