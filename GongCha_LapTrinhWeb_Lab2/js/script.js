document.addEventListener("DOMContentLoaded", function () {
  const options = document.getElementById("quickOptions");
  const totalElement = document.getElementById("quickTotal");
  const addToCartButton = document.getElementById("addToCart");
  const cartMessage = document.getElementById("cartMessage");

  if (!options || !totalElement || !addToCartButton || !cartMessage) return;

  function formatPrice(price) {
    return new Intl.NumberFormat("vi-VN").format(price) + "đ";
  }

  function updateTotal() {
    const selectedSize = options.querySelector('input[name="quick-size"]:checked');
    const selectedToppings = options.querySelectorAll('input[name="quick-topping"]:checked');
    let total = Number(selectedSize.dataset.price);

    selectedToppings.forEach(function (topping) {
      total += Number(topping.dataset.price);
    });

    totalElement.textContent = formatPrice(total);
    cartMessage.textContent = "";
  }

  options.addEventListener("change", updateTotal);

  addToCartButton.addEventListener("click", function () {
    const selectedSize = options.querySelector('input[name="quick-size"]:checked');
    const selectedToppings = Array.from(
      options.querySelectorAll('input[name="quick-topping"]:checked')
    );
    const toppingText = selectedToppings.length
      ? selectedToppings.map(function (topping) {
          return topping.value;
        }).join(", ")
      : "No topping";

    cartMessage.textContent =
      "Đã thêm size " + selectedSize.value + " • " + toppingText +
      " • " + totalElement.textContent;
  });

  updateTotal();
});
