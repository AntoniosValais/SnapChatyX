var req = false;
var username = localStorage.getItem("username");
var url = "http://localhost:8080/SnapChatyX/webapi/location";

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
	var data = JSON.stringify({latitude:latitude,longtitude:longitude,username:username})
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
		req.onreadystatechange = function() {
			
		    if (req.readyState == 4 && req.status == 200) {
		    	alert(req.responseText);
		    }
		  };
	  
		req.open("POST", url, false);
		req.setRequestHeader("Content-Type", "text/plain");
		req.setRequestHeader("Content-length", data.length);
		req.send(data);
	}
	else{
		alert("Request to set your current location failed.");
	}
}