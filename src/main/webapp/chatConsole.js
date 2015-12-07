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
		
		document.getElementById("messagesIO").innerHTML += "<div id='"+clientServerMessage.data.messageId+"'>"+ receivedMessage + "</div>";
		
	}else if( clientServerMessage.messageType == "SnapTextMessageRemove"){
		$('#'+clientServerMessage.data.messageId).remove();
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
