/**
 * 
 * 
 * @Author AntoniosValais
 * 
 */

var webSocketUser2;

function connectUser2()
{
	webSocketUser2 = new WebSocket("ws://localhost:8080/SnapChatyX/createSession");
	
	webSocketUser2.onmessage = function( message)
	{
		document.getElementById("console").innerHTML += message.data + "<br/>";
	};

	webSocketUser2.onopen = function( message )
	{
		var connectJson = ' { "messageType":"UserConnectedMessage", ' +
								' "data":{"username":"Valais" }' +
							 '}';
	
		webSocketUser2.send( connectJson );
	};
}



function sendTextUser2()
{
	var messageJson = ' { "messageType":"SnapTextMessage", ' +
						 ' "data":' +
							' {"senderUsername":"Valais","messageText":"Valais says hello!" } ' +
						 '}';
	

	webSocketUser2.send( messageJson );	

}