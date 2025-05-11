document.addEventListener('DOMContentLoaded', async () => {
  const lista = document.getElementById('vaga-lista');
  const user = JSON.parse(localStorage.getItem('user'));
  const token = user?.token;

  const style = document.createElement('style');
  style.innerHTML = `
    button, .btn-candidatar {
      background-color: #ffffff !important;
      color: #000000 !important;
      border: 2px solid #ffc107;
      padding: 0.5rem 1rem;
      font-weight: bold;
    }
    button:hover, .btn-candidatar:hover {
      background-color: #ffc107;
      color: #000000;
    }
  `;
  document.head.appendChild(style);

  try {
    const response = await fetch('http://localhost:8081/vagas/listar-sem-paginacao', {
      headers: token ? { 'Authorization': `Bearer ${token}` } : {}
    });

    if (!response.ok) throw new Error('Erro ao buscar vagas');

    const vagas = await response.json();
    if (!Array.isArray(vagas)) throw new Error('Formato de resposta inválido');

    if (vagas.length === 0) {
      lista.innerHTML = '<p>Nenhuma vaga disponível no momento.</p>';
      return;
    }

    lista.innerHTML = '';

    vagas.forEach(vaga => {
      const div = document.createElement('div');
      div.className = 'vaga-card';
      div.innerHTML = `
        <h3>${vaga.titulo}</h3>
        <p><strong>Descrição:</strong> ${vaga.descricao}</p>
        <p><strong>Local:</strong> ${vaga.local}</p>
        <p><strong>Salário:</strong> R$ ${vaga.salario.toFixed(2)}</p>
        <button class="btn-candidatar" data-id="${vaga.id}">Candidatar-se</button>
      `;
      lista.appendChild(div);
    });

    document.querySelectorAll('.btn-candidatar').forEach(button => {
      button.addEventListener('click', async (e) => {
        if (!user || !token) {
          alert('Você precisa estar logado como candidato para se candidatar.');
          return;
        }

        if (user.tipo !== 'CANDIDATO') {
          alert('Apenas candidatos podem se candidatar a vagas.');
          return;
        }

        const vagaId = e.target.getAttribute('data-id');

        try {
          const res = await fetch(`http://localhost:8081/candidaturas/cadastrar?vagaId=${vagaId}`, {
            method: 'POST',
            headers: {
              'Authorization': `Bearer ${token}`
            }
          });

          if (!res.ok) throw new Error('Erro ao enviar candidatura');

          alert('Candidatura enviada com sucesso!');
        } catch (err) {
          alert(`Erro ao candidatar: ${err.message}`);
        }
      });
    });

  } catch (error) {
    console.error(error);
    lista.innerHTML = '<p>Erro ao carregar vagas. Tente novamente mais tarde.</p>';
  }
});
