document.addEventListener("DOMContentLoaded", () => {
  const authModal = document.getElementById("authModal")
  const authModalLabel = document.getElementById("authModalLabel")
  const loginForm = document.getElementById("loginForm")
  const registerForm = document.getElementById("registerForm")

  // Función para realizar logout
  window.performLogout = () => {
    const form = document.getElementById("logoutForm")
    if (form) {
      form.submit()
    }
  }

  if (authModal) {
    authModal.addEventListener("show.bs.modal", (event) => {
      const button = event.relatedTarget
      if (button) {
        const authType = button.getAttribute("data-auth-type") || "login"

        if (authType === "login") {
          showLoginForm()
        } else if (authType === "register") {
          showRegisterForm()
        }
      }
    })
  }

  // Función para mostrar formulario de login
  function showLoginForm() {
    if (authModalLabel) authModalLabel.textContent = "Iniciar Sesión"
    if (loginForm) loginForm.style.display = "block"
    if (registerForm) registerForm.style.display = "none"
  }

  // Función para mostrar formulario de registro
  function showRegisterForm() {
    if (authModalLabel) authModalLabel.textContent = "Crear Cuenta"
    if (loginForm) loginForm.style.display = "none"
    if (registerForm) registerForm.style.display = "block"
  }

  // Hacer las funciones globales para poder llamarlas desde el HTML
  window.showLoginForm = showLoginForm
  window.showRegisterForm = showRegisterForm

  // Manejar parámetros de URL para mostrar mensajes
  const urlParams = new URLSearchParams(window.location.search)

  if (urlParams.has("logout")) {
    console.log("Usuario desconectado exitosamente")
    // Opcional: mostrar un mensaje de éxito
  }

  if (urlParams.has("error")) {
    console.log("Error en el login")
    // Opcional: mostrar el modal de login automáticamente
  }

  if (urlParams.has("registrado")) {
    console.log("Usuario registrado exitosamente")
    // Opcional: mostrar un mensaje de éxito
  }

  if (urlParams.has("errorRegistro")) {
    const abrirRegistro = sessionStorage.getItem("abrirRegistro");

    // Mostrar el modal y formulario de registro
    const modal = new bootstrap.Modal(authModal)
    showRegisterForm()
    modal.show()

    // Limpiar la bandera para que no se muestre indefinidamente
    sessionStorage.removeItem("abrirRegistro")
  }

  if (urlParams.has("error")) {
    const modal = new bootstrap.Modal(authModal)
    showLoginForm()
    modal.show()
  }

})
