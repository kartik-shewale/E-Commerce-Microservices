document.addEventListener("DOMContentLoaded", function() {
	
	const params = new URLSearchParams(window.location.search);
	const id = params.get('data');
	
	var cartItems = [];
	
fetch('/MyShop/customer/'+id)
    .then(response => response.json())
    .then(data => {
		
		const userImage = document.getElementById("userImage");
		const username = document.getElementById("userName");

		userImage.src = data.userImage;
		username.textContent = data.userName;
		document.getElementById('userId').value = data.custId;
    })
    .catch(error => {
      console.error('Error fetching User Data:', error);
});
		
fetch('/MyShop/IdByuserId/'+id)
    .then(response => response.json())
    .then(data => {
		cartItems = data.itemIds;
		console.log(cartItems);
    })
    .catch(error => {
    console.error('Error fetching Cart Data:', error);
});
	
  fetch('/MyShop/getProducts')
    .then(response => response.json())
    .then(data => {
      const productsContainer = document.getElementById('products-container');

     data.forEach(product => {
        const productCard = document.createElement('div');
        productCard.classList.add('product-card');
		const isInCart = cartItems.includes(product.id);
        productCard.innerHTML = `
          <img src="https://dummyimage.com/150x150" alt="Product Image" class="product-img" />
          <div class="product-details">
		  
            <h2 class="product-title">${product.name}</h2>
			<span class="product-id">${product.id}</span>
            <p class="product-description">${product.description}</p>
            <p class="product-price">${product.price}</p>
            <div class="quantity-controls">
              <div>
                <button class="quantity-btn quantity-minus">-</button>
                <span class="quantity-label">1</span>
                <button class="quantity-btn quantity-plus">+</button>
              </div>
              <div>
			  <button class="btn add-to-cart" style="background-color: ${isInCart ? 'grey' : 'green'};">
			                ${isInCart ? 'In Cart' : 'Add to Cart'}
			              </button>
              </div>
            </div>
          </div>
        `;

        const quantityMinusBtn = productCard.querySelector('.quantity-minus');
        const quantityPlusBtn = productCard.querySelector('.quantity-plus');
        const quantityLabel = productCard.querySelector('.quantity-label');
        const addToCartBtn = productCard.querySelector('.add-to-cart');

		const productId = productCard.querySelector('.product-id');
		
		
		console.log(`Id ${productId.textContent} `);
		
        quantityMinusBtn.addEventListener('click', function() {
          let currentQuantity = parseInt(quantityLabel.textContent);
          if (currentQuantity > 1) {
            quantityLabel.textContent = currentQuantity - 1;
          }
        });
        quantityPlusBtn.addEventListener('click', function() {
          let currentQuantity = parseInt(quantityLabel.textContent);
          quantityLabel.textContent = currentQuantity + 1;
        });
		
        addToCartBtn.addEventListener('click',async () => {
			
			
		    	const addToCart = {
				itemId: productCard.querySelector('.product-id').textContent,
		        userId: document.getElementById('userId').value,
		        itemName: productCard.querySelector('.product-title').textContent,
		        itemDesc: productCard.querySelector('.product-description').textContent,
		        itemPrice: productCard.querySelector('.product-price').textContent,
		        itemQuantity: productCard.querySelector('.quantity-label').textContent
		    };
			
			try {
		         const response = await fetch('/MyShop/cart', {
		           method: "POST",
		           headers: { "Content-Type": "application/json" },
		           body: JSON.stringify(addToCart),
		         });
		   
		         if (response.ok) {
		           const data = await response.json();
				   cartItems.push(product.id);
	               addToCartBtn.style.backgroundColor = 'grey';
	               addToCartBtn.textContent = 'In Cart';
				   console.log(data);					
		         } else {
		           const errorData = await response.json();
					 showMessageBox(errorData,"OK");
				
		         }
		       } catch (error) {
		         console.error("Error during adding:", error);
				 showMessageBox("An unexpected error occurred.","OK");
		       }
		});
        productsContainer.appendChild(productCard);
      });
    })
    .catch(error => {
      console.error('Error fetching products:', error);
    });
});

document.getElementById('cartLink').addEventListener('click', function(event) {
    event.preventDefault(); 
	
	const userId = document.getElementById('userId').value;	
	const data = { userId: userId};
	const queryString = new URLSearchParams(data).toString();
	window.location.href = `/MyShop/cart?${queryString}`;
});

