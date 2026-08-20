// Call the dataTables jQuery plugin
$(document).ready(function () {
	cargarUsuarios();
	$('#usuariosTable').DataTable();
});

const API_URL = "api/usuarios"


async function cargarUsuarios() {
	const usuarios = await fetch(API_URL, {
		method: 'GET',
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json"
		}
	}).then(response => response.json())
		.catch(error => console.error('Error:', error));

	if (usuarios.length > 0) {
		document.querySelector('#usuariosTable tbody').innerHTML = '';

		usuarios.forEach(usuario => {

			let buttonEliminar = document.createElement('button');
			buttonEliminar.classList.add('btn', 'btn-danger', 'btn-sm');
			buttonEliminar.innerHTML = '<i class="fas fa-trash-alt"></i>';

			let buttonEditar = document.createElement('button');
			buttonEditar.classList.add('btn', 'btn-primary', 'btn-sm');
			buttonEditar.innerHTML = '<i class="fas fa-edit"></i>';

			let row = document.createElement('tr');

			row.innerHTML = `
				<td>${usuario.id}</td>
				<td>${usuario.nombre} ${usuario.apellido}</td>
				<td>${usuario.email}</td>
				<td>${usuario.telefono}</td>
				<td class="actions"></td>
			`;

			const actionsCell = row.querySelector('.actions');
			actionsCell.appendChild(buttonEditar);
			actionsCell.appendChild(buttonEliminar);

			document.querySelector('#usuariosTable tbody').appendChild(row);

			// Agregar evento al botón de eliminar
			buttonEliminar.addEventListener("click", async () => {
				await deleteUsuario(usuario.id);
				await cargarUsuarios();
			});

		});
	}

}

async function deleteUsuario(id) {
	console.log("Eliminando usuario con id:", id);
	if(!confirm(`¿Estás seguro de que deseas eliminar al usuario con id ${id}?`)) {
		return;
	}
	try {
		const response = await fetch(`${API_URL}/${id}`,
			{
				method: "DELETE",
				headers: {
					"Accept": "application/json",
					"Content-Type": "application/json"
				}
			}
		)
		if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
		console.log(`Usuario con id ${id} eliminado correctamente`);

	} catch (error) {
		console.error('Error al eliminar el usuario:', error);
	}

}
