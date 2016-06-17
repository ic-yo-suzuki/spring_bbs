/**
 *
 */

function idValidate($this){
	var button = $($this).next().next();

	console.log($($this));

	if($this.value.length != 0){
		$(button).attr('disabled', 'false');
	}else{
		$(button).attr('disabled', 'true');
	}
}

function passValidate($this){
	var button = $($this).next();

	if($this.value.length != 0){
		$(button).attr('disabled', 'false');
	}else{
		$(button).attr('disabled', 'true');
	}
}