document.addEventListener('DOMContentLoaded', function () {
  document.getElementById('guardarBtn').addEventListener('click', function () {

    const nombre = document.getElementById('nombre').value;
    const correo = document.getElementById('correo').value;
    const telefono = document.getElementById('telefono').value;
    const direccion = document.getElementById('direccion').value;


    document.getElementById('perfilname').textContent = nombre;
    document.getElementById('perfilcorreo').textContent = correo;
    document.getElementById('perfilnumber').textContent = telefono;
    document.getElementById('perfiladdress').textContent = direccion;


    const modal = bootstrap.Modal.getInstance(document.getElementById('editarPerfilModal'));
    modal.hide();
  });
});
