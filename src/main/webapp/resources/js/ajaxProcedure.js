/**
 *
 */

function sendData(form, button, data){
	var jq = jQuery.noConflict();
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
			location.reload();
		},

		error : function(xhr, textStatus, error) {
			alert("NG...");
			console.log("XHR:" + xhr);
			console.log("textStatus:" + textStatus);
			console.log("Error:" + error);
		}

	});
}