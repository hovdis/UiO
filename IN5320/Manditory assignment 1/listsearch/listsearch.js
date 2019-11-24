var searchList = [];
window.onload = function() { // Function runs when entering the page
	liste = ["Java", "Euler", "C", "Shell", "Lisp", "Scheme", "Simula", "Smalltalk"];
	applyFunc(liste, appendString);
};

function appendString(word){
	var listObject = document.getElementById("theList2");
	if(listObject !== null){
		var wordList = document.createElement("li");
		var child = document.createTextNode(word);
		wordList.appendChild(child);
		searchList.push(word);
		listObject.appendChild(wordList);
	}
}

function applyFunc(list, func){
	for(var i = 0; i<list.length; i++){
		func(list[i]);
	}
}

function startsSame(element, searchWord){
	if(element.toLowerCase().startsWith(searchWord.toLowerCase())){
		return true;
	}
	return false;
}

function listStartsSame(list, searchWord){
	var newList = [];
	for(var i = 0; i<list.length;i++){
		if(startsSame(list[i], searchWord)){
			newList.push(list[i]);
		}
	}
	return newList;
}

function listAfterSearch(keyStroke){
	var unorderedList = document.getElementById("theList2");
	var searchWord = document.getElementById("search").value + String.fromCharCode(keyStroke.which).toLowerCase();
	if(event.keyCode == 8){
		var deleted = searchWord.slice(0, -2);
		searchWord = deleted;
	}
	var updatedList = listStartsSame(searchList, searchWord);
	unorderedList.innerHTML = "";

	for(var i = 0;i<updatedList.length;i++){
		var lo = document.createElement("li");
		lo.appendChild(document.createTextNode(updatedList[i]));
		unorderedList.appendChild(lo);
	}
}
