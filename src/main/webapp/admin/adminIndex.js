/**
 * 
 * 
 * @Author iKetsi
 * 
 */
var user = localStorage.getItem("username");
var banUnbanUser = document.getElementById("ban_unban_user_input");
var addRemoveAdmin = document.getElementById("add_remove_admin_input"); 


function banList()
{
	if(window.XMLHttpRequest){
		var req = new XMLHttpRequest();
		}
	else{
		if(window.ActiveXObject){
			try{
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e){
				try{
					req = new ActiveXObject("MsXML2.XMLHTTP.3.0");
				}
				catch(e){
					alert("Error creating request" + e.toString())
				}
			}
		}
	}
	if(req){
		
	    req.onreadystatechange = function()
	    { 
	        if ( req.readyState == 4 && req.status == 200 )
	        {
	        	var response = JSON.parse( req.responseText );
	        	for( var i = 0; i < response.length; i++ ){
	        		document.getElementById("bannedUserList").innerHTML += response[i];
	        		document.getElementById("bannedUserList").innerHTML += "<br/>";
	        	}

	        }
	 
	    };
	    
	    req.open("GET", "http://localhost:8080/SnapChatyX/webapi/getBanList/", true);
	    req.send();
		
	}
	else{
		alert("Request failed.")
	}
}
//banList();

function adminList()
{
	if(window.XMLHttpRequest){
		var req = new XMLHttpRequest();
		}
	else{
		if(window.ActiveXObject){
			try{
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e){
				try{
					req = new ActiveXObject("MsXML2.XMLHTTP.3.0");
				}
				catch(e){
					alert("Error creating request" + e.toString())
				}
			}
		}
	}
	if(req){
		
	    req.onreadystatechange = function()
	    { 
	        if ( req.readyState == 4 && req.status == 200 )
	        {
	        	var response = JSON.parse( req.responseText );
	        	for( var i = 0; i < response.length; i++ ){
	        		document.getElementById("adminList").innerHTML += response[i];
	        		document.getElementById("adminList").innerHTML += "<br/>";
	        	}
	        }
	 
	    };
	    
	    req.open("GET", "http://localhost:8080/SnapChatyX/webapi/getAdminList/", true);
	    req.send();
		
	}
	else{
		alert("Request failed.")
	}
}
//adminList();

function banUser()
{
	if(window.XMLHttpRequest){
		var req = new XMLHttpRequest();
		}
	else{
		if(window.ActiveXObject){
			try{
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e){
				try{
					req = new ActiveXObject("MsXML2.XMLHTTP.3.0");
				}
				catch(e){
					alert("Error creating request" + e.toString())
				}
			}
		}
	}
	if(req){
		
	    req.onreadystatechange = function()
	    { 
	        if ( req.readyState == 4 && req.status == 200 )
	        {
	        	var response = JSON.parse( req.responseText );
				alert( response.result + " " + response.message );
	        }
	 
	    };
	    
	    req.open("POST", "http://localhost:8080/SnapChatyX/webapi/banUser", true);
	    
	    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    
	    req.send( "user=" + banUnbanUser );
	}
	else{
		alert("Request failed.")
	}
}

function unbanUser()
{
	if(window.XMLHttpRequest){
		var req = new XMLHttpRequest();
		}
	else{
		if(window.ActiveXObject){
			try{
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e){
				try{
					req = new ActiveXObject("MsXML2.XMLHTTP.3.0");
				}
				catch(e){
					alert("Error creating request" + e.toString())
				}
			}
		}
	}
	if(req){
		
	    req.onreadystatechange = function()
	    { 
	        if ( req.readyState == 4 && req.status == 200 )
	        {
	        	var response = JSON.parse( req.responseText );
				alert( response.result + " " + response.message );
	        }
	 
	    };
	    
	    req.open("POST", "http://localhost:8080/SnapChatyX/webapi/unbanUser", true);
	    
	    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    
	    req.send( "user=" + banUnbanUser );
	}
	else{
		alert("Request failed.")
	}
}

function addAdmin()
{
	if(window.XMLHttpRequest){
		var req = new XMLHttpRequest();
		}
	else{
		if(window.ActiveXObject){
			try{
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e){
				try{
					req = new ActiveXObject("MsXML2.XMLHTTP.3.0");
				}
				catch(e){
					alert("Error creating request" + e.toString())
				}
			}
		}
	}
	if(req){
		
	    req.onreadystatechange = function()
	    { 
	        if ( req.readyState == 4 && req.status == 200 )
	        {
	        	var response = JSON.parse( req.responseText );
				alert( response.result + " " + response.message );
	        }
	 
	    };
	    
	    req.open("POST", "http://localhost:8080/SnapChatyX/webapi/addAdmin", true);
	    
	    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    
	    req.send( "user=" + addRemoveAdmin );
	}
	else{
		alert("Request failed.")
	}
}

function removeAdmin()
{
	if(window.XMLHttpRequest){
		var req = new XMLHttpRequest();
		}
	else{
		if(window.ActiveXObject){
			try{
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e){
				try{
					req = new ActiveXObject("MsXML2.XMLHTTP.3.0");
				}
				catch(e){
					alert("Error creating request" + e.toString())
				}
			}
		}
	}
	if(req){
		
	    req.onreadystatechange = function()
	    { 
	        if ( req.readyState == 4 && req.status == 200 )
	        {
	        	var response = JSON.parse( req.responseText );
				alert( response.result + " " + response.message );
	        }
	 
	    };
	    
	    req.open("POST", "http://localhost:8080/SnapChatyX/webapi/removeAdmin", true);
	    
	    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    
	    req.send( "user=" + addRemoveAdmin );
	}
	else{
		alert("Request failed.")
	}
}