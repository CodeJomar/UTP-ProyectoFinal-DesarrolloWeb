document.addEventListener('DOMContentLoaded', function () {
  const fechaInput = document.getElementById('fecha');
  const today = new Date().toISOString().split('T')[0];
  fechaInput.setAttribute('min', today);

  const form = document.getElementById('citaForm');
  form.addEventListener('submit', function (event) {
    event.preventDefault();
    event.stopPropagation();

    if (form.checkValidity()) {
      guardarCita();
    }

    form.classList.add('was-validated');
  });

  // Limpiar formulario al cerrar modal
  const modal = document.getElementById('citaModal');
  modal.addEventListener('hidden.bs.modal', function () {
    limpiarFormulario();
  });
});

let editandoCita = false;
let citaEditId = null;

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

function guardarCita() {
  const formData = {
    usuario_id: document.getElementById('usuario_id').value,
    motivo_id: document.getElementById('motivo_id').value,
    estado_id: document.getElementById('estado_id').value,
    fecha: document.getElementById('fecha').value,
    hora: document.getElementById('hora').value,
    observaciones: document.getElementById('observaciones').value
  };

  const fechaSeleccionada = new Date(formData.fecha);
  const hoy = new Date();
  hoy.setHours(0, 0, 0, 0);

  if (fechaSeleccionada < hoy) {
    mostrarAlerta('No puedes agendar citas en fechas pasadas.', 'danger');
    return;
  }

  if (editandoCita) {
    mostrarAlerta('Cita actualizada correctamente', 'success');
    editandoCita = false;
    document.getElementById('citaModalLabel').innerHTML = '<i class="bi bi-calendar-plus me-2"></i>Nueva Cita';
  } else {
    mostrarAlerta('Cita agendada correctamente', 'success');
    agregarFilaCita(formData);
  }

  const modal = bootstrap.Modal.getInstance(document.getElementById('citaModal'));
  modal.hide();
  limpiarFormulario();
}

function agregarFilaCita(data) {
  const tbody = document.getElementById('citasTableBody');
  const newRow = document.createElement('tr');

  const motivoTexto = document.querySelector(`#motivo_id option[value="${data.motivo_id}"]`).textContent;
  const estadoTexto = document.querySelector(`#estado_id option[value="${data.estado_id}"]`).textContent;
  const estadoClass = getEstadoClass(data.estado_id);

  newRow.innerHTML = `
                <td>${String(tbody.children.length + 1).padStart(3, '0')}</td>
                <td>${motivoTexto}</td>
                <td>${data.fecha}</td>
                <td>${data.hora}</td>
                <td><span class="status-badge ${estadoClass}">${estadoTexto}</span></td>
                <td>
                    <button class="btn btn-sm btn-outline-primary btn-action" onclick="editarCita(${tbody.children.length + 1})" title="Editar">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="btn btn-sm btn-outline-danger btn-action" onclick="cancelarCita(${tbody.children.length + 1})" title="Cancelar">
                        <i class="bi bi-x-circle"></i>
                    </button>
                </td>
            `;

  tbody.appendChild(newRow);
}

function getEstadoClass(estadoId) {
  const clases = {
    '1': 'status-pendiente',
    '2': 'status-confirmada',
    '3': 'status-completada',
    '4': 'status-cancelada'
  };
  return clases[estadoId] || 'status-pendiente';
}

function cancelarCita(id) {
  if (confirm('¿Está seguro de que desea cancelar esta cita?')) {
    mostrarAlerta('Cita cancelada correctamente', 'warning');
  }
}

function limpiarFormulario() {
  document.getElementById('citaForm').reset();
  document.getElementById('citaForm').classList.remove('was-validated');
  document.getElementById('citaId').value = '';
  editandoCita = false;
  citaEditId = null;

  document.getElementById('estado_id').value = '1';
  document.getElementById('citaModalLabel').innerHTML = '<i class="bi bi-calendar-plus me-2"></i>Nueva Cita';
}

function logout() {
  if (confirm('¿Está seguro de que desea cerrar sesión?')) {
    window.location.href = 'index.html';
  }
}


document.addEventListener("DOMContentLoaded", function () {
  const container = document.querySelector("[data-abrir-modal]");
  if (!container) return;

  const abrirModal = container.getAttribute("data-abrir-modal");
  if (abrirModal && abrirModal.toLowerCase() === "true") {
    const citaModal = new bootstrap.Modal(document.getElementById('citaModal'));
    citaModal.show();
  }
});

