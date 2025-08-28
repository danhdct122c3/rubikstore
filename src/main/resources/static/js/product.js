const API_PRODUCT = '/home/products';
const API_CATEGORY = '/home/categories';
// Create Role
document.getElementById('createProductForm').addEventListener('submit', async (e) => {
    e.preventDefault();



    const categorySelect = document.getElementById('loadCategory');
    const selectedCategory = Array.from(categorySelect.selectedOptions).map(option => option.value);

    const ProductData = {
        productName: document.getElementById('product-name').value,
        description: document.getElementById('product-description').value,
        quantity: document.getElementById('product-quantity').value,
        price: document.getElementById('product-price').value,

        categories: selectedCategory
    };

    try {
        console.log('Calling API:', API_PRODUCT); // ✅ Debug log
        console.log('category:', selectedCategory);

        const response = await fetchWithAuth(API_PRODUCT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(ProductData)
        });
        if (!response) return;
        const result = await response.json();



        if (response.ok) {
            alert('product created successfully!');
            document.getElementById('createProductForm').reset();
            loadProducts();
        } else {
            alert('Error: ' + result.message);
        }
    } catch (error) {
        console.error('Full error:', error); // ✅ Better error logging
        alert('Error: ' + error.message);
    }
});

// Load All Product
document.getElementById('loadProduct').addEventListener('click', loadProducts);

async function loadProducts() {

        // ✅ Debug token
        const token = localStorage.getItem('authToken');
        console.log('Current token:', token ? token: 'No token found');

        try {
        console.log('Loading products from:', API_PRODUCT); // ✅ Debug log

        const response = await fetchWithAuth(API_PRODUCT);
        // ✅ Kiểm tra null response trước khi đọc properties
        if (!response) {
            console.log('No response received from fetchWithAuth');
            return;
        }

        console.log('Response status:', response.status); // ✅ Debug log
        console.log('Response URL:', response.url); // ✅ Debug log

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();

        const productList = document.getElementById('productList');
        productList.innerHTML = '';

        if (data.result && data.result.length > 0) {
            data.result.forEach(product => {
                const productDiv = document.createElement('div');
                productDiv.className = 'item';
                productDiv.innerHTML = `
                    <h3>${product.productName}</h3>
                    <p>${product.description}</p>
                    <p>${product.quantity}</p>
                    <p>${product.price}</p>
                  <p>Category: ${product.categories?.map(c => c.name).join(', ') || 'None'}</p>    <!--  sử dụng map vì perrmission trả về object phải map để chuyển về array -->
                    <button onclick="deleteProduct('${product.id}','${product.productName}')">Delete</button>
                `;
                productList.appendChild(productDiv);
            });
        } else {
            productList.innerHTML = '<p>No product found</p>';
        }
    } catch (error) {
        console.error('Full error:', error); // ✅ Better error logging
        alert('Error loading products: ' + error.message);
    }
}

async function loadCategory() {
    try {
        console.log('Loading category from:', API_CATEGORY); // ✅ Debug log

        const response = await fetchWithAuth(API_CATEGORY);

        console.log('Response status:', response.status); // ✅ Debug log
        console.log('Response URL:', response.url); // ✅ Debug log

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();

        const categorySelect = document.getElementById('loadCategory');
        categorySelect.innerHTML = '';

        //  Add default option
        const defaultOption = document.createElement('option');
        defaultOption.value = '';
        defaultOption.textContent = 'Select a category';
        categorySelect.appendChild(defaultOption);

        if (data.result && data.result.length > 0) {
            data.result.forEach(category => {

                const optionElement = document.createElement('option');
                optionElement.value = category.id; // ✅ Set value
                optionElement.textContent = category.name; // ✅ Set display text

                categorySelect.appendChild(optionElement);

            });
        } else {
            categorySelect.innerHTML = ' <option>no category</option>';
        }
    } catch (error) {
        console.error('Full error:', error); // ✅ Better error logging
        alert('Error loading roles: ' + error.message);
    }
}

// Delete Product
async function deleteProduct(id,productName) {
    if (!confirm(`Are you sure you want to delete product: ${productName}?`)) {
        return;
    }

    try {
        const response = await fetchWithAuth(`${API_PRODUCT}/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }

        const result = await response.json();

        alert('Role deleted successfully!');
        loadProducts();
    } catch (error) {
        console.error('Full error:', error);
        alert('Error: ' + error.message);
    }
}

// Load roles on page load
window.addEventListener('load',  () => {
    if (checkAuth()) { // Function từ auth.js
        loadProducts();
        loadCategory();
    }
});
