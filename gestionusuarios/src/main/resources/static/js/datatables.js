// Call the dataTables jQuery plugin
$(document).ready(function() {
	cargarUsuarios();
  $('#usuariosTable').DataTable();
});

async function cargarUsuario(){
	const usuarios = await fetch('http://localhost:8080/usuario/1',{
		method: 'GET',
		headers:{
			"Accept": "application/json",
			"Content-Type": "application/json"
		}
	}).then(response => response.json())
		.catch(error => console.error('Error:', error));
	
}

async function cargarUsuarios(){
	const usuarios = await fetch('http://localhost:8080/usuarios',{
		method: 'GET',
		headers:{
			"Accept": "application/json",
			"Content-Type": "application/json"
		}
	}).then(response => response.json())
		.catch(error => console.error('Error:', error));

	if(usuarios.length > 0){
		document.querySelector('#usuariosTable tbody').innerHTML = '';

		usuarios.forEach(usuario => {

			let buttonEliminar = document.createElement('button');
			buttonEliminar.classList.add('btn', 'btn-danger','btn-sm');
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
				<td>
					${buttonEditar.outerHTML}
					${buttonEliminar.outerHTML}
				</td>
			`;
			
			document.querySelector('#usuariosTable tbody').appendChild(row);
		});
	}
	
}
