/**
 * pairnei tis  metavlhtes apo ta textbox kai kalei tis parakatw sunarthseis
 */
function signUp() {
	var name = document.getElementById("name").value;
	
	var lastname = document.getElementById("lastname").value;
	
	var username = document.getElementById("username").value;
	
	var password = document.getElementById("password").value;
	
	var email = document.getElementById("email").value;
	
	var user = new User(name, lastname, username, password, email);
	
	var jSONRegistration = new UserToJSONRegistration(user);
	
	var jSONtoJava = jSONRegistration.registerToJSON();
}

/**
 * pairnei ena user kai to metatrepei se JSON
 */
var UserToJSONRegistration = function (user) {
    this.user = user;
};

/**
 * pairnei ta dedomena kai dhmiourgei to JSON
 * @returns
 */
UserToJSONRegistration.prototype.registerToJSON = function () {
    var firstName = this.user.getFirstName();

    var lastName = this.user.getLastName();

    var userName = this.user.getUserName();

    var password = this.user.getPassword();

    var eMail = this.user.getEMail();

    var ourJSON = JSON.stringify({
        onoma: firstName,

        epitheto: lastName,

        username: userName,

        pass: password,

        email: eMail
    });
    
    var request = $.ajax({
        type: "POST",

        contentType: "text/plain",

        url: "http://localhost:8080/SnapChatyX/signup",

        dataType: "text",

        data: ourJSON
    })
    return ourJSON
};

/**
 * pairnei ta stoixeia tou user
 */
var User = function (firstName, lastName, userName, password, eMail) {
    this.firstName = firstName;

    this.lastName = lastName;

    this.userName = userName;

    this.password = password;

    this.eMail = eMail;
};

/**
 * apo edw kai katw epistrefei ta stoixeia
 * @returns
 */
User.prototype.getFirstName = function () {
    return this.firstName;
};

User.prototype.getLastName = function () {
    return this.lastName;
};

User.prototype.getUserName = function () {
    return this.userName;
};

User.prototype.getPassword = function () {
    return this.password;
};

User.prototype.getEMail = function () {
    return this.eMail;
};