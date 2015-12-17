function history() {

    var messageHistory = document.getElementById("messageHistorySection").value;
    
    var username = localStorage.getItem("username");
    
	var request = $.ajax({
		dataType : "json",
		url : "http://localhost:8080/SnapChatyX/histories/user-histories/user-history/"+username,
		type : "GET"
	}).done(function(message) 
	{
		
		var userHistoryJson = JSON.parse(message);
		
		var messageList = userHistoryJson.get("messageList");
		
		var messageHistorySection = document.getElementById("messageHistorySection");
		
		messageHistorySection.value = "Messages sent by user with username: " + userHistoryJson.get("username") + "\n";
		
		for (var i = 0; i < messageList.length(); i++) {
			
			messageHistorySection.value += "\n" + messageList[i].get("messageText");
			
		}
		
	}).fail(function(xmlHttpRequest, statusText, ex) {
		alert("xmlHttpRequest: " + xmlHttpRequest + "\n" + "statusText: " + statusText + "\n" + "exception: " + ex);
	});

}