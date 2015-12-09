function history() {

    var messageHistory = document.getElementById("messageHistorySection").value;
    
    var username = localStorage.getItem("username");
    
	var request = $.ajax({
		dataType : "json",
		url : "http://localhost:8080/SnapChatyX/histories/user-histories/user-history/"+username,
		type : "GET"
	}).done(function(message) 
	{
		document.getElementById("messageHistorySection").value += JSON.stringify(message);
		
		
	}).fail(function(xmlHttpRequest, statusText, ex) {
		alert("xmlHttpRequest: " + xmlHttpRequest + "\n" + "statusText: " + statusText + "\n" + "exception: " + ex);
	});

}