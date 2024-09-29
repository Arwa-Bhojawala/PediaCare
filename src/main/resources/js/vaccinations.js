function addVaccine() {
    var newVaccine = $('#newVaccine').val();

    if (newVaccine.trim() === '') {
        alert('Please enter a vaccine name.');
        return;
    }

    var data = {
        vaccineName: newVaccine,
        vaccineType: "PERSONAL"
    };

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/auth/get-user/' + document.cookie,
        contentType: 'application/json',
        success: function(response) {
            data.patient = response;
            console.log(data);
            // Send the POST request using AJAX
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/api/vaccine-record/add',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(responseData) {
                    // Show success message
                    $('.success-message').css('display', 'block');

                    // Optionally, perform any other actions upon successful response

                    // Clear the input field
                    $('#newVaccine').val('');
                },
                error: function(error) {
                    console.error('Error:', error);
                }
            });
        },
        error: function(error) {
            console.error('Error:', error);
            alert("An error occurred when trying to save patient info. " + error.responseText);
        }
    });
}

function populatePersonalVaccineList() {
    var personalVaccineList = document.getElementById('personal-vaccine-list');
    var govVaccineList = document.getElementById('government-vaccine-list');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/auth/get-user/' + document.cookie,
        contentType: 'application/json',
        success: function(response) {
            fetch('http://localhost:8080/api/vaccine-record/get-by-patient', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(response)
            })
                .then(response => response.json())
                .then(data => {
                    // Clear any existing list items
                    personalVaccineList.innerHTML = '';
                    govVaccineList.innerHTML = '';

                    // Iterate through the data and create list items
                    data.forEach(vaccine => {
                        var listItem = document.createElement('li');
                        listItem.classList.add('list-group-item');
                        listItem.textContent = vaccine.vaccineName;

                        if (vaccine.vaccineType === "PERSONAL") {
                            personalVaccineList.appendChild(listItem);
                        } else {
                            govVaccineList.appendChild(listItem);
                        }
                    });
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        },
        error: function(error) {
            console.error('Error:', error);
            alert("An error occurred when trying to save patient info. " + error.responseText);
        }
    });
}
