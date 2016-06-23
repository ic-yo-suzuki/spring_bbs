/**
 *
 *
 */

function checkComment($this) {
	var messageArea = jq($this).siblings('div');

	jq(messageArea).css('color', 'green');
	jq($this).css('background', 'white');

	if ($this.value.length > 500) {
		messageArea.html(($this.value.length - 500) + "文字オーバーしています(" + $this.value.length + "文字)");
		jq(messageArea).css('color', 'red');
		jq($this).css('background', 'pink');
	}else if($this.value.length === 0){
		jq(messageArea).css('color', 'black');
		jq(messageArea).html("");
		jq($this).css('background', 'white');
	}else if($this.value.length > 469){
		jq($this).css('background', '#FFFF99');
		jq(messageArea).html("あと" + (500 - $this.value.length) + "文字入力できます(" + $this.value.length + "文字)");
	}
	else {
		jq(messageArea).html("あと" + (500 - $this.value.length) + "文字入力できます(" + $this.value.length + "文字)");
	}

}

