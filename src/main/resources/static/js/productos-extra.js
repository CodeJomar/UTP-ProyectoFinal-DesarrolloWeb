document.addEventListener('DOMContentLoaded', function () {
  // Validación de formulario
  const form = document.getElementById('productoForm');
  form.addEventListener('submit', function (event) {
   // event.preventDefault();
    //event.stopPropagation();

    form.classList.add('was-validated');
  });
});

// Variables globales
let editandoProducto = false;
let productoEditId = null;

// Función para mostrar alertas
function mostrarAlerta(mensaje, tipo = 'success') {
  const alertContainer = document.getElementById('alert-container');
  const alertDiv = document.createElement('div');
  alertDiv.className = `alert alert-${tipo} alert-dismissible fade show`;
  alertDiv.innerHTML = `
                <i class="bi bi-${tipo === 'success' ? 'check-circle' : 'exclamation-triangle'} me-2"></i>
                ${mensaje}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            `;
  alertContainer.appendChild(alertDiv);

  setTimeout(() => {
    if (alertDiv.parentNode) {
      alertDiv.remove();
    }
  }, 5000);
}

function limpiarFormulario() {
  document.getElementById('productoForm').reset();
  document.getElementById('productoForm').classList.remove('was-validated');
}