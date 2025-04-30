document.addEventListener("DOMContentLoaded", function() {
    // Criar controles de acessibilidade
    const accessibilityControls = document.createElement('div');
    accessibilityControls.className = 'accessibility-controls';
    accessibilityControls.innerHTML = `
        <button id="decrease-font" aria-label="Diminuir tamanho da fonte" title="Diminuir fonte">
            <i class="fas fa-font"></i> <span class="sr-only">Diminuir fonte</span>
        </button>
        <button id="reset-font" aria-label="Tamanho padrão da fonte" title="Fonte normal">
            <i class="fas fa-text-height"></i> <span class="sr-only">Fonte normal</span>
        </button>
        <button id="increase-font" aria-label="Aumentar tamanho da fonte" title="Aumentar fonte">
            <i class="fas fa-font"></i> <span class="sr-only">Aumentar fonte</span>
        </button>
    `;

    document.body.insertBefore(accessibilityControls, document.body.firstChild);

    // Controles de tamanho da fonte
    const decreaseBtn = document.getElementById('decrease-font');
    const resetBtn = document.getElementById('reset-font');
    const increaseBtn = document.getElementById('increase-font');

    decreaseBtn.addEventListener('click', function() {
        changeFontSize(-2);
    });

    resetBtn.addEventListener('click', function() {
        document.documentElement.style.fontSize = '16px';
        localStorage.removeItem('fontSize');
    });

    increaseBtn.addEventListener('click', function() {
        changeFontSize(2);
    });

    function changeFontSize(step) {
        const currentSize = parseFloat(getComputedStyle(document.documentElement).fontSize);
        const newSize = currentSize + step;

        if (newSize >= 12 && newSize <= 24) {
            document.documentElement.style.fontSize = `${newSize}px`;
            localStorage.setItem('fontSize', `${newSize}px`);
        }
    }

    // Aplicar tamanho de fonte salvo
    const savedFontSize = localStorage.getItem('fontSize');
    if (savedFontSize) {
        document.documentElement.style.fontSize = savedFontSize;
    }
});