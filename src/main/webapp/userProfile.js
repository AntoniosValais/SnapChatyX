/**
 * 
 * 
 * @Author AntoniosValais
 * 
 */

var user = localStorage.getItem("username");

var searchProfileName = localStorage.getItem("userProfileName");

function search()
{
    var xmlHttp = new XMLHttpRequest();
    
    xmlHttp.onreadystatechange = function()
    { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
        {
        	      
			document.getElementById( "noProfile" ).style.visibility = 'hidden'; 
			document.getElementById( "info" ).style.visibility = 'visible'; 
			
        	var response = JSON.parse( xmlHttp.responseText );
        	
    		document.getElementById("userName").innerHTML = "Username : " + response.userName;
    		
    		document.getElementById("email").innerHTML = "Email : " + response.email;
    		 		
    		document.getElementById("location").innerHTML = "Current Location : " + response.location;
        }
        else
        {
        	document.getElementById( "info" ).style.visibility = 'hidden';      
			document.getElementById( "noProfile" ).style.visibility = 'visible';     
        }
 
    };
    
    xmlHttp.open("GET", "http://localhost:8080/SnapChatyX/webapi/userProfileServlet/"+searchProfileName, true); // true for asynchronous 
    
    xmlHttp.send( null );
}

search();

function addToFriendList()
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
    
    xmlHttp.open("POST", "http://localhost:8080/SnapChatyX/webapi/addOnFriendList", true); // true for asynchronous 
    
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    
    xmlHttp.send( "user="+user+"&lovesUser="+searchProfileName );	
}

function addToBlackList()
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
    
    xmlHttp.open("POST", "http://localhost:8080/SnapChatyX/webapi/addToBlackList", true); // true for asynchronous 
    
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   
    xmlHttp.send( "user="+user+"&hatesUser="+searchProfileName );	
}
