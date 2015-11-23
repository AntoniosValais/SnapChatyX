/**
 * In order to use this series of functions properly the button with id: 'sign_up_button' ,onclick must not call any
 * function at all. JQuery handles the 'click' event in less code.
 */
$.(document).ready(function() {
    $('#sign_up_button').click(function() {
        var url = "http://localhost:8080/SnapChatyX/signup";

        $.post(
            url,
            {
                firstName : $('#firstName').val(),
                lastName : $('#lastName').val(),
                username : $('#username').val(),
                password : $('#password').val(),
                email : $('#email').val()
            },
            function(response) {
                alert(response.responseText);
            }
        );
    });
});
