document.getElementById("form-vaga").addEventListener("submit", async function (e) {
    e.preventDefault();

    const formData = new FormData(this);
    const data = Object.fromEntries(formData.entries());

    const vagaDTO = {
        titulo: data.titulo,
        descricao: data.descricao,
        local: data.local,
        salario: parseFloat(data.salario),
        empregadorId: parseInt(data.empregadorId)
    };

    const user = JSON.parse(localStorage.getItem("user"));
    const token = user?.token;

    try {
        const response = await fetch("http://localhost:8081/vagas/cadastrar", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(vagaDTO)
        });

        if (response.ok) {
            alert("Vaga cadastrada com sucesso!");
            this.reset();
        } else {
            const errorText = await response.text();
            alert("Erro ao cadastrar vaga:\n" + errorText);
        }
    } catch (err) {
        alert("Erro ao conectar com o servidor.");
        console.error(err);
    }
});
