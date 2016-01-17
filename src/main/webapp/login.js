function singIn() {
	
	var user = new User(document.getElementById("username").value, document.getElementById("password").value);
	
	if(user.isValid())
	{
		var request = $.ajax({
			contentType : "text/plain",
			data : JSON.stringify({
				username : user.userName,
				pass : user.password
			}),
			dataType : "json",
			url : "http://localhost:8080/SnapChatyX/webapi/signin",
			type : "POST"
		}).done(function(message) 
		{
			alert(message["result"]);
			
			if( message["result"] === "User exists")
			{
				localStorage.setItem("username", user.userName);
				
				window.location.assign("chatConsole.html");
			}
			
		}).fail(function(xmlHttpRequest, statusText, ex) {
			alert("xmlHttpRequest: " + xmlHttpRequest + "\n" + "statusText: " + statusText + "\n" + "exception: " + ex);
		});
	}
}

function searchKeyPress(e)
{
    e = e || window.event;
    if (e.keyCode == 13)
    {
        document.getElementById('sendLogin').click();
        return false;
    }
    return true;
}

var User = function(username, password) {
	
	this.userName = username;

	this.password = password;
	
};

User.prototype.isValid = function() {
	var userIsValid = false;

	if (this.isUserNameValid() && this.isPasswordValid()) {
		userIsValid = true;
	}

	return userIsValid;
};

User.prototype.isUserNameValid = function() {
	var  userNameIsValid = false;

	if (!(this.userName == null || this.userName == "")) {
		userNameIsValid = true;
	}

	return userNameIsValid;
};

User.prototype.isPasswordValid = function() {
	var passwordIsValid = false;

	if (!(this.password == null || this.password == "")) {
		passwordIsValid = true;
	} 

	return passwordIsValid;
};
