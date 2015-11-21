/**
 * 
 * 
 * @Author AntoniosValais
 * 
 */

var webSocketUser1;

function connectUser1()
{
	webSocketUser1 = new WebSocket("ws://localhost:8080/SnapChatyX/createSession")
	
	webSocketUser1.onmessage = function( message)
	{
		document.getElementById("console").innerHTML += message.data + "<br/>"
	}
	
	webSocketUser1.onopen = function( message )
	{
		var connectJson = ' { "messageType":"UserConnectedMessage", ' +
								' "data":{"username":"Antonios" }' +
							 '}';
	
		webSocketUser1.send( connectJson );
	}
}



function sendTextUser1()
{
	var messageJson = ' { "messageType":"SnapTextMessage", ' +
						 ' "data":' +
							' {"senderUsername":"Antonios","messageText":"Antonios says hello!" } ' +
						 '}';
	

	webSocketUser1.send( messageJson );	

}