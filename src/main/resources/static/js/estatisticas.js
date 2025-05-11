document.addEventListener('DOMContentLoaded', () => {
  fetch('/api/estatisticas')
    .then(res => res.ok ? res.json() : Promise.reject('Erro ao buscar estatísticas'))
    .then(data => {
      document.getElementById('candidatos-count').textContent = data.candidatos ?? 0;
      document.getElementById('vagas-count').textContent = data.vagas ?? 0;
    })
    .catch(err => console.error(err));
});
