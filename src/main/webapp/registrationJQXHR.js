/**
 * Handles the XML HTTP request, which will be sent to the server, and the response with the JQuery "ajax" function.
 */
function registerUserJQXHR() { //call this from button
    var firstName = document.getElementById("firstName").value;

    var lastName = document.getElementById("lastName").value;

    var username = document.getElementById("username").value;

    var password = document.getElementById("password").value;

    var email = document.getElementById("email").value;

    var userAsJSON = {
        "firstName": firstName,
        "lastName": lastName,
        "username": username,
        "password": password,
        "email": email
    };

    var url = "http://localhost:8080/SnapChatyX/webapi/signup/data";

    $.ajax({
        type: "POST",
        url: url,
        dataType: "text",
        data: JSON.stringify(userAsJSON)
    }).done(function(message) {
        alert(
            "Data saved:" + message
        );
    }).fail(function(xmlHttpRequest, statusText, errorThrown) {
        alert(
            "XML HTTP Request: " + JSON.stringify(xmlHttpRequest) + ",\n" +
            "Status: " + statusText + ",\n" +
            "Error thrown: " + errorThrown
        );
    });
}
