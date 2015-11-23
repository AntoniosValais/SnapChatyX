/**
 * pairnei tis  metavlhtes apo ta textbox kai kalei tis parakatw sunarthseis
 */
function signUp() {
	var firstName = document.getElementById("firstName").value;
	
	var lastName = document.getElementById("lastName").value;
	
	var username = document.getElementById("username").value;
	
	var password = document.getElementById("password").value;
	
	var email = document.getElementById("email").value;
	
	var user = new User(name, lastname, username, password, email);
	
	var jSONRegistration = new UserToJSONRegistration(user);
	
	jSONRegistration.registerToJSON();
}

/**
 * pairnei ena user kai to metatrepei se JSON
 */
var UserToJSONRegistration = function(user) {
    this.user = user;
}

/**
 * pairnei ta dedomena kai dhmiourgei to JSON
 */
UserToJSONRegistration.prototype.registerToJSON = function() {
    var firstName = this.user.getFirstName();

    var lastName = this.user.getLastName();

    var username = this.user.getUsername();

    var password = this.user.getPassword();

    var email = this.user.getEmail();

    var ourJSON = {
        "firstname": firstName,

        "lastname": lastName,

        "username": userName,

        "password": password,

        "email": eMail
    };
    
    $.ajax({
        type: "post",

        url: "http://localhost:8080/SnapChatyX/webapi/signup",
        
        datatype: "text",

        data: JSON.stringfy(ourJSON)
    });
}

/**
 * pairnei ta stoixeia tou user
 */
var User = function(firstName, lastName, username, password, email) {
    this.firstName = firstName;

    this.lastName = lastName;

    this.username = username;

    this.password = password;

    this.email = email;
}

/**
 * epistrefei to first name tou user
 */
User.prototype.getFirstName = function() {
    return this.firstName;
}

/**
 * epistrefei to last name tou user
 */
User.prototype.getLastName = function() {
    return this.lastName;
}

/**
 * epistrefei to username tou user
 */
User.prototype.getUsername = function() {
    return this.userName;
}

/**
 * epistrefei to password tou user
 */
User.prototype.getPassword = function() {
    return this.password;
}

/**
 * epistrefei to e - mail tou user
 */
User.prototype.getEmail = function() {
    return this.eMail;
}
