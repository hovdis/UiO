var ul;
var localstorageArray = [];

window.setInterval(function(){ // Function runs each second
	// parse, saa stringify
	if(localStorage.getItem("theCountries") !== null){
		localstorageArray = JSON.parse(localStorage.getItem("theCountries"));
		if(localstorageArray[0] != null){
			for(var i = 0 ; i<localstorageArray.length ; i++){
				localstorageArray[i].population += localstorageArray[i].diff;
			}
			updateList();
			}
	}
}, 1000);


function add() { // Adding new items to local storage

	var element = document.getElementById("input");
  ul = document.getElementById("thelist");
	var keyword = $(element).val();

	$.getJSON("https://jsonp.afeld.me/?url=http://api.population.io/1.0/population/"+keyword+"/today-and-tomorrow")

	.done(function(data){
		var popDiff = (data.total_population[1].population - data.total_population[0].population)
		var population = data.total_population[0].population;

		if(!iterate(element.value)){
			// parse, saa stringify
				var count = makeCountry(element.value, population, popDiff);
				localstorageArray.push(count);
				localStorage.setItem("theCountries", JSON.stringify(localstorageArray));
				updateList();
	  }else{
	    console.log("That country is already in the list!");
	  }
	  //clear text-field
	  document.getElementById("theform").reset();

	})
	.fail(function(data){
		console.log("That country is not i the api, is spelled wrong, or does'nt have capital first letter");
	});

}


function makeCountry(countryName, pop, difference){
	countryClass= {
		country: countryName,
		population: pop,
		diff: difference
	};
	return countryClass;
}


function delete_item(key){
  var element = key.valueOf();
	localstorageArray = JSON.parse(localStorage.getItem("theCountries"));
	// parse, saa stringify
	for(var k = 0; k< localstorageArray.length;k++){
		if(localstorageArray[k].country === key){
			console.log("Going to delete:" + localstorageArray[k].country);
			localstorageArray.splice(k, 1);
		}
	}
	updateList();
}

function iterate(key){
	// parse, saa stringify
	localstorageArray = JSON.parse(localStorage.getItem("theCountries"));
  for(var i = 0; i < localstorageArray.length;i++){
    if(localstorageArray[i].country === key){
      return true;
    }
  }
  return false;
}

function updateList(){
	var element = document.getElementById("input");
  var x;
	ul = document.getElementById("thelist");
	if(ul !== null){
		ul.innerHTML = "";
		for(var i = 0; i < localstorageArray.length;i++){
			x = localstorageArray[i];
			ul.innerHTML += "<li id='"+x.country+"''>"+x.country+"  - "+x.population+"  "+"<img onclick='javascript:delete_item(\""+x.country+"\")' src='pictures/delete_icon.png'></li>";
		}
		localStorage.setItem("theCountries", JSON.stringify(localstorageArray));
	}
}
