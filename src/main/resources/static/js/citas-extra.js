document.addEventListener('DOMContentLoaded', function () {
  actualizarEstadisticas();
});

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

// Función para confirmar cita // Función para completar cita

function cambiarEstado(idCita, nuevoEstado) {
  fetch(`/citas/estado?id=${idCita}&nuevoEstado=${nuevoEstado}`, {
    method: 'GET'
  })
    .then(response => {
      if (response.ok) {
        location.reload(); // o actualiza la tabla sin recargar
      } else {
        alert("Error al cambiar el estado de la cita.");
      }
    })
    .catch(error => console.error('Error:', error));
}

function eliminarCita(idCita) {
  if (confirm("¿Estás seguro de que deseas eliminar esta cita?")) {
    fetch(`/citas/eliminar?id=${idCita}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: `id=${idCita}`
    })
      .then(response => {
        if (response.ok) {
          location.reload(); // o elimina la fila directamente
        } else {
          alert("Error al eliminar la cita.");
        }
      })
      .catch(error => console.error('Error:', error));
  }
}

function cambiarEstado(idCita, nuevoEstado) {
  fetch(`/citas/estado?id=${idCita}&nuevoEstado=${nuevoEstado}`, {
    method: 'GET'
  })
    .then(response => {
      if (response.ok) {
        location.reload(); // Puedes optimizar esto actualizando solo la fila
      } else {
        alert("No se pudo actualizar el estado.");
      }
    })
    .catch(error => console.error('Error al cambiar estado:', error));
}

function eliminarCita(idCita) {
  if (confirm("¿Seguro que deseas eliminar esta cita?")) {
    fetch(`/citas/eliminar?id=${idCita}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: `id=${idCita}`
    })
      .then(response => {
        if (response.ok) {
          location.reload();
        } else {
          alert("No se pudo eliminar la cita.");
        }
      })
      .catch(error => console.error('Error al eliminar cita:', error));
  }
}


// Función para editar cita
function editarCita(id) {
  // Simular carga de datos
  document.getElementById('editCitaId').value = id;
  document.getElementById('editCliente').value = 'Juan Pérez';
  document.getElementById('editMotivo').value = '1';
  document.getElementById('editFecha').value = '2025-01-15';
  document.getElementById('editHora').value = '10:30';
  document.getElementById('editEstado').value = '2';

  // Abrir modal
  const modal = new bootstrap.Modal(document.getElementById('editCitaModal'));
  modal.show();
}

// Función para guardar cambios de cita
function guardarCambiosCita() {
  const form = document.getElementById('editCitaForm');
  if (form.checkValidity()) {
    mostrarAlerta('Cita actualizada correctamente', 'success');

    // Cerrar modal
    const modal = bootstrap.Modal.getInstance(document.getElementById('editCitaModal'));
    modal.hide();

    // Actualizar tabla
    actualizarTabla();
  } else {
    form.classList.add('was-validated');
  }
}

// Función para actualizar estado de cita en la tabla
function actualizarEstadoCita(id, nuevoEstado) {
  // Aquí iría la lógica para actualizar en el servidor
  // Por ahora solo actualizamos visualmente
  actualizarEstadisticas();
}

// Función para actualizar estadísticas
function actualizarEstadisticas() {
  // Simular conteo de citas
  const filas = document.querySelectorAll('#citasTableBody tr');
  let pendientes = 0, confirmadas = 0, completadas = 0;

  filas.forEach(fila => {
    const estado = fila.querySelector('.status-badge');
    if (estado) {
      if (estado.classList.contains('status-pendiente')) pendientes++;
      else if (estado.classList.contains('status-confirmada')) confirmadas++;
      else if (estado.classList.contains('status-completada')) completadas++;
    }
  });

  document.getElementById('totalCitas').textContent = filas.length;
  document.getElementById('citasPendientes').textContent = pendientes;
  document.getElementById('citasConfirmadas').textContent = confirmadas;
  document.getElementById('citasCompletadas').textContent = completadas;
}

// Función para filtrar citas
function filtrarCitas() {
  const filtroEstado = document.getElementById('filtroEstado').value;
  const filtroMotivo = document.getElementById('filtroMotivo').value;
  const filtroFecha = document.getElementById('filtroFecha').value;

  // Aquí iría la lógica de filtrado
  console.log('Filtrando citas...', { filtroEstado, filtroMotivo, filtroFecha });
}

// Función para buscar citas
function buscarCitas() {
  const termino = document.getElementById('buscarCliente').value.toLowerCase();
  const filas = document.querySelectorAll('#citasTableBody tr');

  filas.forEach(fila => {
    const cliente = fila.cells[1].textContent.toLowerCase();
    const email = fila.cells[2].textContent.toLowerCase();

    if (cliente.includes(termino) || email.includes(termino)) {
      fila.style.display = '';
    } else {
      fila.style.display = 'none';
    }
  });
}

// Función para actualizar tabla
function actualizarTabla() {
  mostrarAlerta('Tabla actualizada', 'info');
  // Aquí iría la lógica para recargar datos del servidor
}

// Función para exportar citas
function exportarCitas() {
  mostrarAlerta('Exportando citas...', 'info');
  // Aquí iría la lógica para exportar a Excel/PDF
}

// Función para logout
function logout() {
  if (confirm('¿Está seguro de que desea cerrar sesión?')) {
    window.location.href = 'index.html';
  }
}