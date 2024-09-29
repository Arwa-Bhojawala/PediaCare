function savePatientInfo(data) {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/auth/get-user/' + document.cookie,
        contentType: 'application/json',
        success: function(response) {
            console.log(response);
            data.patient = response;
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/api/patient/add-data', // Replace with your actual API endpoint URL
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(response) {
                    console.log('Data submitted successfully:', response);
                },
                error: function(error) {
                    console.error('Error:', error);
                    alert("An error occurred when trying to save patient info. " + error.responseText);
                }
            });
        },
        error: function(error) {
            console.error('Error:', error);
            alert("An error occurred when trying to save patient info. " + error.responseText);
        }
    })
}

function populateFormFromAPI() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/auth/get-user/' + document.cookie,
        contentType: 'application/json',
        success: function(response) {
            fetch('http://localhost:8080/api/patient/get-patient-info', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(response)
            })
                .then(response => response.json())
                .then(data => {
                    document.getElementById('name').value = data.name;
                    document.getElementById('age').value = data.age;
                    document.getElementById('bloodGroup').value = data.bloodGroup;
                    document.getElementById('bloodPressure').value = data.bloodPressure;
                    document.getElementById('weight').value = data.weight;
                    document.getElementById('height').value = data.height;
                })
                .catch(error => console.error('Error:', error));
        },
        error: function(error) {
            console.error('Error:', error);
            alert("An error occurred when trying to save patient info. " + error.responseText);
        }
    });
}

function populateHealthRecordsTable() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/auth/get-user/' + document.cookie,
        contentType: 'application/json',
        success: function(response) {
            fetch('http://localhost:8080/api/health-record/get-by-patient', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(response)
            })
                .then(response => response.json())
                .then(data => {
                    // Get the table body element
                    var tableBody = document.getElementById('healthRecordsTable').getElementsByTagName('tbody')[0];

                    // Iterate through the data and populate the table rows
                    data.forEach(record => {
                        var row = tableBody.insertRow();
                        var dateCell = row.insertCell(0);
                        var doctorCell = row.insertCell(1);
                        var diagnosisCell = row.insertCell(2);

                        dateCell.innerHTML = record.date;
                        doctorCell.innerHTML = record.doctorName;
                        diagnosisCell.innerHTML = record.diagnosis;
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

function addHealthRecord() {
    // Get values from form fields
    var recordDate = $('#recordDate').val();
    var doctorName = $('#doctorName').val();
    var diagnosis = $('#diagnosis').val();

    // Check for null or empty values
    if (!recordDate || !doctorName || !diagnosis) {
        alert('Please fill out all fields.');
        return;
    }

    // Create data object
    var data = {
        date: recordDate,
        doctorName: doctorName,
        diagnosis: diagnosis
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
                url: 'http://localhost:8080/api/health-record/add',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(responseData) {
                    console.log('Record added successfully:', responseData);
                    // Optionally, perform any other actions upon successful response
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


