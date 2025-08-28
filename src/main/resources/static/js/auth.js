// auth.js - Utility function for authenticated API calls



function extractApiData(response) {
    return response?.result || response?.data || response;
}

function saveAuthToken(apiResponse) {
    const data = extractApiData(apiResponse);
    if (data?.token) {
        localStorage.setItem('authToken', data.token);
        return true;
    }
    return false;
}

async function fetchWithAuth(url, options = {}) {
    const token = localStorage.getItem('authToken');

    if (!token) {
        alert('Please login first');
        window.location.href = '/home/login.html';
        return null;
    }

    const headers = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
        ...options.headers
    };

    console.log('Sending request with token:', token);
    console.log('Headers:', headers);

    try {
        const response = await fetch(url, {
            ...options,
            headers
        });

        // Nếu token hết hạn hoặc không hợp lệ
        if (response.status === 401) {
            localStorage.removeItem('authToken');
            alert('Session expired. Please login again');
            window.location.href = '/home/login.html';
            return null;
        }


        return response;
    } catch (error) {
        console.error('API call error:', error);
        throw error;
    }
}


// Generic API call without auth (for login, register)
async function fetchPublic(url, options = {}) {
    const defaultHeaders = {
        'Content-Type': 'application/json'
    };

    try {
        const response = await fetch(url, {
            ...options,
            headers: { ...defaultHeaders, ...options.headers }
        });

        let result = null;
        const contentType = response.headers.get('content-type');

        if (contentType && contentType.includes('application/json')) {
            const responseText = await response.text();
            if (responseText.trim()) {
                result = JSON.parse(responseText);
            }
        }

        return { response, result };
    } catch (error) {
        console.error('API call error:', error);
        throw error;
    }
}

// Check authentication on page load
function checkAuth() {
    const token = localStorage.getItem('authToken');

    if (!token) {
        console.log('token: ', token);

        window.location.href = '/home/login.html';
        return false;
    }
    return true;
}

// Logout function
function logout() {
    localStorage.removeItem('authToken');
    window.location.href = '/home/login.html';
}