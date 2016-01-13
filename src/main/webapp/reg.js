var request = false;

function registerUser() 
{
	
	///////////////////////////////

	// if (userValidation.isValid()) {
		// var userStringAsJSON = JSON.stringify({
			// "firstName" : firstName,
			// "lastName" : lastName,
			// "username" : username,
			// "password" : password.toString(),
			// "email" : email
		// });
// 
		// var url = "http://localhost:8080/SnapChatyX/webapi/signup";
// 
		// if (window.XMLHttpRequest) {
			// request = new XMLHttpRequest();
		// } else {
			// if (window.ActiveXObject) {
				// try {
					// request = new ActiveXObject("Microsoft.XMLHTTP");
				// } catch (ex) {
					// alert("Error creating request" + ex.toString());
				// }
			// }
		// }
		// if (request) {
			// request.open("POST", url, true);
// 
			// request.setRequestHeader("Content-Type", "text/plain");
// 
			// request.setRequestHeader("Content-length", userStringAsJSON.length);
// 
			// request.send(userStringAsJSON);
// 
			// request.onreadystatechange = handleResponse();
// 
		// } else {
			// alert("Request failed!");
		// }
	// } else {
		// alert("The data you iserted are not valid!");
	// }
// 	
	///////////////////////////////////	
	
	var firstName = document.getElementById("firstName").value;

	var lastName = document.getElementById("lastName").value;

	var username = document.getElementById("username").value;

	var password = document.getElementById("password").value;

	var repeatedPassword = document.getElementById("repeatedPassword").value;

	var email = document.getElementById("email").value;

	var userValidation = new UserValidation(firstName, lastName, username, password, repeatedPassword, email);

		var request = $.ajax({
		contentType : "text/plain",
		data : JSON.stringify({
			"firstName" : firstName,
			"lastName" : lastName,
			"username" : username,
			"password" : password.toString(),
			"email" : email
		}),
		dataType : "json",
		url : "http://localhost:8080/SnapChatyX/webapi/signup",
		type : "POST"
	}).done( function( message ) 
	{
		alert( message["result"] );
		
	
		if ( message["result"] === "success") {
			alert("You have signed up successfully!" + "\n" + "* " + message["result"] + " *");

			window.location.assign("login.html");

		}
		
	}).fail(function(xmlHttpRequest, statusText, ex) {
		alert("xmlHttpRequest: " + xmlHttpRequest + "\n" + "statusText: " + statusText + "\n" + "exception: " + ex);
	});
}

/**
 * Handles the response which the server returns.
 */
function handleResponse() {

	
	if ( request.readyState == 4 && request.status == 200 )
	{
		var responseText = request.responseText;

		var responceJson = JSON.parse(responseText);

		if (responceJson.result === "success") {
			alert("You have signed up successfully!" + "\n" + "* " + responseText + " *");

			window.location.assign("login.html");

		}
	}
	else
	{
		alert("An error occurred while trying to sign up..." + "\n" + "* " + responseText + " *");
	}
	
}

var UserValidation = function(firstName, lastName, username, password, repeatedPassword, email) {
	this.firstName = firstName;

	this.lastName = lastName;

	this.username = username;

	this.password = password;

	this.repeatedPassword = repeatedPassword;

	this.email = email;
};

UserValidation.prototype.isValid = function() {
	var userIsValid = false;

	if (this.isFirstNameValid() && this.isLastNameValid() && this.isUsernameValid() && this.isPasswordValid() && this.isEmailValid()) {
		userIsValid = true;
	}

	return userIsValid;
};

UserValidation.prototype.isFirstNameValid = function() {
	var userFirstNameIsValid = false;

	if (!(this.firstName == null || this.firstName == "")) {
		userFirstNameIsValid = true;
	} /*else {
		document.getElementById("firstName").value = null;
	}*/

	return userFirstNameIsValid;
};

UserValidation.prototype.isLastNameValid = function() {
	var userLastNameIsValid = false;

	if (!(this.lastName == null || this.lastName == "")) {
		userLastNameIsValid = true;
	} /*else {
		document.getElementById("lastName").value = null;
	}*/

	return userLastNameIsValid;
};

UserValidation.prototype.isUsernameValid = function() {
	var userUsernameIsValid = false;

	if (!(this.username == null || this.username == "")) {
		userUsernameIsValid = true;
	} /*else {
		document.getElementById("username").value = null;
	}*/

	return userUsernameIsValid;
};

UserValidation.prototype.isPasswordValid = function() {
	var userPasswordIsValid = false;

	if (!(this.password == null || this.password == "") && this.password == this.repeatedPassword) {
		userPasswordIsValid = true;
	} /*else {
		document.getElementById("password").value = null;
		document.getElementById("repeatedPassword").value = null;
	}*/

	return userPasswordIsValid;
};

UserValidation.prototype.isEmailValid = function() {
	var userEmailIsValid = false;

	if (!(this.email == null || this.email == "")) {
		userEmailIsValid = true;
	} /*else {
		document.getElementById("password").value = null;
	}*/

	return userEmailIsValid;
};
