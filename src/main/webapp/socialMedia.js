
var urlForUserSocialLogin = "http://localhost:8080/SnapChatyX/webapi/socialsignin";
var urlForUserSocialSignUp = "http://localhost:8080/SnapChatyX/webapi/socialsignup";

function pageRedirectToChatConsole(err,result){
	if(err){
		alert(result);
	}else{
		window.location.assign("chatConsole.html");
		
		localStorage.setItem("username",  username);
	}
}
function userSocialLogin(username,password,callback){
	  
	  var request = $.ajax({
			contentType : "text/plain",
			data : JSON.stringify({
				username :  username,
				pass : password
			}),
			dataType : "json",
			url : urlForUserSocialLogin,
			type : "POST"
		}).done(function(message) 
		{
			
			if( message["result"] === "User exists")
			{
				alert("Successful LogIn");
				
				callback(null,"successfull Log In");
				
			}else{
				alert("no log in");

				 callback("error","user not exist");
			}
			
		}).fail(function(xmlHttpRequest, statusText, ex) {
			alert("xmlHttpRequest: " + xmlHttpRequest + "\n" + "statusText: " + statusText + "\n" + "exception: " + ex);
				return "fail Log In";

		});
  }

function userSocialSignUp(firstName,lastName,username,password,email,callback){
	  
	  
	  var request = $.ajax({
		contentType : "text/plain",
		data : JSON.stringify({
			"firstName" : firstName,
			"lastName" : lastName,
			"username" : username,
			"password" : password,
			"email" : email
		}),
		dataType : "json",
		url : urlForUserSocialSignUp,
		type : "POST"
	}).done( function( message ) 
	{  		
	
		if ( message["result"] === "success") {
			alert("You have signed up successfully!" + "\n" + "* " + message["result"] + " *");
			
			userSocialLogin(username,password,pageRedirectToChatConsole);
			 if(callback)
			 callback(null,"successfull sign up");
		}else{
			alert("no log in");
			if(callback)
			 callback("error","fail sign up");
		}
		
	}).fail(function(xmlHttpRequest, statusText, ex) {
		alert("xmlHttpRequest: " + xmlHttpRequest + "\n" + "statusText: " + statusText + "\n" + "exception: " + ex);
		
	});
	  
}



function twitterLogIn(){
	// Initialize with your OAuth.io app public key
	OAuth.initialize('s1nT2_QhOIxVvXdV32sp5tdvSz8');
	OAuth.popup('twitter').done(function(result) {
		  //use result.access_token in your API request 
		  //or use result.get|post|put|del|patch|me methods (see below)
		alert("succes "+JSON.stringify(result));
		result.me()
	    .done(function (response) {
	    	//got data
	        console.log(JSON.stringify(response));
	        var request = $.ajax({
    			contentType : "text/plain",
    			data : JSON.stringify({
    				username :  response.alias,
    				pass : response.id
    			}),
    			dataType : "json",
    			url : "http://localhost:8080/SnapChatyX/webapi/socialsignin",
    			type : "POST",
    			async: false
    		}).done(function(message) 
    		{
    		
    			if( message["result"] === "User exists")
    			{
    				//2
    				userSocialLogin(response.alias,response.id,pageRedirectToChatConsole);
    				
    			}else{
    				//5
    				userSocialSignUp(response.name,"",response.alias,response.id,response.url);
    			}
    			
    		}).fail(function(xmlHttpRequest, statusText, ex) {
    			alert("xmlHttpRequest: " + xmlHttpRequest + "\n" + "statusText: " + statusText + "\n" + "exception: " + ex);
    		});
	    })
	    .fail(function (err) {
	        //handle error with err
	    	alert("fail fetch data");
	    });
		
		
		})
		.fail(function (err) {
		  //handle error with err
			alert("error twitter");
		});
}


//facebook stuff 
window.fbAsyncInit = function() {
    FB.init({
      appId      : '1109246029088611',
      xfbml      : true,
      version    : 'v2.5'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
  
  function checkLoginState() {
	  FB.getLoginStatus(function(response) {
	    if(response.status === "connected")
	    	{
			    	FB.api('/me',{fields: 'name, email,first_name,last_name' }, function(response) {
			    	    console.log(JSON.stringify(response));
			    	    var request = $.ajax({
			    			contentType : "text/plain",
			    			data : JSON.stringify({
			    				username :  response.name,
			    				pass : response.id
			    			}),
			    			dataType : "json",
			    			url : "http://localhost:8080/SnapChatyX/webapi/socialsignin",
			    			type : "POST",
			    			async: false
			    		}).done(function(message) 
			    		{
			    		
			    			if( message["result"] === "User exists")
			    			{
			    				userSocialLogin(response.name,response.id,pageRedirectToChatConsole);
			    				
			    			}else{
			    				userSocialSignUp(response.first_name,response.last_name,response.name,response.id,response.email);
			    			}
			    			
			    		}).fail(function(xmlHttpRequest, statusText, ex) {
			    			alert("xmlHttpRequest: " + xmlHttpRequest + "\n" + "statusText: " + statusText + "\n" + "exception: " + ex);
			    		});

			    	});
			    				
	    			
	    	} else if (response.status === 'not_authorized') {
	    		var errorMessage = "Not Authorised\n"+
	    							"Try Again or try with an other method.";
	    	    alert(errorMessage);
	    	} else {
	    	    var errorMessage = "Log in Fail\n"+
	    	    					"Try again";
	    	    alert(errorMessage);
	    	}
	  });
	}
  
function googlePlusLogIn(){
	  
	  OAuth.initialize('s1nT2_QhOIxVvXdV32sp5tdvSz8')
	  OAuth.popup('google_plus').done(function(result) {
	      console.log(result)
	      // do some stuff with result
	      alert("succes "+JSON.stringify(result));
	      result.me()
		    .done(function (response) {
		    	//got data
		        console.log(JSON.stringify(response));
		        var request = $.ajax({
	    			contentType : "text/plain",
	    			data : JSON.stringify({
	    				username :  response.raw.nickname,
	    				pass : response.id
	    			}),
	    			dataType : "json",
	    			url : "http://localhost:8080/SnapChatyX/webapi/socialsignin",
	    			type : "POST",
	    			async: false
	    		}).done(function(message) 
	    		{
	    		
	    			if( message["result"] === "User exists")
	    			{
	    				//2
	    				userSocialLogin(response.raw.nickname,response.id,pageRedirectToChatConsole);
	    				
	    			}else{
	    				//5
	    				userSocialSignUp(response.firstname,response.lastname,response.raw.nickname,response.id,response.url);
	    			}
	    			
	    		}).fail(function(xmlHttpRequest, statusText, ex) {
	    			alert("xmlHttpRequest: " + xmlHttpRequest + "\n" + "statusText: " + statusText + "\n" + "exception: " + ex);
	    		});
		    })
		    .fail(function (err) {
		        //handle error with err
		    	alert("fail fetch data");
		    });
	  })
	  
	  
  }

function instagramLogIn(){
	
	// Initialize with your OAuth.io app public key
	
	OAuth.initialize('s1nT2_QhOIxVvXdV32sp5tdvSz8');
	OAuth.popup('instagram').done(function(result) 
			{
		 //use result.access_token in your API request 
		//or use result.get|post|put|del|patch|me methods (see below)
		
		alert("succes "+JSON.stringify(result));
	    	//got data
	        console.log(JSON.stringify(result));
	        var request = $.ajax({
    			contentType : "text/plain",
    			data : JSON.stringify({
    				username :  result.user.username,
    				pass : result.user.id
    			}),
    			
    			dataType : "json",
    			url : "http://localhost:8080/SnapChatyX/webapi/socialsignin",
    			type : "POST",
    			async: false
    		}).done(function(message) 
    		{
    		
    			if( message["result"] === "User exists")
    			{
    				//2
    				
    				userSocialLogin(result.user.username,result.user.id,pageRedirectToChatConsole);
    				
    			}	else
    			{
    				
    				//5
    				userSocialSignUp(result.user.full_name,"",result.user.username,result.user.id,result.user.website);
    			}
    			
    		}).fail(function(xmlHttpRequest, statusText, ex) {
    			alert("xmlHttpRequest: " + xmlHttpRequest + "\n" + "statusText: " + statusText + "\n" + "exception: " + ex);
    		});
	    })
	    .fail(function (err) {
	        //handle error with err
	    	alert("fail fetch data");
	    });	
		
}