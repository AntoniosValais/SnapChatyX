<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js'></script>
<script src="signup.js" type="text/javascript"></script>
</head>

<%@ import = "gr.teicm.toulou.SnapChatyX.MockSignUpResource" %>

<%

%>

<body>
<label>Name: </label><input id="name" type="text">
<br>
<label>Last Name: </label><input id="lastname" type="text">
<br>
<label>Username: </label><input id="username" type="text">
<br>
<label>Password: </label><input id="password" type="password">
<br>
<label>Email: </label><input id="email" type="email">
<br>
<input id="submit" type="submit" value="submit" onclick="signUp();">
<p id="test">
</p>
</body>
</html>