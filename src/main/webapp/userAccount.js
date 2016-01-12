/**
 * 
 * 
 * @Author AntoniosValais
 * 
 */

var user = localStorage.getItem("username");


function getUserAccountInfo()
{
    var xmlHttp = new XMLHttpRequest();
    
    xmlHttp.onreadystatechange = function()
    { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
        {
        	var response = JSON.parse( xmlHttp.responseText );
        	
    		document.getElementById("userName").innerHTML = "Username : " + response.username;
    		
    		document.getElementById("firstName").innerHTML = "First Name : " + response.firstName;
    		
    		document.getElementById("lastName").innerHTML = "Last Name : " + response.lastName;
    		
    		document.getElementById("email").innerHTML = "Email : " + response.email;
    		 		
    		document.getElementById("location").innerHTML = "Current Location : " + response.locationName;
    		
    		for( var index in response.friendList )
    		{
    			var username = response.friendList[ index ];
    			
    			addUserNameOnTable( username, "friendlist", "friendlistTable", parseInt( index ) + 1 );
    		}
    		
    		for( var index in response.blackList )
    		{
    			var username = response.blackList[ index ];

				addUserNameOnTable( username, "blacklist", "blacklistTable", parseInt( index ) + 1);    			
    		}
        }
 
    };
    
    xmlHttp.open("GET", "http://localhost:8080/SnapChatyX/webapi/userAccount/"+user, true); // true for asynchronous 
    
    xmlHttp.send( null );	
}

function addUserNameOnTable( username, listType, tableName, index )
{
	var table = document.getElementById( tableName );
	
	var row = table.insertRow( index );
	
	var td = row.insertCell( 0 );
	
	td.innerHTML = "<input type='checkbox' name='"+listType+"[]' value='"+username+"'/>"+username;
}

function removeFromFriendList()
{
	var friendListArray =  document.getElementsByName("friendlist[]");

	var friendListArrayLength = friendListArray.length;             
	
	var usersToRemoveFromFriendList = [];
	
	for(var index = 0; index < friendListArrayLength; index++)
	{
	      if( friendListArray[ index ].checked )
	      {
	           usersToRemoveFromFriendList.push( friendListArray[ index ].value );	           
	      }
	}
	
	postOnRemoveFriendListServlet( usersToRemoveFromFriendList );		
}

function postOnRemoveFriendListServlet( usersToRemoveFromFriendList )
{
	for( var index = 0; index < usersToRemoveFromFriendList.length; index++ )
	{
	    var xmlHttp = new XMLHttpRequest();
	    
	    xmlHttp.onreadystatechange = function()
	    { 
	        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
	        {
	        	var response = JSON.parse( xmlHttp.responseText );
	
				alert( response.result + " "+ response.message );
	        }
	 
	    };
	    
	    xmlHttp.open("POST", "http://localhost:8080/SnapChatyX/webapi/removeFromFriendList", true); // true for asynchronous 
	    
	    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    
	    xmlHttp.send( "user="+user+"&removeUser="+usersToRemoveFromFriendList[ index ] );			
	}	
}

function removeFromBlackList()
{
	var blackListArray =  document.getElementsByName("blacklist[]");

	var blackListArrayLength = blackListArray.length;             
	
	var usersToRemoveFromblackList = [];
	
	for(var index = 0; index < blackListArrayLength; index++)
	{
	      if( blackListArray[ index ].checked )
	      {
	           usersToRemoveFromblackList.push( blackListArray[ index ].value );	           
	      }
	}
	
	postOnRemoveBlackListServlet( usersToRemoveFromblackList );	
}

function postOnRemoveBlackListServlet( usersToRemoveFromblackList )
{
	for( var index = 0; index < usersToRemoveFromblackList.length; index++ )
	{
	    var xmlHttp = new XMLHttpRequest();
	    
	    xmlHttp.onreadystatechange = function()
	    { 
	        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
	        {
	        	var response = JSON.parse( xmlHttp.responseText );
	
				alert( response.result + " "+ response.message );
	        }
	 
	    };
	    
	    xmlHttp.open("POST", "http://localhost:8080/SnapChatyX/webapi/removeFromBlackList", true); // true for asynchronous 
	    
	    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    
	    xmlHttp.send( "user="+user+"&removeUser="+usersToRemoveFromblackList[ index ] );			
	}	
}

getUserAccountInfo();
