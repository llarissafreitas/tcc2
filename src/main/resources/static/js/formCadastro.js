// formCadastro.js

document.addEventListener("DOMContentLoaded", function () {
  const formCadastroVaga = document.getElementById("form-cadastro-vaga");
  const tituloInput = document.getElementById("titulo");
  const localInput = document.getElementById("local");
  const descricaoInput = document.getElementById("descricao");
  const acessibilidadeInput = document.getElementById("acessibilidade");
  const empregadorIdInput = document.getElementById("empregador-id");
  const mensagem = document.getElementById("mensagem");

  formCadastroVaga.addEventListener("submit", function (event) {
    event.preventDefault();

    const titulo = tituloInput.value;
    const local = localInput.value;
    const descricao = descricaoInput.value;
    const acessibilidadeRaw = acessibilidadeInput.value;
    const empregadorId = empregadorIdInput.value;

    const novaVaga = {
      titulo,
      local,
      descricao,
      acessibilidade: acessibilidadeRaw
        .split(",")
        .map(r => r.trim())
        .filter(Boolean),
      empregador: {
        id: parseInt(empregadorId)
      }
    };

    fetch("http://localhost:8080/vagas/cadastrar", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(novaVaga)
    })
      .then(res => {
        if (res.ok) {
          mensagem.textContent = "Vaga cadastrada com sucesso!";
          mensagem.style.color = "green";
          formCadastroVaga.reset();
        } else {
          return res.text().then(text => {
            mensagem.textContent = "Erro ao cadastrar: " + text;
            mensagem.style.color = "red";
          });
        }
      })
      .catch(error => {
        mensagem.textContent = " Erro de requisição: " + error.message;
        mensagem.style.color = "red";
      });
  });
});
