document.addEventListener('DOMContentLoaded', () => {
  const tipo = document.getElementById('tipo');
  const form = document.getElementById('form-usuario');
  const candidatoFields = document.getElementById('candidato-fields');
  const empregadorFields = document.getElementById('empregador-fields');

  tipo.addEventListener('change', () => {
    if (tipo.value === 'CANDIDATO') {
      candidatoFields.style.display = 'block';
      empregadorFields.style.display = 'none';
      toggleRequired(candidatoFields, true);
      toggleRequired(empregadorFields, false);
    } else if (tipo.value === 'EMPREGADOR') {
      candidatoFields.style.display = 'none';
      empregadorFields.style.display = 'block';
      toggleRequired(candidatoFields, false);
      toggleRequired(empregadorFields, true);
    } else {
      candidatoFields.style.display = 'none';
      empregadorFields.style.display = 'none';
      toggleRequired(candidatoFields, false);
      toggleRequired(empregadorFields, false);
    }
  });

  function toggleRequired(container, required) {
    container.querySelectorAll('input').forEach(input => {
      if (required) {
        input.setAttribute('required', 'required');
      } else {
        input.removeAttribute('required');
      }
    });
  }

  form.addEventListener('submit', (e) => {
    e.preventDefault();
    const formData = new FormData(form);
    const tipoUsuario = formData.get('tipo');
    const email = formData.get('email');
    const senha = formData.get('senha');

    let url, data;

    if (tipoUsuario === 'CANDIDATO') {
      url = 'http://localhost:8081/cadastro/candidato';
      data = {
        nome: formData.get('nome'),
        telefone: formData.get('telefone'),
        cpf: formData.get('cpf'),
        endereco: formData.get('enderecoCandidato'),
        habilidades: formData.get('habilidades'),
        experiencia: formData.get('experiencia'),
        email,
        senha
      };
    } else if (tipoUsuario === 'EMPREGADOR') {
      url = 'http://localhost:8081/cadastro/empregador';
      data = {
        nome: formData.get('nomeEmpresa'),
        cnpj: formData.get('cnpj'),
        descricao: formData.get('descricao'),
        setor: formData.get('setor'),
        telefone: formData.get('telefoneEmpresa'),
        endereco: formData.get('enderecoEmpregador'),
        email,
        senha
      };
    } else {
      alert("Selecione um tipo de usuário válido antes de cadastrar.");
      return;
    }

    fetch(url, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    })
    .then(res => {
      if (!res.ok) return res.text().then(text => Promise.reject(text));
      return res.text(); // <- pega como texto mesmo que seja sucesso
    })
    .then(responseText => {
      alert('Cadastro realizado com sucesso: ' + responseText);
      form.reset();
      candidatoFields.style.display = 'none';
      empregadorFields.style.display = 'none';
    })
    .catch(err => {
      console.error('Erro completo:', err);
      alert('Erro no cadastro: ' + err);
    });

  });
});
