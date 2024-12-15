document.addEventListener("DOMContentLoaded", function() {
	
	const params = new URLSearchParams(window.location.search);
	const id = params.get('data');
	
	fetch('/MyShop/customer/'+id)
	    .then(response => response.json())
	    .then(data => {
			
			const userImage = document.getElementById("userImage");
			const username = document.getElementById("userName");

			userImage.src = data.userImage;
			username.textContent = data.userName;
			document.getElementById('userId').value = data.iserId;
	    })
	    .catch(error => {
	      console.error('Error fetching User Data:', error);
	    });
	
	
  // Function to fetch products from the server (replace URL with actual endpoint)
  fetch('/MyShop/getProducts')
    .then(response => response.json())
    .then(data => {
      const productsContainer = document.getElementById('products-container');

      // Loop through products and create cards dynamically
      data.forEach(product => {
        const productCard = document.createElement('div');
        productCard.classList.add('product-card');

        productCard.innerHTML = `
          <img src="https://dummyimage.com/150x150" alt="Product Image" class="product-img" />
          <div class="product-details">
		  
            <h2 class="product-title">${product.name}</h2>
			<span class="product-id">${product.id}</span>
            <p class="product-description">${product.description}</p>
            <p class="product-price">$${product.price}</p>
            <div class="quantity-controls">
              <div>
                <button class="quantity-btn quantity-minus">-</button>
                <span class="quantity-label">1</span>
                <button class="quantity-btn quantity-plus">+</button>
              </div>
              <div>
                <button class="btn add-to-cart">Add to Cart</button>
              </div>
            </div>
          </div>
        `;

        // Add event listeners for the buttons
        const quantityMinusBtn = productCard.querySelector('.quantity-minus');
        const quantityPlusBtn = productCard.querySelector('.quantity-plus');
        const quantityLabel = productCard.querySelector('.quantity-label');
        const addToCartBtn = productCard.querySelector('.add-to-cart');

		const productId = productCard.querySelector('.product-id');
		
		
		console.log(`Id ${productId.textContent} `);
		
        // Event listener for the minus button
        quantityMinusBtn.addEventListener('click', function() {
          let currentQuantity = parseInt(quantityLabel.textContent);
          if (currentQuantity > 1) {
            quantityLabel.textContent = currentQuantity - 1;
          }
        });

        // Event listener for the plus button
        quantityPlusBtn.addEventListener('click', function() {
          let currentQuantity = parseInt(quantityLabel.textContent);
          quantityLabel.textContent = currentQuantity + 1;
        });

        // Event listener for the add to cart button
        addToCartBtn.addEventListener('click', function() {
          const quantity = parseInt(quantityLabel.textContent);
          console.log(`Added ${quantity} of ${product.name} to the cart`);
          // You can handle the cart logic here, like adding it to an array or sending to the server
        });

        // Append the product card to the container
        productsContainer.appendChild(productCard);
      });
    })
    .catch(error => {
      console.error('Error fetching products:', error);
    });
});
