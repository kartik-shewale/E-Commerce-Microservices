
document.addEventListener("DOMContentLoaded", () => {
    const showLoginButton = document.getElementById("showLogin");
    const showSignupButton = document.getElementById("showSignup");
    const loginFormContainer = document.getElementById("loginFormContainer");
    const signupFormContainer = document.getElementById("signupFormContainer");
    const loginButton = document.getElementById("loginButton");
    const signupButton = document.getElementById("signupButton");
    const loginErrorMessage = document.getElementById("loginErrorMessage");
    const signupErrorMessage = document.getElementById("signupErrorMessage");
	
	const adminLoginButton = document.getElementById("adminLoginButton");
	const adminLoginFormContainer = document.getElementById("adminLoginFormContainer");
	const adminErrorMessage = document.getElementById("adminErrorMessage");
	const adminButton = document.getElementById("adminButton");
	
	const forgotPasswordLabel = document.getElementById("forgotPasswordLabel");
	const adminForgotPasswordLabel = document.getElementById("adminForgotPasswordLabel");
	const forgotPasswordContainer = document.getElementById("forgotPasswordContainer");
	const forgotPasswordButton = document.getElementById("forgot");
	const forgotPassowrdErrorMessage = document.getElementById("forgotPassowrdErrorMessage");
	
	const email = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	const mobile = /^[7-9][0-9]{9}$/;

	
	
    // Toggle Forms
    showLoginButton.addEventListener("click", () => {
      loginFormContainer.classList.remove("hidden");
      signupFormContainer.classList.add("hidden");
	  adminLoginFormContainer.classList.add("hidden");
	  forgotPasswordContainer.classList.add("hidden");
	  document.getElementById("loginUsername").value = '';
	  document.getElementById("loginPassword").value = '';
      showLoginButton.classList.add("active");
      showSignupButton.classList.remove("active");
    });
  
    showSignupButton.addEventListener("click", () => {
      signupFormContainer.classList.remove("hidden");
      loginFormContainer.classList.add("hidden");
	  adminLoginFormContainer.classList.add("hidden");
	  forgotPasswordContainer.classList.add("hidden");
	  document.getElementById("signupUsername").value = '';
	  document.getElementById("signupPassword").value = '';
      showSignupButton.classList.add("active");
      showLoginButton.classList.remove("active");
    });

	adminLoginButton.addEventListener("click", function() {
	  adminLoginFormContainer.classList.remove("hidden");
	  const loginFormContainer = document.getElementById("loginFormContainer");
	  const signupFormContainer = document.getElementById("signupFormContainer");
	  document.getElementById("adminUsername").value = "";
	  document.getElementById("adminPassword").value = "";
	  loginFormContainer.classList.add("hidden");
	  signupFormContainer.classList.add("hidden");
	  showSignupButton.classList.remove("active");
	  forgotPasswordContainer.classList.add("hidden");
	  showLoginButton.classList.remove("active");
  });
  

	  forgotPasswordLabel.addEventListener("click", function(e) {
	    e.preventDefault();
	    forgotPasswordButton.textContent = "Reset User Password";
	    forgotPasswordContainer.classList.remove("hidden");
	    signupFormContainer.classList.add("hidden");
		document.getElementById("forgotMailOrMobile").value = "";
		document.getElementById("newPassword").value ="";
	    loginFormContainer.classList.add("hidden");
	    adminLoginFormContainer.classList.add("hidden");
	  });

	  adminForgotPasswordLabel.addEventListener("click", function(e) {
	    e.preventDefault();
	    forgotPasswordButton.textContent = "Reset Admin Password";
	    forgotPasswordContainer.classList.remove("hidden");
		
		document.getElementById("forgotMailOrMobile").value = "";
		document.getElementById("newPassword").value ="";
		forgotPassowrdErrorMessage.textContent = "";
		loginErrorMessage.textContent = "";
		signupErrorMessage.textContent = "";
		
	    signupFormContainer.classList.add("hidden");
	    loginFormContainer.classList.add("hidden");
	    adminLoginFormContainer.classList.add("hidden");
	  });
  
  
  	// Forgot Password Login
      forgotPasswordButton.addEventListener("click", async () => {
		const username = document.getElementById("forgotMailOrMobile").value.trim();
		const password = document.getElementById("newPassword").value.trim();
		forgotPassowrdErrorMessage.textContent = "";
		
        if (!username || !password) {
          forgotPassowrdErrorMessage.textContent = "Username and password are required.";
          return;
        }
		if (!email.test(username) && !mobile.test(username)) {
          forgotPassowrdErrorMessage.textContent = "Enter Valid Mobile or Email.";
          return;
        }
				
		var url = "";
		if(forgotPasswordButton.textContent == "Reset Admin Password")
			{
				url = 	"/admin/forgotAdmin";		
			}else{
				url = 	"/MyShop/forgotUser";	
			}
    
        try {
          const response = await fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, password }),
          });
    
          if (response.ok) {
            const data = await response.json();
			alert(data.message);
            window.location.href = data.redirect || "/dashboard";
          } else {
            const errorData = await response.json();
			//alert(errorData.message);
            forgotPassowrdErrorMessage.textContent = errorData.message || "Login failed.";
          }
        } catch (error) {
          console.error("Error during login:", error);
          forgotPassowrdErrorMessage.textContent = "An unexpected error occurred.";
        }
      });

  
    // Handle Login
    loginButton.addEventListener("click", async () => {
      const username = document.getElementById("loginUsername").value.trim();
      const password = document.getElementById("loginPassword").value.trim();
      loginErrorMessage.textContent = "";
  
      if (!username || !password) {
        loginErrorMessage.textContent = "Username and password are required.";
        return;
      }
  
      try {
        const response = await fetch("/MyShop/login", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ username, password }),
        });
  
        if (response.ok) {
          const data = await response.json();
		  const queryString = encodeURIComponent(data.customers.custId);
          window.location.href = `${data.redirect || "/dashboard"}?data=${queryString}`;
        } else {
          const errorData = await response.json();
          loginErrorMessage.textContent = errorData.message || "Login failed.";
        }
      } catch (error) {
        console.error("Error during login:", error);
        loginErrorMessage.textContent = "An unexpected error occurred.";
      }
    });
	
		// Handle admin login Login
    adminButton.addEventListener("click", async () => {
      const username = document.getElementById("adminUsername").value.trim();
      const password = document.getElementById("adminPassword").value.trim();
      adminErrorMessage.textContent = "";
  
      if (!username || !password) {
        adminErrorMessage.textContent = "Username and password are required.";
        return;
      }
  
      try {
        const response = await fetch("/admin/login", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ username, password }),
        });
  
        if (response.ok) {
          const data = await response.json();
          window.location.href = data.redirect || "/dashboard";
        } else {
          const errorData = await response.json();
          adminErrorMessage.textContent = errorData.message || "Login failed.";
        }
      } catch (error) {
        console.error("Error during login:", error);
        adminErrorMessage.textContent = "An unexpected error occurred.";
      }
    });
  
    // Handle Sign-Up
    signupButton.addEventListener("click", async () => {
      const fName = document.getElementById("signupFName").value.trim();
      const lName = document.getElementById("signupLName").value.trim();
      const email = document.getElementById("signupEmail").value.trim();
      const mobile = document.getElementById("signupMobile").value.trim();
      const userName = document.getElementById("signupUsername").value.trim();
      const password = document.getElementById("signupPassword").value.trim();
      const imageInput = document.getElementById("signupImage").files[0];
      signupErrorMessage.textContent = "";
  
      if (!fName || !lName || !email || !mobile || !userName || !password) {
        signupErrorMessage.textContent = "All fields are required.";
        return;
      }
  
      let base64Image = null;
      if (imageInput) {
        const reader = new FileReader();
        reader.onload = async function (e) {
          base64Image = e.target.result;
  
          try {
            const response = await fetch("/MyShop/signup", {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify({
                fName,
                lName,
                email,
                mobile,
                userName,
                password,
                userImage: base64Image,
              }),
            });
  
            if (response.ok) {
				const data = await response.json();
				const queryString = encodeURIComponent(data.customers.custId);
				window.location.href = `${data.redirect || "/dashboard"}?data=${queryString}`;
              alert("Sign-Up successful! Please log in.");
             // showLoginButton.click(); // Switch to login form
            } else {
              const errorData = await response.json();
              signupErrorMessage.textContent = errorData.message || "Sign-Up failed.";
            }
          } catch (error) {
            console.error("Error during sign-up:", error);
            signupErrorMessage.textContent = "An unexpected error occurred.";
          }
        };
        reader.readAsDataURL(imageInput); // Convert image to Base64
      }
    });
  });
  