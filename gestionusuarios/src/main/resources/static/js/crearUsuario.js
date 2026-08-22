// Call the dataTables jQuery plugin
$(document).ready(function () {
	document.querySelector("#crearUsuario").addEventListener("click", ()=>{
		crearUsuario();
	})
	
});

const API_URL = "api/registrar"


async function crearUsuario() {	
	const formData = new FormData(document.querySelector("#formCreateUser"));
	console.log(formData);

	if (formData.get("password") !== formData.get("repeatPassword")) {
		alert("Las contraseñas no coinciden");
		return;
	}

	if (!formData.get("nombre") || !formData.get("apellido") || !formData.get("email") || !formData.get("telefono") || !formData.get("password")) {
		alert("Todos los campos son obligatorios");
		return;
	}

	const usuario = {
		nombre: formData.get("nombre"),
		apellido: formData.get("apellido"),
		email: formData.get("email"),
		telefono: Number(formData.get("telefono")),
		password: formData.get("password")
	}

	const usuarios = await fetch(`${API_URL}`, {
		method: 'POST',
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json"
		},
		body: JSON.stringify(usuario)
	})
		.catch(error => console.error('Error:', error));

	if(!usuarios.ok){
		alert("Error al crear el usuario");
		return;
	}
	
	alert("Usuario creado correctamente");
	document.getElementById('formCreateUser').reset();
}


