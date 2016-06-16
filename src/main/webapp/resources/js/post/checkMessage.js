/**
 *
 */

function checkText($this){
	colorChange('normal', "#textHeader", "#text");
	if($this.value.length > 1000){
		document.getElementById("textHeader").innerHTML = ($this.value.length - 1000) + "文字オーバーしています(" + $this.value.length + "文字)";
		colorChange('caution', "#textHeader", "#text");
	}else if($this.value.length == 0){
		document.getElementById("textHeader").innerHTML = "本文を入力してください";
		colorChange('caution', "#textHeader", "#text");
	}else{
		document.getElementById("textHeader").innerHTML = "あと" + (1000 - $this.value.length) + "文字入力できます(" + $this.value.length + "文字)";
	}
}

function checkTitle($this){
	colorChange('normal', "#titleHeader", "#title");
	if($this.value.length > 50){
		document.getElementById("titleHeader").innerHTML = ($this.value.length - 50) + "文字オーバーしています(" + $this.value.length + "文字)";
		colorChange('caution', "#titleHeader", "#title");

	}else if($this.value.length == 0){
		document.getElementById("titleHeader").innerHTML = "タイトルを入力してください";
		colorChange('caution', "#titleHeader", "#title");
	}else{
		document.getElementById("titleHeader").innerHTML = "あと" + (50 - $this.value.length) + "文字入力できます(" + $this.value.length + "文字)";
	}

}


function colorChange(mode, headerDom, textDom){
	if(mode === 'normal'){
		$(headerDom).css('color', 'black');
		$(textDom).css('background', 'white');
	}else if(mode === 'caution'){
		$(headerDom).css('color', 'red');
		$(textDom).css('background', 'pink');
	}

}
