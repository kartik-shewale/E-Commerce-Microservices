
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
    // Toggle Forms
    showLoginButton.addEventListener("click", () => {
      loginFormContainer.classList.remove("hidden");
      signupFormContainer.classList.add("hidden");
	  adminLoginFormContainer.classList.add("hidden");
	  document.getElementById("loginUsername").value = '';
	  document.getElementById("loginPassword").value = '';
      showLoginButton.classList.add("active");
      showSignupButton.classList.remove("active");
    });
  
    showSignupButton.addEventListener("click", () => {
		
      signupFormContainer.classList.remove("hidden");
      loginFormContainer.classList.add("hidden");
	  adminLoginFormContainer.classList.add("hidden");
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
	  showSignupButton.classList.remove("active");
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
          window.location.href = data.redirect || "/dashboard";
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
	        const response = await fetch("/MyShop/admin", {
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
				window.location.href = data.redirect || "/dashboard";
              alert("Sign-Up successful! Please log in.");
              showLoginButton.click(); // Switch to login form
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
  


/*
document.addEventListener("DOMContentLoaded", () => {
  const loginForm = document.getElementById("loginForm");
  const loginButton = document.getElementById("loginButton");
  const errorMessage = document.getElementById("errorMessage");

  // Handle form submission
  loginButton.addEventListener("click", async (e) => {
    e.preventDefault();

    // Gather form data
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    if (!username || !password) {
      errorMessage.textContent = "Username and password are required.";
      return;
    }

    try {
      // Make an HTTP POST request to the server
      const response = await fetch("/MyShop/login", {
        method: "POST", // Use POST for submitting login data
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }), // Send username and password as JSON
      });

      if (response.ok) {
        // Handle successful login
        const data = await response.json();
        window.location.href = data.redirect || "/dashboard"; // Redirect to dashboard or provided URL
      } else {
        // Handle login failure
        const errorData = await response.json();
        errorMessage.textContent = errorData.message || "Login failed. Please try again.";
      }
    } catch (error) {
      console.error("Error during login:", error);
      errorMessage.textContent = "An unexpected error occurred.";
    }
  });
});
*/
