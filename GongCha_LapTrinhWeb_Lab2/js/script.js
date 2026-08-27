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
  const menuTabs = document.querySelectorAll(".menu-tabs [data-series]");
  const productImage = document.getElementById("menuProductImage");
  const productDescription = document.getElementById("menuProductDescription");
  const sizeMPrice = document.getElementById("sizeMPrice");
  const sizeLPrice = document.getElementById("sizeLPrice");
  const sizeMInput = options.querySelector('input[name="quick-size"][value="M"]');
  const sizeLInput = options.querySelector('input[name="quick-size"][value="L"]');
  const toppingInputs = options.querySelectorAll('input[name="quick-topping"]');
  const productLinks = document.querySelectorAll("[data-series-target]");
  const seriesPanel = document.getElementById("seriesPanel");

  const seriesData = {
    signature: {
      name: "Gong Cha Signature Drink",
      description: "Trà nguyên bản kết hợp lớp milk foam mịn, béo nhẹ và đậm hương trà đặc trưng.",
      image: "img/tea-cup.png",
      sizeM: 45000,
      sizeL: 55000,
    },
    brewed: {
      name: "Pure Oolong Brewed Tea",
      description: "Trà Oolong ủ tươi mỗi ngày với hương thơm thanh khiết và hậu vị dịu nhẹ.",
      image: "img/hero-bi-dao.jpg",
      sizeM: 35000,
      sizeL: 45000,
    },
    milk: {
      name: "Trà Thái Oolong Sữa",
      description: "Hương trà Oolong thơm thanh hòa quyện cùng vị sữa ngọt dịu, mượt mà.",
      image: "img/hero-thai.jpg",
      sizeM: 45000,
      sizeL: 55000,
    },
    smoothie: {
      name: "Dark Cocoa Smoothie",
      description: "Đá xay cacao mát lạnh, đậm vị và mịn màng cho những ngày cần một món ngọt.",
      image: "img/hero-cocoa.jpg",
      sizeM: 48000,
      sizeL: 58000,
    },
    creative: {
      name: "Trà Sữa Bí Đao",
      description: "Vị bí đao thanh mát kết hợp cùng sữa thơm nhẹ, phù hợp cho ngày hè.",
      image: "img/hero-bi-dao.jpg",
      sizeM: 42000,
      sizeL: 52000,
    },
  };

  if (
    !options ||
    !totalElement ||
    !addToCartButton ||
    !cartMessage ||
    !productName ||
    !cartToggle ||
    !miniCart ||
    !cartClose ||
    !cartCount ||
    !cartEmpty ||
    !cartItems ||
    !cartSummary ||
    !cartTotal ||
    !productImage ||
    !productDescription ||
    !sizeMPrice ||
    !sizeLPrice ||
    !sizeMInput ||
    !sizeLInput ||
    menuTabs.length === 0
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

  menuTabs.forEach(function (tab) {
    tab.addEventListener("click", function (event) {
      event.preventDefault();
      const seriesKey = tab.dataset.series;
      const selectedSeries = seriesData[seriesKey];
      if (!selectedSeries) return;

      menuTabs.forEach(function (menuTab) {
        const isActive = menuTab === tab;
        menuTab.classList.toggle("active", isActive);
        menuTab.setAttribute("aria-selected", String(isActive));
      });

      productName.textContent = selectedSeries.name;
      productDescription.textContent = selectedSeries.description;
      productImage.src = selectedSeries.image;
      productImage.alt = selectedSeries.name;
      sizeMInput.dataset.price = String(selectedSeries.sizeM);
      sizeLInput.dataset.price = String(selectedSeries.sizeL);
      sizeMPrice.textContent = formatPrice(selectedSeries.sizeM);
      sizeLPrice.textContent = formatPrice(selectedSeries.sizeL);
      sizeMInput.checked = true;
      toppingInputs.forEach(function (topping) {
        topping.checked = false;
      });
      window.history.replaceState(null, "", "#" + seriesKey);
      updateTotal();
    });
  });

  productLinks.forEach(function (productLink) {
    productLink.addEventListener("click", function (event) {
      event.preventDefault();
      const targetSeries = productLink.dataset.seriesTarget;
      const targetTab = Array.from(menuTabs).find(function (tab) {
        return tab.dataset.series === targetSeries;
      });

      if (!targetTab || !seriesPanel) return;
      targetTab.click();
      seriesPanel.scrollIntoView({ behavior: "smooth", block: "start" });
    });
  });

  function renderCart() {
    const itemCount = cart.length;
    cartCount.textContent = itemCount;
    cartCount.hidden = itemCount === 0;
    cartEmpty.hidden = itemCount > 0;
    cartSummary.hidden = itemCount === 0;
    cartItems.innerHTML = "";

    let total = 0;
    cart.forEach(function (item, itemIndex) {
      total += item.price;
      const itemElement = document.createElement("article");
      const removeButton = document.createElement("button");
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
      removeButton.className = "cart-item-remove";
      removeButton.type = "button";
      removeButton.dataset.itemIndex = String(itemIndex);
      removeButton.textContent = "×";
      removeButton.setAttribute(
        "aria-label",
        "Xóa " + item.name + " khỏi giỏ hàng",
      );
      itemElement.appendChild(removeButton);
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

  cartItems.addEventListener("click", function (event) {
    const removeButton = event.target.closest(".cart-item-remove");
    if (!removeButton) return;

    const itemIndex = Number(removeButton.dataset.itemIndex);
    cart.splice(itemIndex, 1);
    renderCart();
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

  const initialSeries = window.location.hash.replace("#", "");
  const initialTab = Array.from(menuTabs).find(function (tab) {
    return tab.dataset.series === initialSeries;
  });

  if (initialTab) {
    initialTab.click();
  } else {
    updateTotal();
  }
  renderCart();
});
