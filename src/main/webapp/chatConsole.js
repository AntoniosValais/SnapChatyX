/**
 * 
 * @Author AntoniosValais
 * 
 */

var websocket = new WebSocket("ws://localhost:8080/SnapChatyX/createSession");

websocket.onmessage = function( message)
{
	var clientServerMessage = JSON.parse( message.data );
	
	if( clientServerMessage.messageType == "SnapTextMessage" )
	{
		var receivedMessage = clientServerMessage.data.senderUsername 
								+": "
								+ clientServerMessage.data.messageText;
		
		document.getElementById("messagesIO").innerHTML += receivedMessage + "<br/>";
	}
};

websocket.onopen = function( message )
{
	var connectJson =
	{
		messageType : "UserConnectedMessage",
		data : 
		{
			username : "Antonios" 	
		}
	};
	
	var connectMessage = JSON.stringify( connectJson );
	
	websocket.send( connectMessage );
};

function sendMessage()
{
	var textMessageToSent = document.getElementById("inputElement").value;
	
	var textMessageJson =
	{
		messageType : "SnapTextMessage",
		data : 
		{
			senderUsername : "Antonios",
			messageText : textMessageToSent
		}
	};
	
	var textMessage = JSON.stringify( textMessageJson );
	
	websocket.send( textMessage );
}
