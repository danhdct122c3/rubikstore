// Navigation function
function navigateTo(path) {
    window.location.href = path;
}

// Load user info
async function loadUserInfo() {
    try {
        const token = localStorage.getItem('authToken');
        if (!token) {
            window.location.href = '/home/login.html';
            return;
        }

        // Decode JWT token để lấy username (nếu có)
        const payload = JSON.parse(atob(token.split('.')[1]));
        const username = payload.sub || payload.username || 'Admin';

        document.getElementById('username').textContent = username;
    } catch (error) {
        console.error('Error loading user info:', error);
        document.getElementById('username').textContent = 'Admin';
    }
}

// Load quick stats
async function loadQuickStats() {
    try {
        // Load categories count
        const categoriesResponse = await fetchWithAuth('/home/categories');
        if (categoriesResponse.ok) {
            const categoriesData = await categoriesResponse.json();
            const categoriesCount = categoriesData.result ? categoriesData.result.length : 0;
            document.getElementById('totalCategories').textContent = categoriesCount;
        }

        // Load products count (nếu có API)
        const productsResponse = await fetchWithAuth('/home/products');
        if (productsResponse.ok) {
            const productsData = await productsResponse.json();
            const productsCount = productsData.result ? productsData.result.length : 0;
            document.getElementById('totalProducts').textContent = productsCount;
        }

        // Placeholder values for now
        document.getElementById('totalProducts').textContent = '0';
        document.getElementById('totalRoles').textContent = '0';
        document.getElementById('totalPermissions').textContent = '0';

    } catch (error) {
        console.error('Error loading stats:', error);
        // Set default values
        document.getElementById('totalProducts').textContent = '-';
        document.getElementById('totalCategories').textContent = '-';
        document.getElementById('totalRoles').textContent = '-';
        document.getElementById('totalPermissions').textContent = '-';
    }
}

// Logout function
function logout() {
    if (confirm('Are you sure you want to logout?')) {
        localStorage.removeItem('authToken');
        window.location.href = '/home/login.html';
    }
}

// Initialize page
window.addEventListener('load', () => {
    if (checkAuth()) {
        loadUserInfo();
        loadQuickStats();
    }
});

// Logout event listener
document.getElementById('logoutBtn').addEventListener('click', logout);