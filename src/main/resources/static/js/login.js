document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('login-form');

  form?.addEventListener('submit', async (e) => {
    e.preventDefault();
    const email = form.email.value;
    const senha = form.senha.value;

    try {
      const response = await fetch('/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, senha })
      });

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText);
      }

      const user = await response.json();

      if (!user.token) {
        throw new Error('Token JWT não retornado do servidor.');
      }

      const usuario = {
        email: user.email,
        tipo: user.tipo,
        token: user.token
      };

      localStorage.setItem('user', JSON.stringify(usuario));
      localStorage.setItem('token', user.token);

      alert('Login realizado com sucesso!');
      window.location.href = '/vagas.html';
    } catch (err) {
      console.error('Erro no login:', err);
      alert('Erro no login: ' + err.message);
    }
  });
});
