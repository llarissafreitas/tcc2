document.addEventListener("DOMContentLoaded", function() {
    const vagaLista = document.getElementById("vaga-lista");

    // Simulação de vagas - Em um projeto real, você faria uma requisição AJAX aqui
    const vagas = [
        { titulo: "Desenvolvedor de Software", local: "São Paulo", descricao: "Vaga para desenvolvedor Júnior." },
        { titulo: "Analista de Suporte", local: "Rio de Janeiro", descricao: "Vaga para analista de TI." },
        { titulo: "Analista de FinOps", local: "Rio de Janeiro", descricao: "Vaga para analista de TI." }

    ];

    vagas.forEach(vaga => {
        const vagaElement = document.createElement("div");
        vagaElement.classList.add("vaga");
        vagaElement.innerHTML = `
            <h3>${vaga.titulo}</h3>
            <p>Local: ${vaga.local}</p>
            <p>${vaga.descricao}</p>
        `;
        vagaLista.appendChild(vagaElement);
    });
});
