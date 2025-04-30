document.addEventListener("DOMContentLoaded", function() {
    // Menu mobile
    const menuToggle = document.querySelector('.menu-toggle');
    const nav = document.querySelector('.nav');

    if (menuToggle && nav) {
        menuToggle.addEventListener('click', function() {
            const expanded = this.getAttribute('aria-expanded') === 'true';
            this.setAttribute('aria-expanded', !expanded);
            nav.classList.toggle('active');
        });
    }

    // Verificar preferência de contraste
    if (localStorage.getItem('highContrast') === 'true') {
        document.body.classList.add('high-contrast');
    }

    // Contraste toggle
    const contrastToggle = document.getElementById('contrast-toggle');
    if (contrastToggle) {
        contrastToggle.addEventListener('click', function(e) {
            e.preventDefault();
            document.body.classList.toggle('high-contrast');
            localStorage.setItem('highContrast', document.body.classList.contains('high-contrast'));
        });
    }

    // Carregar estatísticas na página inicial
    if (document.getElementById('candidatos-count')) {
        fetch('/api/estatisticas')
            .then(response => response.json())
            .then(data => {
                document.getElementById('candidatos-count').textContent = data.candidatos || '0';
                document.getElementById('vagas-count').textContent = data.vagas || '0';
                document.getElementById('contratacoes-count').textContent = data.contratacoes || '0';
            })
            .catch(error => {
                console.error('Erro ao carregar estatísticas:', error);
            });
    }
});

// Suporte a navegação por teclado
document.addEventListener('keydown', function(e) {
    if (e.key === 'Tab') {
        document.body.classList.add('keyboard-navigation');
    }
});

document.addEventListener('mousedown', function() {
    document.body.classList.remove('keyboard-navigation');
});