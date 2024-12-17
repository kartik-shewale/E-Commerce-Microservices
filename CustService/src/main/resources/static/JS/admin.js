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
				alert(data.message);
		           window.location.href = data.redirect || "/dashboard";
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


function showAddCategory() {
    document.getElementById("categoriesSection").style.display = "block";
    document.getElementById("stocksSection").style.display = "none";
    document.getElementById("ordersSection").style.display = "none";
    document.getElementById("addProductFormContainer").style.display = "none";
}

function showStocks() {
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

	
