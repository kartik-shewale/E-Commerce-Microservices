document.addEventListener("DOMContentLoaded", function() {
	
	const params = new URLSearchParams(window.location.search);
	const id = params.get('userId');
	const price = params.get('total');
	document.getElementById('finalAmount').value = price;
	loadCustomerDetails(id)
	
});

function loadCustomerDetails(id){
	fetch('/MyShop/customer/'+id)
	    .then(response => response.json())
	    .then(data => {
			
			const userImage = document.getElementById("userImage");
			const username = document.getElementById("userName");
			userImage.src = data.userImage;
			username.textContent = data.userName;
			
			document.getElementById('userId').value = data.custId;
			document.getElementById('userId2').value = data.custId;
			document.getElementById('UserName').value = data.userName;
			document.getElementById('fName').value = data.fName;
			document.getElementById('lName').value = data.lName;
			document.getElementById('mobile').value = data.mobile;
			document.getElementById('email').value = data.email;
			
			createAddress(data.address);
	    })
	    .catch(error => {
	      console.error('Error fetching User Data:', error);
        });
}

document.getElementById('addAddress').addEventListener('click', function(e) {
    e.preventDefault();
	const addNewAddressBtn = document.getElementById("addNewAddressBtn");
	addNewAddressBtn.textContent = "Add"
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
	addressFieldEmpty();
});

document.getElementById('editCustDetails').addEventListener('click', async () => {
	   
	  const userId = document.getElementById('userId').value;
	  const userName = document.getElementById('UserName').value;
	  const fName = document.getElementById('fName').value;
	  const lName = document.getElementById('lName').value;
	  const mobile = document.getElementById('mobile').value;
	  const email = document.getElementById('email').value;
	
	  if (!userId || !userName || !fName || !lName || !mobile || !email) {
		  showMessageBox("Please fill all the mandatory fields.","ok")
	      return false;
	  }
	   const userDetails = {
	       custId: userId,
	       userName: userName,
	       fName: fName,
	       lName: lName,
	       mobile: mobile,
	       email: email
	   };

	   try {
          const response = await fetch("/MyShop/signup", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(userDetails),
          });

          if (response.ok) {
			const data = await response.json();
			showMessageBox(data.message,"ok")
          } 
        } catch (error) {
			showMessageBox(error,"ok")
        }	
});

document.getElementById('addNewAddressBtn').addEventListener('click', async () => {
	
	   const selectedRadio = document.querySelector('input[name="add1"]:checked');	   
	   const userId = document.getElementById('userId').value;
	   const firstLine = document.getElementById('fLine').value;
	   const secondLine = document.getElementById('sLine').value;
	   const city = document.getElementById('city').value;
	   const state = document.getElementById('state').value;
	   const pincode = document.getElementById('pincode').value;
	
	   if (!userId || !firstLine || !secondLine || !city || !state || !pincode) {
		   showMessageBox("Please fill all the mandatory fields.","ok")
	       return false;
	   }
	   const address = {
	       addressId: selectedRadio ? selectedRadio.value : "",
	       custId: userId,
	       firstLine: firstLine,
	       secondLine: secondLine,
	       city: city,
	       state: state,
	       pincode: pincode
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
		   addressFieldEmpty();				
         } else {
         }
       } catch (error) {
         console.error("Error during adding:", error);
       }
	

	   const container = document.getElementById("radioContainer");
	   container.innerHTML = "";
	   var id = document.getElementById('userId').value;
	   loadCustomerDetails(id);
	
		document.getElementById('custForm').classList.remove("hidden");
		document.getElementById('addressForm').classList.remove("hidden");
		document.getElementById('makePaymentForm').classList.remove("hidden");
		document.getElementById('newAddressForm').classList.add("hidden");
	
});

document.getElementById('editAddress').addEventListener('click', async () => {
	
	const addNewAddressBtn = document.getElementById("addNewAddressBtn");
	addNewAddressBtn.textContent = "Update"
	
	const selectedRadio = document.querySelector('input[name="add1"]:checked');
	 if(!selectedRadio)
		{
			showMessageBox("Please select the address","ok");
			return;
		}
	var id = document.querySelector('input[name="add1"]:checked').value;
	fetch('/MyShop/address/'+id)
	    .then(response => response.json())
	    .then(data => {
			document.getElementById('userId').value = data.address.custId;			
			document.getElementById('fLine').value = data.address.firstLine;
			document.getElementById('sLine').value = data.address.secondLine;
			document.getElementById('city').value = data.address.city;
			document.getElementById('state').value = data.address.state;
			document.getElementById('pincode').value = data.address.pincode;
	    })
	    .catch(error => {
	      console.error('Error fetching User Data:', error);
      });
	  document.getElementById('custForm').classList.add("hidden");
	  document.getElementById('addressForm').classList.add("hidden");
	  document.getElementById('makePaymentForm').classList.add("hidden");
	  document.getElementById('newAddressForm').classList.remove("hidden");
	
});

document.getElementById('deleteAddress').addEventListener('click', async () => {
	
	const selectedRadio = document.querySelector('input[name="add1"]:checked');
	 if(!selectedRadio)
		{
			showMessageBox("Please select the address","ok");
			return;
		}
	   var id = document.querySelector('input[name="add1"]:checked').value;
	   const url = `/MyShop/address/${id}`; 
		fetch(url, {
		    method: 'DELETE',
		    headers: { 'Content-Type': 'application/json' }
		})
		.then(response => response.json())
		.then(data => {
	    	console.log(data.message); 
			showMessageBox(data.message,"ok")
			const container = document.getElementById("radioContainer");
			container.innerHTML = "";
			var id = document.getElementById('userId').value;
			loadCustomerDetails(id);
		})
		.catch(() => {
		    console.error('Error:', data.message);
		});
});

function addRadioButton(name, value, label) {
    const container = document.getElementById("radioContainer");
    const radioButton = document.createElement("input");
    radioButton.type = "radio";
    radioButton.name = name;
    radioButton.value = value;
    radioButton.id = value; 
    const radioLabel = document.createElement("label");
    radioLabel.textContent = label;
    radioLabel.setAttribute("for", value);
    const radioWrapper = document.createElement("div");
    radioWrapper.style.display = "flex"; 
    radioWrapper.style.alignItems = "center"; 
    radioWrapper.style.justifyContent = "space-between";
    radioWrapper.style.marginBottom = "10px"; 
    radioButton.style.marginRight = "10px";
    radioWrapper.appendChild(radioButton);
    radioWrapper.appendChild(radioLabel);
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


document.getElementById('makePayment').addEventListener('click', async () => {

    const userId = document.getElementById('userId').value;
    const paymentMethod = document.getElementById('paymentMethod').value;
	const cardNumber = document.getElementById('cardNumber').value;
	const cardPin = document.getElementById('cardPin').value;
	const upiId = document.getElementById('upiId').value;
	const waletPin = document.getElementById('waletPin').value;
    const finalAmount = parseFloat(document.getElementById('finalAmount').value);
	
	
	const selectedRadio = document.querySelector('input[name="add1"]:checked');
		 if(!selectedRadio)
			{
				showMessageBox("Please select the address","ok");
				return;
			}
	var addresId = document.querySelector('input[name="add1"]:checked').value;


	if (paymentMethod === "Credit Card") {
	    if (!cardNumber || !cardPin) {
			
			showMessageBox("Please enter card number and pin.","ok")
	        return false;
	    }
	}
	if (paymentMethod === "UPI") {
	    if (!upiId) {
			showMessageBox("Please enter UPI ID.","ok")
	        return false;
	    }
	}
	if (paymentMethod === "Wallet") {
	    if (!waletPin) {
			showMessageBox("Please enter wallet pin.","ok")
	        return false;
	    }
	}
	
	const paymentDetails = {
	    userId: userId,
	    paymentMethod: paymentMethod,
		addressId:addresId,
	    finalAmount: finalAmount
	};
	
    //const paymentStatusDiv = document.getElementById('paymentStatus');
    //paymentStatusDiv.style.display = 'block';
    //paymentStatusDiv.textContent = 'Processing your payment...';
    //paymentStatusDiv.classList.remove('success', 'error');

	try {
         const response =await fetch('/MyShop/payorder', {
           method: "POST",
           headers: { "Content-Type": "application/json" },
           body: JSON.stringify(paymentDetails),
         });
   
         if (response.ok) {
           const data =await response.json();
		   console.log(data);				
         }
       } catch (error) {
          console.error("Error during adding:", error);
       }
   
	 /*   setTimeout(() => {
      const isPaymentSuccessful = Math.random() > 0.5;

      if (isPaymentSuccessful) {
        paymentStatusDiv.textContent = `Payment of $${finalAmount} from User ID ${userId} was successful using ${paymentMethod}.`;
        paymentStatusDiv.classList.add('success');
      } else {
        paymentStatusDiv.textContent = `Payment failed. Please try again.`;
        paymentStatusDiv.classList.add('error');
      }
    }, 2000); 
	*/
  });
  
function addressFieldEmpty(){
	document.getElementById('userId').value = "";			
	document.getElementById('fLine').value = "";
	document.getElementById('sLine').value = "";
	document.getElementById('city').value = "";
	document.getElementById('state').value = "";
	document.getElementById('pincode').value = "";
}
  
function handlePaymentMethodChange() {
	
	const value = document.getElementById("paymentMethod").value;
	
	 const cardNumber = document.getElementById('cardNumber');
	 const cardPin = document.getElementById('cardPin');
	 const upiId = document.getElementById('upiId');
	 const waletPin = document.getElementById('waletPin');
	 
	 const cardNumberDiv = document.getElementById('cardNumberDiv');
	 const cardPinDiv = document.getElementById('cardPinDiv');
	 const upiIdDiv = document.getElementById('upiIdDiv');
	 const waletPinDiv = document.getElementById('waletPinDiv');
	 
	 console.log("Selected Payment Method:", value);
	 
	 if (value === "Credit Card") {
		
	     cardNumber.setAttribute("required", "required");
		 cardPin.setAttribute("required", "required");
		 upiId.removeAttribute("required");
		 waletPin.removeAttribute("required"); 
		 
		 cardNumberDiv.classList.remove("hidden");
		 cardPinDiv.classList.remove("hidden");
		 upiIdDiv.classList.add("hidden");
		 waletPinDiv.classList.add("hidden");
		 
	 } else if (value === "Wallet") {
		
		waletPin.setAttribute("required", "required");
		upiId.removeAttribute("required");
		cardPin.removeAttribute("required"); 
		cardNumber.removeAttribute("required");
	
		cardNumberDiv.classList.add("hidden");
		cardPinDiv.classList.add("hidden");
		upiIdDiv.classList.add("hidden");
		waletPinDiv.classList.remove("hidden");
		 
	 }else{
		
		upiId.setAttribute("required", "required");
		waletPin.removeAttribute("required");
		cardPin.removeAttribute("required"); 
		cardNumber.removeAttribute("required");
		
		cardNumberDiv.classList.add("hidden");
		cardPinDiv.classList.add("hidden");
		upiIdDiv.classList.remove("hidden");
		waletPinDiv.classList.add("hidden");
	 }
 }