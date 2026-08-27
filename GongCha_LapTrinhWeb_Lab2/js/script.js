document.addEventListener("DOMContentLoaded", function () {
  const options = document.getElementById("quickOptions");
  const totalElement = document.getElementById("quickTotal");
  const addToCartButton = document.getElementById("addToCart");
  const cartMessage = document.getElementById("cartMessage");
  const productName = document.querySelector(".menu-copy h3");
  const cartToggle = document.getElementById("cartToggle");
  const miniCart = document.getElementById("miniCart");
  const cartClose = document.getElementById("cartClose");
  const cartCount = document.getElementById("cartCount");
  const cartEmpty = document.getElementById("cartEmpty");
  const cartItems = document.getElementById("cartItems");
  const cartSummary = document.getElementById("cartSummary");
  const cartTotal = document.getElementById("cartTotal");

  if (
    !options ||
    !totalElement ||
    !addToCartButton ||
    !cartMessage ||
    !productName ||
    !cartToggle ||
    !miniCart
  )
    return;

  const cart = [];

  function formatPrice(price) {
    return new Intl.NumberFormat("vi-VN").format(price) + "đ";
  }

  function updateTotal() {
    const selectedSize = options.querySelector(
      'input[name="quick-size"]:checked',
    );
    const selectedToppings = options.querySelectorAll(
      'input[name="quick-topping"]:checked',
    );
    let total = Number(selectedSize.dataset.price);

    selectedToppings.forEach(function (topping) {
      total += Number(topping.dataset.price);
    });

    totalElement.textContent = formatPrice(total);
    cartMessage.textContent = "";
  }

  options.addEventListener("change", updateTotal);

  function renderCart() {
    const itemCount = cart.length;
    cartCount.textContent = itemCount;
    cartCount.hidden = itemCount === 0;
    cartEmpty.hidden = itemCount > 0;
    cartSummary.hidden = itemCount === 0;
    cartItems.innerHTML = "";

    let total = 0;
    cart.forEach(function (item) {
      total += item.price;
      const itemElement = document.createElement("article");
      itemElement.className = "cart-item";
      itemElement.innerHTML =
        "<h3>" +
        item.name +
        "</h3>" +
        "<p>Size: <strong>" +
        item.size +
        "</strong></p>" +
        "<p>Toppings: <strong>" +
        item.toppings +
        "</strong></p>" +
        '<strong class="cart-item-price">' +
        formatPrice(item.price) +
        "</strong>";
      cartItems.appendChild(itemElement);
    });

    cartTotal.textContent = formatPrice(total);
  }

  function setCartVisibility(isOpen) {
    miniCart.hidden = !isOpen;
    cartToggle.setAttribute("aria-expanded", String(isOpen));
  }

  cartToggle.addEventListener("click", function () {
    setCartVisibility(miniCart.hidden);
  });

  cartClose.addEventListener("click", function () {
    setCartVisibility(false);
  });

  addToCartButton.addEventListener("click", function () {
    const selectedSize = options.querySelector(
      'input[name="quick-size"]:checked',
    );
    const selectedToppings = Array.from(
      options.querySelectorAll('input[name="quick-topping"]:checked'),
    );
    const toppingText = selectedToppings.length
      ? selectedToppings
          .map(function (topping) {
            return topping.value;
          })
          .join(", ")
      : "No topping";
    const price =
      Number(selectedSize.dataset.price) +
      selectedToppings.reduce(function (sum, topping) {
        return sum + Number(topping.dataset.price);
      }, 0);

    cart.push({
      name: productName.textContent.trim(),
      size: selectedSize.value,
      toppings: toppingText,
      price: price,
    });
    renderCart();

    cartMessage.textContent =
      "Đã thêm size " +
      selectedSize.value +
      " • " +
      toppingText +
      " • " +
      totalElement.textContent;
  });

  updateTotal();
  renderCart();
});
