const API_ROLE = '/home/roles';
const API_PERMISSION = '/home/permission';

// Create Role
document.getElementById('createPermissionForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const permissionSelect = document.getElementById('loadPermission');

    const permissionData = {
        name: document.getElementById('permissionName').value,
        description: document.getElementById('permissionDescription').value,

    };

    try {
        console.log('Calling API:', API_PERMISSION); // ✅ Debug log

        const response = await fetchWithAuth(API_PERMISSION, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(permissionData)
        });

        const result = await response.json();

        if (response.ok) {
            alert('Role created successfully!');
            document.getElementById('createPermissionForm').reset();
            loadPermissions();
        } else {
            alert('Error: ' + result.message);
        }
    } catch (error) {
        console.error('Full error:', error); // ✅ Better error logging
        alert('Error: ' + error.message);
    }
});

// Load All Roles
document.getElementById('loadPermission').addEventListener('click', loadPermissions);

async function loadPermissions() {
    try {
        console.log('Loading roles from:', API_PERMISSION); // ✅ Debug log

        const response = await fetchWithAuth(API_PERMISSION);

        console.log('Response status:', response.status); // ✅ Debug log
        console.log('Response URL:', response.url); // ✅ Debug log

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();

        const permissionsList = document.getElementById('permissionList');
        permissionsList.innerHTML = '';

        if (data.result && data.result.length > 0) {
            data.result.forEach(permission => {
                const permissionDiv = document.createElement('div');
                permissionDiv.className = 'item';
                permissionDiv.innerHTML = `
                    <h3>${permission.name}</h3>
                    <p>${permission.description}</p>
                    <button onclick="deletePermission('${permission.name}')">Delete</button>
                `;
                permissionsList.appendChild(permissionDiv);
            });
        } else {
            permissionsList.innerHTML = '<p>No permissions found</p>';
        }
    } catch (error) {
        console.error('Full error:', error); // ✅ Better error logging
        alert('Error loading permissions: ' + error.message);
    }
}



// Delete Role
async function deletePermission(permissionName) {
    if (!confirm(`Are you sure you want to delete permission: ${permissionName}?`)) {
        return;
    }

    try {
        const response = await fetchWithAuth(`${API_PERMISSION}/${permissionName}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }

        const result = await response.json();

        alert('Permission deleted successfully!');
        await loadPermissions();
    } catch (error) {
        console.error('Full error:', error);
        alert('Error: ' + error.message);
    }
}

// Load roles on page load
window.addEventListener('load',() => {
    if(checkAuth()) loadPermissions();
});