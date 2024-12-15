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
