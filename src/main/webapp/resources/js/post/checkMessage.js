/**
 *
 */

function checkText($this){

	var form = $($this).parents('form');

	console.log(form);

	colorChange('normal', "#textValidCheck", "#text");
	if($this.value.length > 1000){
		document.getElementById("textValidCheck").innerHTML = ($this.value.length - 1000) + "文字オーバーしています(" + $this.value.length + "文字)";
		colorChange('error', "#textValidCheck", "#text");
	}else if($this.value.length == 0){
		document.getElementById("textValidCheck").innerHTML = "";
		colorChange('normal', "#textValidCheck", "#text");
	}else if($this.value.length > 969){
		document.getElementById("textValidCheck").innerHTML = "あと" + (1000 - $this.value.length) + "文字入力できます(" + $this.value.length + "文字)";
		colorChange('notice', "#textValidCheck", "#text");
	}else{
		document.getElementById("textValidCheck").innerHTML = "あと" + (1000 - $this.value.length) + "文字入力できます(" + $this.value.length + "文字)";
	}
}

function checkTitle($this){
	colorChange('normal', "#titleValidCheck", "#title");
	if($this.value.length > 50){
		document.getElementById("titleValidCheck").innerHTML = ($this.value.length - 50) + "文字オーバーしています(" + $this.value.length + "文字)";
		colorChange('error', "#titleValidCheck", "#title");

	}else if($this.value.length == 0){
		document.getElementById("titleValidCheck").innerHTML = "";
		colorChange('normal', "#titleValidCheck", "#title");
	}else if($this.value.length > 39){
		document.getElementById("titleValidCheck").innerHTML = "あと" + (50 - $this.value.length) + "文字入力できます(" + $this.value.length + "文字)";
		colorChange('notice', "#titleValidCheck", "#title");
	}

	else{
		document.getElementById("titleValidCheck").innerHTML = "あと" + (50 - $this.value.length) + "文字入力できます(" + $this.value.length + "文字)";
	}

}

function checkCategory($this){

}


function colorChange(mode, validDivDom, textDom){
	if(mode === 'normal'){
		$(validDivDom).css('color', 'black');
		$(textDom).css('background', 'white');
	}else if(mode === 'error'){
		$(validDivDom).css('color', 'red');
		$(textDom).css('background', 'pink');
	}else if(mode === 'notice'){
		$(textDom).css('background', '#FFFF99');
	}

}
