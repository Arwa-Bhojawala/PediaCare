function login() {
        // Get the input values
        var username = $('#username').val();
        var password = $('#password').val();

        console.log("Username: " + username);
        console.log("Password: " + password);

        // Prepare the data to send in the request
        var data = {
            username: username,
            password: password
        };

        // Send the POST request
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/auth/login-user',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                // Handle successful response from the API
                console.log('API Response:', response);

                // Perform any additional actions after successful login
                console.log(response);

                if (response.status === 'SUCCESS') {
                    document.cookie = response.cookie;
                }

                window.location.href = "../static/landing-page.html";
            },
            error: function(error) {
                // Handle error
                console.error('Error:', error);
                alert("An error occurred when trying to login. Please try again.");
            }
        });
}