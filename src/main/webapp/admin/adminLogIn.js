/**
 * 
 * @Author Eftiqia Bibo
 * 
 * 
 */
function adminLogIn()
{

		var adminName = document.getElementById("adminName").value;
		
		var password = document.getElementById("password").value;
		
		if(adminName != "" || password != "") 
		{
			
			var xmlHttp = new XMLHttpRequest();
	    
		    xmlHttp.onreadystatechange = function()
		    { 
		        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
		        {
		        	var response = JSON.parse( xmlHttp.responseText );
		
					alert( response.result + " "+ response.message );
					
					localStorage.setItem("adminName", adminName);
					
					window.location.assign("index.html");
		        }
		        else if (xmlHttp.readyState == 4 && xmlHttp.status == 401) {
		        	
		        	var response = JSON.parse( xmlHttp.responseText );
		    		
					alert( response.result + " "+ response.message );
		        }
		 
		    };
		    
		    xmlHttp.open("POST", "http://localhost:8080/SnapChatyX/webapi/adminLogIn", true); // true for asynchronous 
		    
		    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		    
		    xmlHttp.send( "adminName="+adminName+"&password="+password );	
		}
	
}