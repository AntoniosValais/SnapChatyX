/**
 * 
 * @Author AntoniosValais
 * @Author TaniaG.
 * 
 */

var websocket = new WebSocket("ws://localhost:8080/SnapChatyX/createSession");

var signInUsername = localStorage.getItem("username");

websocket.onmessage = function( message)
{
	var clientServerMessage = JSON.parse( message.data );
	
	if( clientServerMessage.messageType == "SnapTextMessage" )
	{
		var receivedMessage = 	clientServerMessage.data.senderUsername
								+": "
								+ clientServerMessage.data.messageText;
		
		document.getElementById("messagesIO").innerHTML += "<div id='"+clientServerMessage.data.messageId+"'>"+ receivedMessage + "</div>";
		document.getElementById('beep').play();
	}else if( clientServerMessage.messageType == "SnapTextMessageRemove"){
		removeMessageWithId(clientServerMessage.data.messageId);
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

function searchKeyPress(e)
{
    e = e || window.event;
    if (e.keyCode == 13)
    {
        document.getElementById('sendButton').click();
        return false;
    }
    return true;
}
function sendMessage()
{
	if(document.getElementById("inputElement").value != ""){
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
	
	websocket.send( textMessage );
	var clean = "";
	document.getElementById("inputElement").value = clean;
	}
	
}

function searchProfile()
{
	var profileName = document.getElementById("profileSearchInput").value;
	
	if( profileName != "" )
	{					
			localStorage.setItem("userProfileName", profileName);
			
			var win = window.open('userProfile.html', '_blank');
			
			if(win)
			{			    
			    win.focus();
			}
			else
			{
			    alert('Please allow popups for this site');
			}
	}
}

function removeMessageWithId(msgId){
	$('#'+msgId).remove();
}