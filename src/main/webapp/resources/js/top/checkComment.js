/**
 *
 *
 */

function checkComment($this) {
	var messageArea = $($this).siblings('div');

	$(messageArea).css('color', 'green');
	$($this).css('background', 'white');

	if ($this.value.length > 500) {
		messageArea.html(($this.value.length - 500) + "文字オーバーしています(" + $this.value.length + "文字)");
		$(messageArea).css('color', 'red');
		$($this).css('background', 'pink');
	}else if($this.value.length === 0){
		$(messageArea).css('color', 'black');
		$(messageArea).html("");
		$($this).css('background', 'white');
	}else if($this.value.length > 469){
		$($this).css('background', '#FFFF99');
		$(messageArea).html("あと" + (500 - $this.value.length) + "文字入力できます(" + $this.value.length + "文字)");
	}
	else {
		$(messageArea).html("あと" + (500 - $this.value.length) + "文字入力できます(" + $this.value.length + "文字)");
	}

}