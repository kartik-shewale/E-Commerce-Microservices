// Simulate payment processing
document.getElementById('paymentForm').addEventListener('submit', function(e) {
    e.preventDefault();  // Prevent form from submitting
  
    // Get values from the form
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
  