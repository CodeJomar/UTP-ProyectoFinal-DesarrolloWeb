document.addEventListener("DOMContentLoaded", function () {
    // Mostrar el modal si hay errores de validación
    const shouldShowModal = document.body.getAttribute("data-mostrar-modal") === "true";
    if (shouldShowModal) {
        const modal = new bootstrap.Modal(document.getElementById('editarPerfilModal'));
        modal.show();
    }
});





