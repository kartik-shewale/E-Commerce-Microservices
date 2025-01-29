document.addEventListener("DOMContentLoaded", function() {
	
	const params = new URLSearchParams(window.location.search);
	const id = params.get('userId');
	
	fetch('/MyShop/customer/'+id)
	    .then(response => response.json())
	    .then(data => {
			
			const userImage = document.getElementById("userImage");
			const username = document.getElementById("userName");

			userImage.src = data.userImage;
			username.textContent = data.userName;
			document.getElementById('userId').value = data.custId;
			getAllCartProduct(document.getElementById('userId').value);
	    })
	    .catch(error => {
	      console.error('Error fetching User Data:', error);
	    });  
});
function deleteItem(id){
	const url = `/MyShop/cartItem/${id}`; 
	fetch(url, {
	    method: 'DELETE',
	    headers: { 'Content-Type': 'application/json' }
	})
	.then(response => response.json())
	.then(data => {
    	console.log(data.message); 
		getAllCartProduct(document.getElementById('userId').value); 
		console.log('Item deleted successfully!');
	})
	.catch(() => {
	    console.error('Error:', data.message);
	});

}
function getAllCartProduct(id){
	fetch('/MyShop/cart/'+id)
	    .then(response => response.json())
	    .then(data => {
	      const productsContainer = document.getElementById('products-container');
		  productsContainer.innerHTML = "";
		  const items = data.Item;
		  calculateTotalAmount(items);
		  
	      items.forEach(Item => {
	        const productCard = document.createElement('div');
	        productCard.classList.add('product-card');

	        productCard.innerHTML = `
	          <img src="https://dummyimage.com/150x150" alt="Product Image" class="product-img" />
	          <div class="product-details">
			  
	            <h2 class="product-title">${Item.itemName}</h2>
				<span class="product-id">${Item.id}</span>
				<span class="item-id">${Item.itemId}</span>
	            <p class="product-description">${Item.itemDesc}</p>
	            <p class="product-price">$${Item.itemPrice}</p>
	            <div class="quantity-controls">
	              <div>
	                <button class="quantity-btn quantity-minus">-</button>
	                <span class="quantity-label">${Item.itemQuantity}</span>
	                <button class="quantity-btn quantity-plus">+</button>
	              </div>
	              <div>
	                <button class="btn add-to-cart"  onclick="deleteItem('${Item.id}')">Remove</button>
	              </div>
	            </div>
	          </div>
	        `;

	        // Add event listeners for the buttons
	        const quantityMinusBtn = productCard.querySelector('.quantity-minus');
	        const quantityPlusBtn = productCard.querySelector('.quantity-plus');
	        const quantityLabel = productCard.querySelector('.quantity-label');


			const productId = productCard.querySelector('.product-id');
			let price = productCard.querySelector('.product-price').textContent;
			price = parseInt(price.replace('$', ''));
			
			console.log(`Id ${productId.textContent} `);
			
	        quantityMinusBtn.addEventListener('click', function() {
	          let currentQuantity = parseInt(quantityLabel.textContent);
	          if (currentQuantity > 1) {
				
				const totalAmountElement = document.getElementById('totalAmount');
				let total = parseFloat(totalAmountElement.textContent.replace('$', ''));
				total -= price;
				totalAmountElement.textContent = `$${total.toFixed(2)}`;
				
				const addToCart = {
				  	itemId: productCard.querySelector('.item-id').textContent,
				      userId: document.getElementById('userId').value,
				      itemName: productCard.querySelector('.product-title').textContent,
				      itemDesc: productCard.querySelector('.product-description').textContent,
				      itemPrice:price,
				      itemQuantity: -1
				  };
				  updateCart(addToCart);

	            quantityLabel.textContent = currentQuantity - 1;
	          }
	        });

	        quantityPlusBtn.addEventListener('click', function() {
	          let currentQuantity = parseInt(quantityLabel.textContent);
			  
			  const totalAmountElement = document.getElementById('totalAmount');
			  let total = parseFloat(totalAmountElement.textContent.replace('$', ''));
			  total += price;
			  totalAmountElement.textContent = `$${total.toFixed(2)}`;
			  
			  const addToCart = {
			  	  itemId: productCard.querySelector('.item-id').textContent,
			      userId: document.getElementById('userId').value,
			      itemName: productCard.querySelector('.product-title').textContent,
			      itemDesc: productCard.querySelector('.product-description').textContent,
			      itemPrice:price ,
			      itemQuantity: 1
			  };
			  updateCart(addToCart);
			  
			  
	          quantityLabel.textContent = currentQuantity + 1;
	        });

			/*const addToCartBtn = productCard.querySelector('.add-to-cart');
	        addToCartBtn.addEventListener('click', function() {
	          const quantity = parseInt(quantityLabel.textContent);
	          console.log(`Added ${quantity} of ${Item.name} to the cart`);
	        });*/

	        productsContainer.appendChild(productCard);
	      });
	    })
	    .catch(error => {
	      console.error('Error fetching products:', error);
	    });
}

function updateCart(addToCart){

	
	try {
         const response =fetch('/MyShop/cart', {
           method: "POST",
           headers: { "Content-Type": "application/json" },
           body: JSON.stringify(addToCart),
         });
   
         if (response.ok) {
           const data = response.json();
		   console.log(data);					
         } else {
         }
       } catch (error) {
         console.error("Error during adding:", error);
       }
	
}


document.getElementById("productLink").addEventListener('click', function(event) {
    event.preventDefault(); 
	window.history.length > 1 ? window.history.back() : window.location.href = '/MyShop/login';
});

function calculateTotalAmount(items) {
    const totalAmountElement = document.getElementById('totalAmount');
    let total = 0;

    items.forEach(item => {
        total += item.itemPrice * item.itemQuantity;
    });

    totalAmountElement.textContent = `$${total.toFixed(2)}`;
}

document.getElementById("proceedBtn").addEventListener('click', function(event) {
    event.preventDefault(); 
	
	const totalAmountElement = document.getElementById('totalAmount');
	let total = parseFloat(totalAmountElement.textContent.replace('$', ''));
	
	if(total == 0)return;
	const userId = document.getElementById('userId').value;	
	const data = { userId: userId,total:total};
	const queryString = new URLSearchParams(data).toString();
	
	window.location.href = `/MyShop/payment?${queryString}`;
});