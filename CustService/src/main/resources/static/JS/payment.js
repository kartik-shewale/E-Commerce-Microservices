document.addEventListener("DOMContentLoaded", function() {
	
	const params = new URLSearchParams(window.location.search);
	const id = params.get('userId');
	const price = params.get('total'); 
	document.getElementById('finalAmount').value = price;
	fetch('/MyShop/customer/'+id)
	    .then(response => response.json())
	    .then(data => {
			
			const userImage = document.getElementById("userImage");
			const username = document.getElementById("userName");

			userImage.src = data.userImage;
			username.textContent = data.userName;
			document.getElementById('userId').value = data.custId;
			

			document.getElementById('UserName').value = data.userName;
			document.getElementById('fName').value = data.fName;
			document.getElementById('lName').value = data.lName;
			document.getElementById('mobile').value = data.mobile;
			document.getElementById('email').value = data.email;
			
			createAddress(data.address);
			//getAllCartProduct(document.getElementById('userId').value);
	    })
	    .catch(error => {
	      console.error('Error fetching User Data:', error);
      });
});

document.getElementById('addAddress').addEventListener('click', function(e) {
    e.preventDefault();
	document.getElementById('custForm').classList.add("hidden");
	document.getElementById('addressForm').classList.add("hidden");
	document.getElementById('makePaymentForm').classList.add("hidden");
	document.getElementById('newAddressForm').classList.remove("hidden");
	
});

document.getElementById('cancelAddress').addEventListener('click', function(e) {
    e.preventDefault();
	document.getElementById('custForm').classList.remove("hidden");
	document.getElementById('addressForm').classList.remove("hidden");
	document.getElementById('makePaymentForm').classList.remove("hidden");
	document.getElementById('newAddressForm').classList.add("hidden");
	
});

document.getElementById('addNewAddressBtn').addEventListener('click', async () => {
   
	const address = {
		custId: document.getElementById('userId').value,
		firstLine: document.getElementById('fLine').value,
		secondLine: document.getElementById('sLine').value,
		city: document.getElementById('city').value,
		state:document.getElementById('state').value,
		pincode:document.getElementById('pincode').value		
	};
	
	try {
         const response =await fetch('/MyShop/address', {
           method: "POST",
           headers: { "Content-Type": "application/json" },
           body: JSON.stringify(address),
         });
   
         if (response.ok) {
           const data =await response.json();
		   console.log(data);					
         } else {
         }
       } catch (error) {
         console.error("Error during adding:", error);
       }
	
	
	document.getElementById('custForm').classList.remove("hidden");
	document.getElementById('addressForm').classList.remove("hidden");
	document.getElementById('makePaymentForm').classList.remove("hidden");
	document.getElementById('newAddressForm').classList.add("hidden");
	
});

function addRadioButton(name, value, label) {
    const container = document.getElementById("radioContainer");

    const radioButton = document.createElement("input");
    radioButton.type = "radio";
    radioButton.name = name;
    radioButton.value = value;

    const radioLabel = document.createElement("label");
    radioLabel.textContent = label;

    // Create a wrapper div for the radio button and label
    const radioWrapper = document.createElement("div");
    radioWrapper.style.display = "flex";  // Using flexbox for alignment
    radioWrapper.style.alignItems = "center";  // Align items vertically
    radioWrapper.style.justifyContent = "space-between";  // Justify content with space between
    radioWrapper.style.marginBottom = "10px";  // Optional: add space between radio options

    // Add margin between the radio button and label
    radioButton.style.marginRight = "10px";  // Adjust this value for desired gap

    radioWrapper.appendChild(radioButton);
    radioWrapper.appendChild(radioLabel);

    // Append the radioWrapper to the container
    container.appendChild(radioWrapper);
}

 
 function createAddress(data){
	
	
	data.forEach(address => {
		var add = address.firstLine +" ";
		add+= address.secondLine + " ";
		add+= address.city+" ";
		add+=address.state+" ";
		add+=address.pincode;
		
		addRadioButton("add1", address.addressId, add);
	  });
 }


document.getElementById('paymentForm').addEventListener('submit', function(e) {
    e.preventDefault();  // Prevent form from submitting


    const userId = document.getElementById('userId').value;
    const paymentMethod = document.getElementById('paymentMethod').value;
    const finalAmount = parseFloat(document.getElementById('finalAmount').value);

    // Show loading status
    const paymentStatusDiv = document.getElementById('paymentStatus');
    paymentStatusDiv.style.display = 'block';
    paymentStatusDiv.textContent = 'Processing your payment...';
    paymentStatusDiv.classList.remove('success', 'error');

    // Simulate a delay for payment processing (setTimeout)
    setTimeout(() => {
      // Simulate a random success or failure for payment
      const isPaymentSuccessful = Math.random() > 0.5;  // 50% chance of success

      if (isPaymentSuccessful) {
        paymentStatusDiv.textContent = `Payment of $${finalAmount} from User ID ${userId} was successful using ${paymentMethod}.`;
        paymentStatusDiv.classList.add('success');
      } else {
        paymentStatusDiv.textContent = `Payment failed. Please try again.`;
        paymentStatusDiv.classList.add('error');
      }
    }, 2000);  // Simulate 2 seconds delay for processing
  });
  
  
  function handlePaymentMethodChange(value) {
     // Perform any action based on the selected value
     console.log("Selected Payment Method:", value);
     
     // Example action: Display a message based on the selection
     if (value === "Credit Card") {
       alert("You selected Credit Card. Please ensure you have your card details ready.");
     } else if (value === "Bank Transfer") {
       alert("You selected Bank Transfer. Ensure you have your bank details handy.");
     }
   }