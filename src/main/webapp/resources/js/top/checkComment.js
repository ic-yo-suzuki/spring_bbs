/**
 *
 *
 */

function checkComment($this){
	$("#commentHeader").css('color', 'black');
	$("#comment-box").css('background', 'white');

	console.log($this);

	if($this.value.length > 500){
		document.getElementById("commentHeader").innerHTML = ($this.value.length - 500) + "文字オーバーしています(" + $this.value.length + "文字)";
		$("#commentHeader").css('color', 'red');
		$("#comment-box").css('background', 'pink');
	}else{;
		document.getElementById("commentHeader").innerHTML = "あと" + (500 - $this.value.length) + "文字入力できます(" + $this.value.length + "文字)";
	}
}