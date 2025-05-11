document.addEventListener("DOMContentLoaded", async () => {
  const user = JSON.parse(localStorage.getItem("user"));
  const token = user?.token;
  const container = document.getElementById("vagas-container");

  if (!token || user?.tipo !== "EMPREGADOR") {
    container.innerHTML = "<p>Acesso restrito. Faça login como empregador.</p>";
    return;
  }

  try {
    const res = await fetch("http://localhost:8081/empregadores/minhas-vagas-com-candidatos", {
      headers: {
        "Authorization": `Bearer ${token}`
      }
    });

    if (!res.ok) throw new Error("Erro ao buscar vagas");

    const data = await res.json();

    if (data.length === 0) {
      container.innerHTML = "<p>Você ainda não cadastrou nenhuma vaga.</p>";
      return;
    }

    data.forEach(vaga => {
      const div = document.createElement("div");
      div.className = "vaga-card";

      let candidatosHTML = "<p style='margin-left: 1rem;'>Nenhum candidato ainda.</p>";

      if (vaga.candidatos.length > 0) {
        candidatosHTML = vaga.candidatos.map(c => `
          <div class="candidato-card" style="margin-left: 1rem; margin-bottom: 1rem; border-left: 2px solid #ccc; padding-left: 1rem;">
            <p><strong>Nome:</strong> ${c.nome}</p>
            <p><strong>Email:</strong> ${c.email}</p>
            <p><strong>Telefone:</strong> ${c.telefone}</p>
            <p><strong>Habilidades:</strong> ${c.habilidades}</p>
            <p><strong>Experiência:</strong> ${c.experiencia}</p>
            <button class="btn-selecionar" data-candidatura-id="${c.candidaturaId}">Selecionar</button>
          </div>
        `).join("");
      }

      div.innerHTML = `
        <h3>${vaga.titulo}</h3>
        <p><strong>Local:</strong> ${vaga.local}</p>
        <p><strong>Descrição:</strong> ${vaga.descricao}</p>
        <p><strong>Salário:</strong> R$ ${vaga.salario.toFixed(2)}</p>
        <h4>Candidatos:</h4>
        ${candidatosHTML}
      `;

      container.appendChild(div);
    });

    document.querySelectorAll(".btn-selecionar").forEach(button => {
      button.addEventListener("click", async () => {
        const candidaturaId = button.getAttribute("data-candidatura-id");

        try {
          const response = await fetch(`http://localhost:8081/candidaturas/selecionar?candidaturaId=${candidaturaId}`, {
            method: "POST",
            headers: {
              "Authorization": `Bearer ${token}`
            }
          });

          if (response.ok) {
            alert("Candidato notificado com sucesso!");
            button.disabled = true;
            button.textContent = "Selecionado";
            button.style.backgroundColor = "#28a745";
          } else {
            const error = await response.text();
            alert("Erro ao selecionar:\n" + error);
          }
        } catch (err) {
          alert("Erro ao conectar com o servidor.");
          console.error(err);
        }
      });
    });

  } catch (err) {
    console.error(err);
    container.innerHTML = "<p>Erro ao carregar dados.</p>";
  }
});
