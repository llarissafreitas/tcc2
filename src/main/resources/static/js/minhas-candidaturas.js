document.addEventListener("DOMContentLoaded", async () => {
  const user = JSON.parse(localStorage.getItem("user"));
  const token = user?.token;
  const lista = document.getElementById("candidaturas-lista");

  try {
    const res = await fetch("http://localhost:8081/candidaturas/minhas", {
      headers: { Authorization: `Bearer ${token}` }
    });

    if (!res.ok) throw new Error("Erro ao buscar candidaturas");

    const vagas = await res.json();

    if (vagas.length === 0) {
      lista.innerHTML = "<p>Você ainda não se candidatou a nenhuma vaga.</p>";
      return;
    }

    vagas.forEach(vaga => {
      const div = document.createElement("div");
      div.className = "vaga-card";
      div.innerHTML = `
        <h3>${vaga.tituloVaga}</h3>
        <p><strong>Local:</strong> ${vaga.local}</p>
        <p><strong>Salário:</strong> R$ ${vaga.salario.toFixed(2)}</p>
        <p><strong>Empresa:</strong> ${vaga.nomeEmpresa}</p>
      `;
      lista.appendChild(div);
    });

  } catch (err) {
    console.error(err);
    lista.innerHTML = "<p>Erro ao carregar candidaturas.</p>";
  }
});
