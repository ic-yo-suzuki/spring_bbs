/**
 *
 */

jq(function($){
	$("form#narrowingForm").submit(function(event){
		var form = $(this);
		var category = form.find("select#category");
		var startDate = form.find("input#dateStart");
		var endDate = form.find("input#dateEnd");

		var valid = false;

		valid |= checkCategory(category);
		valid |= checkDate(startDate, endDate);

		if(!valid && form.find("p").html().length == 0){
			alert("検索条件を指定してください");
			event.preventDefault();
		}
	});

	function checkCategory(category){
		var value = category.val();
		if(value.length == 0){
			return false;
		}else{
			return true;
		}

	}

	function checkDate(startDate, endDate){
		var start = startDate.val();
		var end = endDate.val();

		if(start.length == 0 && end.length == 0){
			return false;
		}else{
			return true;
		}

	}

});