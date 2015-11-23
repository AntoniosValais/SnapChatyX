function singIn(){
	

var inputUsername = document.getElementById("username").value;
var inputPassword = document.getElementById("password").value;

var request = $.ajax({
contentType: "text/plain",
data: JSON.stringify({username:inputUsername,password:inputPassword}),
dataType: "text",
url: "http://localhost:8080/SnapChatyX/webapi/myresource",
type: "POST"
});

}
