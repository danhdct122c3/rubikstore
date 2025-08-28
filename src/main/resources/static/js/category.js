
const API_CATEGORY = '/home/categories';
// Create Role
document.getElementById('createCategoryForm').addEventListener('submit', async (e) => {
    e.preventDefault();


    const categoryData = {
        name: document.getElementById('category-name').value,


    };

    try {
        console.log('Calling API:', API_CATEGORY); // ✅ Debug log


        const response  =await fetchWithAuth(API_CATEGORY, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(categoryData)
        });

        const result = await response.json();



        if (response.ok) {
            alert('category created successfully!');
            document.getElementById('createCategoryForm').reset();
            loadCategories();
        } else {
            alert('Error: ' + result.message);
        }
    } catch (error) {
        console.error('Full error:', error); // ✅ Better error logging
        alert('Error: ' + error.message);
    }
});

// Load All Product
document.getElementById('loadCategory').addEventListener('click', loadCategories);

async function loadCategories() {
    try {
        console.log('Loading categories from:', API_CATEGORY); // ✅ Debug log

        const response= await fetchWithAuth(API_CATEGORY);

        console.log('Response status:', response.status); // ✅ Debug log
        console.log('Response URL:', response.url); // ✅ Debug log

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();

        const categoryList = document.getElementById('categoryList');
        categoryList.innerHTML = '';

        if (data.result && data.result.length > 0) {
            data.result.forEach(category => {
                const categoryDiv = document.createElement('div');
                categoryDiv.className = 'item';
                categoryDiv.innerHTML = `
                    <h3>${category.name}</h3>
                      <button onclick="deleteCategory('${category.id}','${category.name}')">Delete</button>
                `;
                categoryList.appendChild(categoryDiv);
            });
        } else {
            categoryList.innerHTML = '<p>No product found</p>';
        }
    } catch (error) {
        console.error('Full error:', error); // ✅ Better error logging
        alert('Error loading products: ' + error.message);
    }
}


// Delete Product
async function deleteCategory(id,name) {
    if (!confirm(`Are you sure you want to delete category: ${name}?`)) {
        return;
    }

    try {
        const response = await fetchWithAuth(`${API_CATEGORY}/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }



        alert('category deleted successfully!');
        loadCategories();
    } catch (error) {
        console.error('Full error:', error);
        alert('Error: ' + error.message);
    }
}

// Load roles on page load
window.addEventListener('load',  () => {
    if (checkAuth()) { // Function từ auth.js
        loadCategories();
    }
});