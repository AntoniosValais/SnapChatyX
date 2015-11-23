var request = null;

/**
 * Initializes the request which will be sent to the server. If an error occurs an alert box will explain the reason.
 */
function initRequest() {
	try {
		request = new XMLHttpRequest();
	} catch (microsoftEx) {
		var errorMessage = microsoftEx.toString();

		try {
			request = new ActiveXObject("MsXML2.XMLHTTP");
		} catch (otherMicrosoftEx) {
			errorMessage += "\n" + otherMicrosoftEx.toString();

			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (failedEx) {
				errorMessage += "\n" + failedEx.toString();

				request = null;
			}
		}
	}

	if (request == null) {
		alert(
				"Error initializing request object! Reason:" + "\n" +
				errorMessage
		);
	}
}

/**
 * Initializes the request, fills it with the appropriate information and data, sends it to the server and handles the
 * response.
 */
function registerUserXHR() { //call this from button
	initRequest();
	
	var firstName = document.getElementById("firstName").value;
	
	var lastName = document.getElementById("lastName").value;
	
	var username = document.getElementById("username").value;
	
	var password = document.getElementById("password").value;
	
	var email = document.getElementById("email").value;
	
	var url = "http://localhost:8080/SnapChatyX/signup";
	
	var jsonToSend = {
			"firstName": firstName,
			"lastName": lastName,
			"username": username,
			"password": password,
			"email": + email 
	};
	
	request.open("POST", url, true);
	
	request.setRequestHeader("Content-Type", "text/plain");
	
	request.setRequestHeader("Content-length", jsonToSend.length);
	
	request.send(JSON.stringify(jsonToSend));
	
	request.onreadystatechange = handleResponse;
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
