const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');
const API_USER = '/home/users';
const API_LOGIN ='/home/authenticate/token'

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});


// Create user
document.getElementById('formCreateUser').addEventListener('submit', async (e) => {
    e.preventDefault();


    const userData = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        dob: document.getElementById('birthday').value,
        address: document.getElementById('address').value

    };

    try {
        console.log('Calling API:', API_USER); // ✅ Debug log

        const response = await fetch(API_USER, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData)
        });


        const result = await response.json();

        if (response.ok) {
            alert('user created successfully!');
            document.getElementById('formCreateUser').reset();

        } else {
            alert('Error: ' + result.message);
        }
    } catch (error) {
        console.error('Full error:', error); // ✅ Better error logging
        alert('Error: ' + error.message);
    }
});

// login
document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();


    const loginData = {
        username: document.getElementById('username-login').value,
        password: document.getElementById('password-login').value

    };


    try {
        console.log('Calling API:', API_LOGIN); // ✅ Debug log

        const { response, result } = await fetchPublic(API_LOGIN, {
            method: 'POST',
            body: JSON.stringify(loginData)
        });

        console.log('Response status:', response.status);
        console.log('Response headers:', response.headers.get('content-type'));


        if (response.ok) {
            alert('Login successfully!');

            if (saveAuthToken(result)) {
                window.location.href = '/home/createproduct.html';
            }
            console.log('token:', extractApiData(result).token);



        } else {
            // ✅ Handle different error cases
            let errorMessage = 'Login failed';

            if (result && result.message) {
                errorMessage = result.message;
            } else if (response.status === 400) {
                errorMessage = 'Invalid username or password';
            } else if (response.status === 401) {
                errorMessage = 'Unauthorized access';
            } else {
                errorMessage = `HTTP ${response.status}: ${response.statusText}`;
            }

            alert('Error: ' + errorMessage);
        }
    } catch (error) {
        console.error('Login error:', error);
        alert('Network error: ' + error.message);
    }
});