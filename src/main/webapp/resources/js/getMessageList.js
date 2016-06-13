/**
 *
 */

var jq = jQuery.noConflict();

jq(function($) {
	$.ajax({
		type : "GET",
		url : "/Spring_BBS/getMessageList/",
		dataType : "json",
		contentType: "application/json; charset=UTF-8",
		success : function(data) {
			success(data, "message");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			error(XMLHttpRequest, textStatus, errorThrown);
		}
	});
	$.ajax({
		type: "GET",
		url: "/Spring_BBS/getCommentList/",
		dataType:"json",
		contentType: "application/json; charset=UTF-8",
		success: function(data) {
			success(data, "comment");

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			error(XMLHttpRequest, textStatus, errorThrown);
		}
	})
});

function success(data, message) {
//	alert("success:" + message + ":" +  data);


}
function error(XMLHttpRequest, textStatus, errorThrown) {
//	alert("error:" + XMLHttpRequest);
//	alert("status:" + textStatus);
//	alert("errorThrown:" + errorThrown);
}