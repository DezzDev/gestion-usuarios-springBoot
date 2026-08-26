document.addEventListener("DOMContentLoaded", ()=>{

	document.querySelector("#loginBtn").addEventListener("click", async (e)=>{
		e.preventDefault();
		login();
	});

});
const API_URL = "api/login"

async function login(){
	console.log("Iniciando sesión...");
	const formData = new FormData(document.querySelector("#loginForm"));

	if(!formData.get("email") || !formData.get("password")){
		alert("Por favor, complete todos los campos.");
		return;
	}

	const loginData = {
		email: formData.get("email"),
		password: formData.get("password")
	};
	
	try {
		const data = await fetch(`${API_URL}`, {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(loginData)
		});
		const response = await data.text();
		console.log("Respuesta del servidor:", response);

		if (response === "error") {
			throw new Error("Error en la solicitud de inicio de sesión");
		}

		// Guardar el token en el almacenamiento local
		localStorage.setItem("token", response);
		localStorage.setItem("email", loginData.email);
		alert("Inicio de sesión exitoso. Redirigiendo a la página de usuarios...");
		document.location.href = "usuarios.html";

		
	} catch (error) {
		alert("Error al iniciar sesión. Por favor, verifique sus credenciales.");
		console.error("Error:", error);
	}
}