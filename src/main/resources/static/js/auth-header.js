document.addEventListener("DOMContentLoaded", function () {
    const user = JSON.parse(localStorage.getItem("user"));
    const loginLink = document.getElementById("login-link");
    const cadastroLink = document.getElementById("cadastro-link");
    const userWelcome = document.getElementById("user-welcome");
    const logoutLink = document.getElementById("logout-link");
    const menuCadastrarVaga = document.getElementById("menu-cadastrar-vaga");
    const menuMinhasCandidaturas = document.getElementById("menu-minhas-candidaturas");
    const menuMinhasVagas = document.getElementById("menu-minhas-vagas");

    if (user && user.token) {
        if (loginLink) loginLink.style.display = "none";
        if (cadastroLink) cadastroLink.style.display = "none";
        if (logoutLink) logoutLink.style.display = "inline";
        if (userWelcome) {
            userWelcome.textContent = `Olá, ${user.email}`;
            userWelcome.style.display = "inline";
        }

        if (user.tipo === "EMPREGADOR") {
            if (menuCadastrarVaga) menuCadastrarVaga.style.display = "inline";
            if (menuMinhasVagas) menuMinhasVagas.style.display = "inline";

        }

        if (user.tipo === "CANDIDATO") {
            if (menuMinhasCandidaturas) menuMinhasCandidaturas.style.display = "inline";
        }
    } else {
        if (loginLink) loginLink.style.display = "inline";
        if (cadastroLink) cadastroLink.style.display = "inline";
        if (logoutLink) logoutLink.style.display = "none";
        if (userWelcome) userWelcome.style.display = "none";
        if (menuCadastrarVaga) menuCadastrarVaga.style.display = "none";
        if (menuMinhasCandidaturas) menuMinhasCandidaturas.style.display = "none";
        if (menuMinhasVagas) menuMinhasVagas.style.display = "none";
    }

    if (logoutLink) {
        logoutLink.addEventListener("click", () => {
            localStorage.removeItem("user");
            window.location.href = "/";
        });
    }
});
