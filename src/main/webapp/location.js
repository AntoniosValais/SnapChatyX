var req = false;
var username = localStorage.getItem("username");
var url = "http://localhost:8080/SnapChatyX/webapi/location";


function saveLocation(location){
	var loc = document.getElementById("locdata");
	var location = loc.value;
	var data = JSON.stringify({location:location,username:username})
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
	  
		req.open("POST", url, true);
		req.setRequestHeader("Content-Type", "text/plain");
		req.setRequestHeader("Content-length", data.length);
		req.send(data);
	}
	else{
		alert("Request to set your current location failed.");
	}
}