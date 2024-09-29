function signUp() {
    event.preventDefault(); // Prevents the default form submission

    console.log("I'm running javascript");

    // Get the input values
    var username = $('#username').val();
    var password = $('#password').val();
    var confirmPassword = $('#password-confirmation').val();

    // Check if passwords match
    if (password !== confirmPassword) {
        alert('Passwords do not match');
        return;
    }

    // Prepare the data to send in the request
    var data = {
        username: username,
        password: password
    };

    // Send the POST request
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/api/auth/sign-up', // Replace with your actual API endpoint URL
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response) {
            // Handle successful response from the API
            console.log('Registration Successful');
            window.location.href = '../static/index.html';
        },
        error: function(error) {
            // Handle error
            console.error('Error:', error);
            alert('An error occurred during signup. ' + error.responseText);
        }
    });
}