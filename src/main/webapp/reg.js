
var req = false;

function test(){

	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var email = document.getElementById("email").value;
	if(firstName && lastName && username && password && email){
		var url = "http://localhost:8080/SnapChatyX/webapi/signup";
		var dataToSend = ("firstName:" + firstName + ",lastName:" + lastName + ",username:" + username + ",password:" + password + ",email:" + email);
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
			req.setRequestHeader("Content-length", dataToSend.length);
			req.send(dataToSend);
		}
		else alert("REQUEST FAILED.");
	
	}
	else alert("YOU DIDNT SET DATA");
}