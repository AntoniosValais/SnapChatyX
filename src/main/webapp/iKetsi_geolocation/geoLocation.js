var req = false;
var username = "iKetsi";
var url = "http://localhost:8080/SnapChatyX/webapi/locationtest";


function getLocation(){
	var map = new google.maps.Map(document.getElementById('map'), {
		center: {lat: -34.397, lng: 150.644},
		zoom: 14
	});
	var infoWindow = new google.maps.InfoWindow({map: map});
	
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var locationData = {
				lat: position.coords.latitude,
				lng: position.coords.longitude,
	};
	infoWindow.setPosition(locationData);
	infoWindow.setContent('Location found.');
	map.setCenter(locationData);
	saveGeoLocation(position.coords.latitude, position.coords.longitude);
	},
	function() {
		handleLocationError(true, infoWindow, map.getCenter());
    	saveGeoLocation(locationData);
    });
		
		
	} else {
	handleLocationError(false, infoWindow, map.getCenter());
	}
}

function handleLocationError(browserHasGeolocation, infoWindow, locationData){
	infoWindow.setPosition(locationData);
	infoWindow.setContent(browserHasGeolocation ?
	'Error: The Geolocation service failed.' :
	'Error: Your browser doesn\'t support geolocation.');
	}



function saveGeoLocation(latitude,longitude){
var data = ("Latitude: " + latitude + " Longitude: " + longitude + " Username: " + username)
if(window.XMLHttpRequest){
	req = new XMLHttpRequest();
}
else{
	if(window.ActiveXObject){
		try{
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		catch (e){alert("Error creating request" + e.toString())} 
	}
}
if(req){
	req.open("POST", url, true);
	req.setRequestHeader("Content-Type", "text/plain");
	req.setRequestHeader("Content-length", data.length);
	req.send(data);
//TODO: add response from server that user's geolocation is saved.
}
else{
	document.getElementById("screen").innerHTML = "Request to set your current location failed.";
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}