document.addEventListener("DOMContentLoaded", function () {
	

	//fetchOrders();
	

	
	
    const addCategoryLink = document.getElementById("addCatagoryBtn");
	const addingErrorMessage = document.getElementById("addingErrorMessage");
	
	addCategoryLink.addEventListener("click", async () => {
			const category = document.getElementById("categoryName").value.trim();
			const catagaoryDesc = document.getElementById("categoryDescription").value.trim();
			addingErrorMessage.textContent = "";
			
		       if (!category || !catagaoryDesc) {
		         addingErrorMessage.textContent = "catagory and catagory description are required.";
		         return;
		       }
		       try {
		         const response = await fetch('catagory', {
		           method: "POST",
		           headers: { "Content-Type": "application/json" },
		           body: JSON.stringify({ category, catagaoryDesc }),
		         });
		   
		         if (response.ok) {
		           const data = await response.json();
					//alert(data.message);
			          // window.location.href = data.redirect || "/dashboard";
					   showMessageBox(data.message,"OK");
					  
					  document.getElementById("categoryName").value = "";
					  document.getElementById("categoryDescription").value ="";
					  addAllCatagory();
		         } else {
		           const errorData = await response.json();
				alert(errorData.message);
		           addingErrorMessage.textContent = errorData.message || "Failed to add Catagory.";
		         }
		       } catch (error) {
		         console.error("Error during adding:", error);
		         showMessageBox("An unexpected error occurred.","OK");
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
	fetchCatagoryForAddproduct()
    document.getElementById("categoriesSection").style.display = "none";
    document.getElementById("stocksSection").style.display = "none";
    document.getElementById("ordersSection").style.display = "none";
    document.getElementById("addProductFormContainer").style.display = "block";
}


function showMessageBox(message,buttonText)
{
	const messageBox = document.getElementById('messageBox');
	//const showMessageBoxBtn = document.getElementById('showMessageBoxBtn');
	const closeMessageBoxBtn = document.getElementById('closeMessageBoxBtn');
	//const messageTitle = document.querySelector('.message-box-content h3');
	const messageText = document.querySelector('.message-box-content p');

	messageText.textContent = message;
	closeMessageBoxBtn.textContent = buttonText;
	messageBox.style.display = 'block';
}
function showBootBox(){
	const bootBox = document.getElementById("customModal");
	const openModalBtn = document.getElementById("openModalBtn");
	const confirmBtn = document.getElementById("confirmBtn");
	const cancelBtn = document.getElementById("cancelBtn");
	

	openModalBtn.addEventListener('click', () => {
	    bootBox.style.display = 'block';
	});

	const closeModalBootbox = () => {
	    bootBox.style.display = 'none';
	};
	cancelBtn.addEventListener('click', closeModal);

	confirmBtn.addEventListener('click', () => {
	    alert('Action Confirmed!');
	    closeModalBootbox();
	});
	window.addEventListener('click', (event) => {
	    if (event.target === bootBox) {
	        closeModalBootbox();
	    }
	});
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
		    <h3 class="category-title">${catagory.category}</h3>
		    <p class="category-description">${catagory.catagaoryDesc}</p>
		  </div>`;
	    categoryItems.appendChild(listItem);
      });
    })
    .catch(error => {
      console.error('Error fetching products:', error);
    });
}

function deleteProduct(id){
	const url = `/MyShop/product/${id}`; 
	fetch(url, {
	    method: 'DELETE',
	    headers: { 'Content-Type': 'application/json' }
	})
	.then(response => response.json())
	.then(data => {
    	console.log(data.message);  
		console.log('Product deleted successfully!');
	})
	.catch(() => {
	    console.error('Error:', data.message);
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
			
			<div class="button-row"> <button class="btn" onclick="editProduct('${product.id}')">Edit</button>
	            <button class="btn" onclick="deleteProduct('${product.id}')">Delete</button>
	        </div>`;
	    stocksList.appendChild(productCard);
	  });
	})
	.catch(error => {
	  console.error('Error fetching products:', error);
	});
}


function fetchCategory(){

	const dropdown = document.getElementById("selectedValue");
	dropdown.innerHTML = "";

	fetch('/admin/catagory')
	    .then(response => response.json()) // Parse the JSON from the response
	    .then(data => {
	       
        console.log("API Response:", data);

        const categories = data.catagory;
        if (Array.isArray(categories)) { 
			categories.forEach(category => {
				const newOption = document.createElement("option");
				newOption.value = category.category;
				newOption.text = category.category;
				dropdown.add(newOption);
			});
        } else {
            console.error("Catagory is not an array or is undefined");
        }
	    })
	    .catch(error => {
	        console.error("Error fetching categories:", error);
	    });
}

function editProduct(id) {
	
	fetchCategory();
	
	fetch('/MyShop/product/'+id)
    .then(response => response.json())
	    .then(data => {
		const product =  data.product;
		if (product) {
		       // Populate the modal with the product details
		       document.getElementById('editProductId').value = product.id;
		       document.getElementById('editProductName').value = product.name;
		       document.getElementById('editProductDescription').value = product.description;
		       document.getElementById('editProductPrice').value = product.price;
		       document.getElementById('editProductQuantity').value = product.quantity;
			   document.getElementById("selectedValue").value = product.category;
		      // document.getElementById('editProductCategory').value = product.category;
			   
		       document.getElementById('editModal').style.display = 'block';
		   }
	    })
	    .catch(error => {
	      console.error('Error fetching User Data:', error);
	    });
	}

function closeModal() {
    document.getElementById('editModal').style.display = 'none';
}


const updateProductBtn = document.getElementById("updateProductbtn");
const addingErrorMessage = document.getElementById("addingErrorMessage");
updateProductBtn.addEventListener("click", async () => {
	
    	const editedProduct = {
        id: document.getElementById('editProductId').value,
        name: document.getElementById('editProductName').value,
        description: document.getElementById('editProductDescription').value,
        price: document.getElementById('editProductPrice').value,
        quantity: document.getElementById('editProductQuantity').value,
        category: document.getElementById('selectedValue').value
    };
	
	try {
         const response = await fetch('/MyShop/addUpdateProduct', {
           method: "POST",
           headers: { "Content-Type": "application/json" },
           body: JSON.stringify(editedProduct),
         });
   
         if (response.ok) {
           const data = await response.json();
		   showMessageBox(data.message,"OK");
		   console.log(data);
			
         } else {
          // const errorData = await response.json();
		
         }
       } catch (error) {
         console.error("Error during adding:", error);
          showMessageBox(error,"OK");
       }
	
    updateProduct(editedProduct);
});

function updateProduct(product) {
	showStockDetails();
    console.log("Product updated:", product);
    closeModal();
}

function fetchCatagoryForAddproduct(){
	const dropdown = document.getElementById("selectedValueAddproduct");
	dropdown.innerHTML = `<option value="" disabled selected>Select Category</option>`;
	fetch('/admin/catagory')
    .then(response => response.json())
    .then(data => {
        console.log("API Response:", data);
        const categories = data.catagory;
        if (Array.isArray(categories)) {
			categories.forEach(category => {
				const newOption = document.createElement("option");
				newOption.value = category.category;
				newOption.text = category.category;
				dropdown.add(newOption);
			});
				
        } else {
            console.error("Catagory is not an array or is undefined");
        }
    })
    .catch(error => {
        console.error("Error fetching categories:", error);
    });	
}
const dropdown = document.getElementById("selectedValueAddproduct");

dropdown.addEventListener("change", function () {
  if (dropdown.value !== "") {
    dropdown.classList.add("option-selected");
  } else {
    dropdown.classList.remove("option-selected");
  }
});


const addProductBtn = document.getElementById("newProductAdd");
const addProductMessage = document.getElementById("addProductErrorMsg");
addProductBtn.addEventListener("click", async () => {
	
    	const editedProduct = {
        name: document.getElementById('productName').value,
        description: document.getElementById('productDesc').value,
        price: document.getElementById('productPrice').value,
        quantity: document.getElementById('productStock').value,
        category: document.getElementById('selectedValueAddproduct').value
    };
	
	try {
         const response = await fetch('/MyShop/addUpdateProduct', {
           method: "POST",
           headers: { "Content-Type": "application/json" },
           body: JSON.stringify(editedProduct),
         });
   
         if (response.ok) {
           const data = await response.json();
		   console.log(data);
		    showMessageBox(data.message,"OK");
			makeAddProductFieldEmpty();
			
         } else {
           const errorData = await response.json();
			 showMessageBox(errorData,"OK");
		
         }
       } catch (error) {
         console.error("Error during adding:", error);
		 showMessageBox("An unexpected error occurred.","OK");
       }
});

function makeAddProductFieldEmpty(){
	document.getElementById('productName').value = "";
	document.getElementById('productDesc').value = "";
	document.getElementById('productPrice').value = "";
	document.getElementById('productStock').value = "";
	document.getElementById('selectedValueAddproduct').value = "";
}

const messageBox = document.getElementById('messageBox');
const closeMessageBoxBtn = document.getElementById('closeMessageBoxBtn');

closeMessageBoxBtn.addEventListener('click', () => {
    messageBox.style.display = 'none';
});
window.addEventListener('click', (event) => {
    if (event.target === messageBox) {
        messageBox.style.display = 'none';
    }
});
