/**
 * 
 * @Author AntoniosValais
 * 
 */

var websocket = new WebSocket("ws://localhost:8080/SnapChatyX/createSession");

var signInUsername = localStorage.getItem("username");

websocket.onmessage = function( message)
{
	var clientServerMessage = JSON.parse( message.data );
	
	if( clientServerMessage.messageType == "SnapTextMessage" )
	{
		var receivedMessage = clientServerMessage.data.senderUsername 
								+": "
								+ clientServerMessage.data.messageText;
		
		document.getElementById("messagesIO").innerHTML += receivedMessage + "<br/>";
		
		setTimeout(function(){
			var messagesHtml = document.getElementById("messagesIO").innerHTML; 
		    var res = messagesHtml.replace(receivedMessage, "");
		    document.getElementById("messagesIO").innerHTML = res;
		    $('#messagesIO br:first').remove();
		}, clientServerMessage.data.timeToLive * 1000 );
	}
};


websocket.onopen = function( message )
{
	var connectJson =
	{
		messageType : "UserConnectedMessage",
		data : 
		{
			username :  signInUsername	
		}
	};
	
	var connectMessage = JSON.stringify( connectJson );
	
	websocket.send( connectMessage );
};

function sendMessage()
{
	var textMessageToSent = document.getElementById("inputElement").value;
	
	var timeToLive = document.getElementById("message_time").value;
	
	var textMessageJson =
	{
		messageType : "SnapTextMessage",
		data : 
		{
			senderUsername : signInUsername,
			messageText : textMessageToSent,
			timeToLive : timeToLive
		}
	};
	
	var textMessage = JSON.stringify( textMessageJson );
	alert(textMessage);
	
	websocket.send( textMessage );
}
