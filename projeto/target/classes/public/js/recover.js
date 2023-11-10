document.addEventListener("DOMContentLoaded", function() {
	// Extrair o token da URL
	const urlParams = new URLSearchParams(window.location.search);
	const token = urlParams.get("token");

	const resetPasswordForm = document.getElementById("resetPasswordForm");
	const messageDiv = document.createElement("div");
	messageDiv.classList.add("messageDiv"); // Adicionei a classe 'messageDiv'
	messageDiv.style.fontSize = "12px";
	messageDiv.style.textAlign = "center";
	messageDiv.style.position = "absolute"; // Alterei para 'absolute'
	messageDiv.style.bottom = "0";
	messageDiv.style.left = "0";
	messageDiv.style.width = "100%";
	messageDiv.style.zIndex = "999";
	messageDiv.style.display = "none";

	resetPasswordForm.appendChild(messageDiv);

	// Selecionando o botão "RECUPERAR" pela classe
	const recuperarButton = document.querySelector(".btn");

	// Desativando o botão "RECUPERAR" inicialmente
	recuperarButton.disabled = true;
	
	
    // Regex para validar a senha
    const passwordRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%\^&\*]).{8,}$/;


	// Função para verificar se os campos de senha têm pelo menos 8 caracteres
	function validatePasswordFields() {
		const password = document.getElementById("novaSenha").value;
		const confirmation_password = document.getElementById("confirmacaoSenha").value;

		// Ativando ou desativando o botão "RECUPERAR" com base no comprimento dos campos
		recuperarButton.disabled = !(password.length >= 8 && confirmation_password.length >= 8);
	}

	// Adicionando um ouvinte de evento de entrada para os campos de senha
	document.getElementById("novaSenha").addEventListener("input", validatePasswordFields);
	document.getElementById("confirmacaoSenha").addEventListener("input", validatePasswordFields);



	resetPasswordForm.addEventListener("submit", function(event) {
		event.preventDefault();

		// Obtendo dados do formulário
		const password = document.getElementById("novaSenha").value;
		const confirmation_password = document.getElementById("confirmacaoSenha").value;
		
		// Construindo dados para enviar ao servidor
		const data = {
			password: password,
			confirmation_password: confirmation_password,
		};
		if (!passwordRegex.test(password)) {
            messageDiv.textContent = "Senha requer número, símbolo, letra maiúscula e minúscula".toUpperCase();
			messageDiv.classList.add("error");
			messageDiv.style.display = "block";
		} else if (password !== confirmation_password) {
			messageDiv.textContent = "Senhas não conferem".toUpperCase();
			messageDiv.classList.add("error");
			messageDiv.style.display = "block";
		} else {
			if (token) {
				fetch("https://b13-investimentos.herokuapp.com/resetPassword", {
					method: "POST",
					headers: {
						Authorization: "Bearer " + token,
						"Content-Type": "application/json",
					},
					body: JSON.stringify(data),
				})
					.then((response) => {
						if (response.status === 200) {
							// Em caso de sucesso, redirecionar para a página de sucesso
							window.location.href = "/public/sucessrecover.html";
							// Em caso de sucesso
							// messageDiv.textContent = "Senha alterada com sucesso".toUpperCase();
							// messageDiv.classList.add("success"); // Adicionei a classe 'success'
							// messageDiv.style.display = "block";
						} else {
							// Tratar erros com base na mensagem da resposta
							return response.json();
						}
					})
					.then((data) => {
						// Exibir mensagem de erro em caso de falha
						if (data && data.message) {
							const errorCode = data.message.substring(0, 4);

							switch (errorCode) {
								case "1007":
									messageDiv.textContent = "mínimo 8 caracteres".toUpperCase();
									messageDiv.classList.add("error"); // Adicionei a classe 'error'
									break;
								case "2003":
									messageDiv.textContent = "senhas não conferem".toUpperCase();
									messageDiv.classList.add("error"); // Adicionei a classe 'error'
									break;
								case "7002":
									messageDiv.textContent = "token de acesso invalido".toUpperCase();
									messageDiv.classList.add("error"); // Adicionei a classe 'error'
									break;
								case "7003":
									messageDiv.textContent = "link expirado".toUpperCase();
									messageDiv.classList.add("error"); // Adicionei a classe 'error'
									break;
								case "7004":
									messageDiv.textContent = "token de acesso invalido".toUpperCase();
									messageDiv.classList.add("error"); // Adicionei a classe 'error'
									break;
								case "7005":
									messageDiv.textContent = "link inválido".toUpperCase();
									messageDiv.classList.add("error"); // Adicionei a classe 'error'
									break;
								case "7006":
									messageDiv.textContent = "token de acesso invalido".toUpperCase();
									messageDiv.classList.add("error"); // Adicionei a classe 'error'
									break;
								default:
									messageDiv.textContent = "falha na solicitação".toUpperCase();
									messageDiv.classList.add("error"); // Adicionei a classe 'error'
									break;
							}

							messageDiv.style.display = "block";
						}
					})
					.catch((error) => {
						console.error(error);
					});
			}
		}

	});
});
