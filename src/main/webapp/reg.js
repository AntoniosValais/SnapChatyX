var request = false;

function registerUser() {
	var firstName = document.getElementById("firstName").value;
	
	var lastName = document.getElementById("lastName").value;
	
	var username = document.getElementById("username").value;
	
	var password = document.getElementById("password").value;
	
	var repeatedPassword = document.getElementById("repeatedPassword").value;
	
	var email = document.getElementById("email").value;
	
	if (firstName && lastName && username && password && email && password == repeteadPassword) {
		var url = "http://localhost:8080/SnapChatyX/webapi/signup";
		
		var dataToSend = JSON.stringify({
			"firstName" : firstName,
			"lastName" : lastName,
			"username" : username,
			"password" : password.toString(),
			"email" : email
		});
		
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else {
			if (window.ActiveXObject) {
				try {
					request = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (ex) {
					alert("Error creating request" + ex.toString());
				}
			}
		}
		if (request) {
			request.open("POST", url, true);
			
			request.setRequestHeader("Content-Type", "text/plain");
			
			request.setRequestHeader("Content-length", dataToSend.length);
			
			request.send(dataToSend);
			
			request.onreadystatechange = handleResponse(); 
		} else {
			alert("REQUEST FAILED.");
		}
	} else {
		alert("YOU DIDNT SET DATA.");
	}
}

/**
 * Handles the response which the server returns.
 */
function handleResponse() {
	var responseText = request.responseXML.responseText;

	if (request.readyState == 4 && request.status == 200)
	{
		alert(
				"You have signed up successfully!" + "\n" +
				"* " + responseText + " *"
		);
	} else {
		alert(
				"An error occurred while trying to sign up..." + "\n" +
				"* " + responseText + " *"

		);
	}
}
