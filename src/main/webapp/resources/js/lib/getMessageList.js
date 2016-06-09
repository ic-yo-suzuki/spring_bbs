/**
 *
 */

var jq = jQuery.noConflict();

jq(function($) {
	$.ajax({
		type : "GET",
		url : "getMessageList",
		dataType : "json",
		success : function(data) {
			success(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			error(XMLHttpRequest, textStatus, errorThrown);
		}
	});
});

function success(data) {
	alert("success:" + data);

}
function error(XMLHttpRequest, textStatus, errorThrown) {
	alert("error:" + XMLHttpRequest);
	alert("status:" + textStatus);
	alert("errorThrown:" + errorThrown);
}