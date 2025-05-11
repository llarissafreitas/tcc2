document.addEventListener("DOMContentLoaded", function () {
  // Criar controles de acessibilidade
  const accessibilityControls = document.createElement("div");
  accessibilityControls.className = "accessibility-controls";
accessibilityControls.innerHTML = `
  <button id="decrease-font" aria-label="Diminuir tamanho da fonte" title="Diminuir fonte">
    <i class="fas fa-minus"></i> <span class="sr-only">Diminuir fonte</span>
  </button>
  <button id="reset-font" aria-label="Tamanho padrão da fonte" title="Fonte normal">
    <i class="fas fa-text-height"></i> <span class="sr-only">Fonte normal</span>
  </button>
  <button id="increase-font" aria-label="Aumentar tamanho da fonte" title="Aumentar fonte">
    <i class="fas fa-plus"></i> <span class="sr-only">Aumentar fonte</span>
  </button>
  <button id="contrast-toggle" aria-label="Alternar contraste" title="Contraste">
    <i class="fas fa-adjust"></i> <span class="sr-only">Alternar contraste</span>
  </button>
`;


  // Posicionamento fixo no topo direito
  accessibilityControls.style.position = 'fixed';
  accessibilityControls.style.top = '10px';
  accessibilityControls.style.right = '10px';
  accessibilityControls.style.zIndex = '9999';
  accessibilityControls.style.display = 'flex';
  accessibilityControls.style.gap = '0.5rem';
  accessibilityControls.style.padding = '0.5rem';

  document.body.appendChild(accessibilityControls);

  const decreaseBtn = document.getElementById("decrease-font");
  const resetBtn = document.getElementById("reset-font");
  const increaseBtn = document.getElementById("increase-font");

  decreaseBtn?.addEventListener("click", () => changeFontSize(-2));
  resetBtn?.addEventListener("click", () => {
    document.documentElement.style.fontSize = "16px";
    localStorage.removeItem("fontSize");
  });
  increaseBtn?.addEventListener("click", () => changeFontSize(2));

  function changeFontSize(step) {
    const currentSize = parseFloat(getComputedStyle(document.documentElement).fontSize);
    const newSize = currentSize + step;
    if (newSize >= 12 && newSize <= 24) {
      document.documentElement.style.fontSize = `${newSize}px`;
      localStorage.setItem("fontSize", `${newSize}px`);
    }
  }

  const savedFontSize = localStorage.getItem("fontSize");
  if (savedFontSize) {
    document.documentElement.style.fontSize = savedFontSize;
  }

  // Contraste
  const contrastToggle = document.getElementById("contrast-toggle");
  if (localStorage.getItem("highContrast") === "true") {
    document.body.classList.add("high-contrast");
  }

  contrastToggle?.addEventListener("click", function (e) {
    e.preventDefault();
    document.body.classList.toggle("high-contrast");
    localStorage.setItem("highContrast", document.body.classList.contains("high-contrast"));
  });

  // Navegação por teclado
  document.addEventListener("keydown", function (e) {
    if (e.key === "Tab") {
      document.body.classList.add("keyboard-navigation");
    }
  });

  document.addEventListener("mousedown", function () {
    document.body.classList.remove("keyboard-navigation");
  });
});
