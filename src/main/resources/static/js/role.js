const API_ROLE = '/home/roles';
const API_PERMISSION = '/home/permission';
// Create Role
document.getElementById('createRoleForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const permissionSelect = document.getElementById('loadPermission');
    const selectedPermissions = Array.from(permissionSelect.selectedOptions).map(option => option.value);

    const roleData = {
        name: document.getElementById('roleName').value,
        description: document.getElementById('roleDescription').value,
        permissions: selectedPermissions
    };

    try {
        console.log('Calling API:', API_ROLE); // ✅ Debug log

        const response = await fetch(API_ROLE, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(roleData)
        });

        const result = await response.json();

        if (response.ok) {
            alert('Role created successfully!');
            document.getElementById('createRoleForm').reset();
            loadRoles();
        } else {
            alert('Error: ' + result.message);
        }
    } catch (error) {
        console.error('Full error:', error); // ✅ Better error logging
        alert('Error: ' + error.message);
    }
});

// Load All Roles
document.getElementById('loadRoles').addEventListener('click', loadRoles);

async function loadRoles() {
    try {
        console.log('Loading roles from:', API_ROLE); // ✅ Debug log

        const response = await fetch(API_ROLE);

        console.log('Response status:', response.status); // ✅ Debug log
        console.log('Response URL:', response.url); // ✅ Debug log

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();

        const rolesList = document.getElementById('rolesList');
        rolesList.innerHTML = '';

        if (data.result && data.result.length > 0) {
            data.result.forEach(role => {
                const roleDiv = document.createElement('div');
                roleDiv.className = 'role-item';
                roleDiv.innerHTML = `
                    <h3>${role.name}</h3>
                    <p>${role.description}</p>
                  <p>Permissions: ${role.permissions?.map(p => p.name).join(', ') || 'None'}</p>    <!--  sử dụng map vì perrmission trả về object phải map để chuyển về array -->
                    <button onclick="deleteRole('${role.name}')">Delete</button>
                `;
                rolesList.appendChild(roleDiv);
            });
        } else {
            rolesList.innerHTML = '<p>No roles found</p>';
        }
    } catch (error) {
        console.error('Full error:', error); // ✅ Better error logging
        alert('Error loading roles: ' + error.message);
    }
}

async function LoadPermission() {
    try {
        console.log('Loading permission from:', API_PERMISSION); // ✅ Debug log

        const response = await fetch(API_PERMISSION);

        console.log('Response status:', response.status); // ✅ Debug log
        console.log('Response URL:', response.url); // ✅ Debug log

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();

        const permissionSelect = document.getElementById('loadPermission');
        permissionSelect.innerHTML = '';

        //  Add default option
        const defaultOption = document.createElement('option');
        defaultOption.value = '';
        defaultOption.textContent = 'Select a permission';
        permissionSelect.appendChild(defaultOption);

        if (data.result && data.result.length > 0) {
            data.result.forEach(permission => {

                const optionElement = document.createElement('option');
                optionElement.value = permission.name; // ✅ Set value
                optionElement.textContent = permission.name; // ✅ Set display text

                permissionSelect.appendChild(optionElement);



            });
        } else {
            rolesList.innerHTML = ' <option>no permission</option>';
        }
    } catch (error) {
        console.error('Full error:', error); // ✅ Better error logging
        alert('Error loading roles: ' + error.message);
    }
}

// Delete Role
async function deleteRole(roleName) {
    if (!confirm(`Are you sure you want to delete role: ${roleName}?`)) {
        return;
    }

    try {
        const response = await fetch(`${API_ROLE}/${roleName}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }

        const result = await response.json();

        alert('Role deleted successfully!');
        loadRoles();
    } catch (error) {
        console.error('Full error:', error);
        alert('Error: ' + error.message);
    }
}

// Load roles on page load
window.addEventListener('load', loadRoles);
window.addEventListener('load', LoadPermission);