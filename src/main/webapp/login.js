function singIn(){
	

var inputUsername = document.getElementById("username").value;
var inputPassword = document.getElementById("password").value;

var request = $.ajax({
contentType: "text/plain",
data: JSON.stringify({username:inputUsername,password:inputPassword}),
dataType: "text",
url: "http://localhost:8080/SnapChatyX/webapi/signin",
type: "POST"
}).done(function(message){
	alert(message);
}).fail(function(xmlHttpRequest, statusText, ex){
	alert("xmlHttpRequest: " + xmlHttpRequest + "\n" +
		  "statusText: " + statusText + "\n" +
		  "exception: " + ex);
});

}
