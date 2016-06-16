/**
 *
 */

var category = "";
function categorySelectChanged() {

	radio = document.getElementsByName('categorySelect');

	if (radio[0].checked) {
		document.getElementById('createCategory').style.display = "none";
		document.getElementById('selectCategory').style.display = "";
		category = postMessageForm.selectCategory.value;
	} else if (radio[1].checked) {
		document.getElementById('createCategory').style.display = "";
		document.getElementById('selectCategory').style.display = "none";
		category = postMessageForm.createCategory.value;
	}

}

function setCategory() {
	radio = document.getElementsByName('categorySelect');
	if (radio[0].checked) {
		category = postMessageForm.selectCategory.value;
	} else if (radio[1].checked) {
		category = postMessageForm.createCategory.value;
	}
	document.getElementById('category').value = category;
}

// オンロードさせ、リロード時に選択を保持
window.onload = categorySelectChanged;