document.addEventListener('DOMContentLoaded', function () {
    const authModal = document.getElementById('authModal');
    const authModalLabel = document.getElementById('authModalLabel');
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');

    authModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        const authType = button.getAttribute('data-auth-type') || 'login';

        if (authType === 'login') {
            showLoginForm();
        } else if (authType === 'register') {
            showRegisterForm();
        }
    });

    // Función para mostrar formulario de login
    function showLoginForm() {
        authModalLabel.textContent = 'Iniciar Sesión';
        loginForm.style.display = 'block';
        registerForm.style.display = 'none';
    }

    // Función para mostrar formulario de registro
    function showRegisterForm() {
        authModalLabel.textContent = 'Crear Cuenta';
        loginForm.style.display = 'none';
        registerForm.style.display = 'block';
    }

    // Hacer las funciones globales para poder llamarlas desde el HTML
    window.showLoginForm = showLoginForm;
    window.showRegisterForm = showRegisterForm;
});