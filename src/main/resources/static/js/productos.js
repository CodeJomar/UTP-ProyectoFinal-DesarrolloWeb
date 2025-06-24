// Actualizar precio máximo del rango
document.getElementById('rangoPrecio').addEventListener('input', function () {
  document.getElementById('precioMaximo').textContent = this.value;
});

function limpiarFiltros() {
  document.querySelectorAll('.form-check-input').forEach(input => input.checked = false);
  document.getElementById('rangoPrecio').value = 250;
  document.getElementById('precioMaximo').textContent = '250';
}