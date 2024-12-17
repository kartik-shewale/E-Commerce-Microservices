document.addEventListener("DOMContentLoaded", function () {
	

	//fetchOrders();
	
    const addCategoryLink = document.getElementById("addCatagoryBtn");
	const addingErrorMessage = document.getElementById("addingErrorMessage");
	
	addCategoryLink.addEventListener("click", async () => {
			const catagory = document.getElementById("categoryName").value.trim();
			const catagaoryDesc = document.getElementById("categoryDescription").value.trim();
			addingErrorMessage.textContent = "";
			
		       if (!catagory || !catagaoryDesc) {
		         addingErrorMessage.textContent = "catagory and catagory description are required.";
		         return;
		       }
		       try {
		         const response = await fetch('catagory', {
		           method: "POST",
		           headers: { "Content-Type": "application/json" },
		           body: JSON.stringify({ catagory, catagaoryDesc }),
		         });
		   
		         if (response.ok) {
		           const data = await response.json();
					//alert(data.message);
			          // window.location.href = data.redirect || "/dashboard";
					  addingErrorMessage.textContent = data.message;
		         } else {
		           const errorData = await response.json();
				alert(errorData.message);
		           addingErrorMessage.textContent = errorData.message || "Failed to add Catagory.";
		         }
		       } catch (error) {
		         console.error("Error during adding:", error);
		         addingErrorMessage.textContent = "An unexpected error occurred.";
		       }
		     });

});

// Function to show the "Add Product" form
function showAddProductForm() {
    document.getElementById('addProductFormContainer').classList.remove('hidden');
    document.getElementById('stocksSection').classList.add('hidden');
}

// Function to cancel adding a product
function cancelAddProductForm() {
    document.getElementById('addProductFormContainer').classList.add('hidden');
    document.getElementById('stocksSection').classList.remove('hidden');
}

function showAddCategory() {
	
	addAllCatagory();
    document.getElementById("categoriesSection").style.display = "block";
    document.getElementById("stocksSection").style.display = "none";
    document.getElementById("ordersSection").style.display = "none";
    document.getElementById("addProductFormContainer").style.display = "none";
}

function showStocks() {
	showStockDetails()
    document.getElementById("categoriesSection").style.display = "none";
    document.getElementById("stocksSection").style.display = "block";
    document.getElementById("ordersSection").style.display = "none";
    document.getElementById("addProductFormContainer").style.display = "none";
}

function showOrders() {
    document.getElementById("categoriesSection").style.display = "none";
    document.getElementById("stocksSection").style.display = "none";
    document.getElementById("ordersSection").style.display = "block";
    document.getElementById("addProductFormContainer").style.display = "none";
}

function showAddProduct() {
    document.getElementById("categoriesSection").style.display = "none";
    document.getElementById("stocksSection").style.display = "none";
    document.getElementById("ordersSection").style.display = "none";
    document.getElementById("addProductFormContainer").style.display = "block";
}

function addAllCatagory(){
	
	
	fetch('/admin/catagory')
	    .then(response => response.json())
	    .then(data => {
			const categories = data.catagory;
			console.log(data.catagory)

		  const categoryItems = document.getElementById('categoriesList');
		  categoryItems.innerHTML = `<h2>Available Categories</h2>`;
	      categories.forEach(catagory => {
			const listItem  = document.createElement('div');
			
			listItem.className = 'category-item';

			listItem.innerHTML = `
			  <div class="category-card">
			    <h3 class="category-title">${catagory.catagory}</h3>
			    <p class="category-description">${catagory.catagaoryDesc}</p>
			  </div>
			`;

	        categoryItems.appendChild(listItem);
	      });
	    })
	    .catch(error => {
	      console.error('Error fetching products:', error);
	    });
}

function showStockDetails(){
	
	fetch('/MyShop/getProducts')
	    .then(response => response.json())
	    .then(data => {
			const stocksList = document.getElementById('stocksList');
			stocksList.innerHTML = ''; 
			
			data.forEach(product => {
			    const productCard = document.createElement('div');
			    productCard.classList.add('product-card');
			    
			    productCard.innerHTML = `
			        <h3 class="product-title">${product.name}</h3>
			        <p class="product-description">${product.description}</p>
			        <p><strong>Price:</strong> $${product.price}</p>
			        <p><strong>Quantity:</strong> ${product.quantity}</p>
			        <p><strong>Category:</strong> ${product.category}</p>
			        <button class="btn" onclick="editProduct(${product.id})">Edit</button>
			        <button class="btn" onclick="deleteProduct(${product.id})">Delete</button>
			    `;
			    
			    stocksList.appendChild(productCard);
	      });
	    })
	    .catch(error => {
	      console.error('Error fetching products:', error);
	    });
}

	
