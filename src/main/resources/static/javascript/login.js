document.addEventListener('DOMContentLoaded', function () {
  const modal = document.getElementById('modal');
  const modalContent = modal.querySelector('.modal-content');

  modal.addEventListener('show.bs.modal', function (event) {
    let button = event.relatedTarget;
    let cardLogin = button.getAttribute('data-modal-login');
    let cardBarber = button.getAttribute('data-card-barber');

    switch (cardLogin) {
      case 'login':
        modalContent.innerHTML =
          `
        <section class="row mt-3 d-flex justify-content-center align-items-center text-center">
          <div class="col-12 mb-3">
            <h2 class="fw-bold text-uppercase">Iniciar Sesión</h2>
            <p class="text-muted">Por favor, ingresa tu correo electrónico y contraseña</p>
          </div>
        </section>
        <section class="row mt-2 mb-2 d-flex justify-content-center align-items-center text-center">
          <form action="">
            <!-- Email -->
            <div class="col-12 mb-3">
              <div class="input-group">
                <span class="input-group-text">@</span>
                <div class="form-floating">
                  <input type="email" class="form-control" id="floatingInputGroup1" placeholder="name@example.com"
                    required>
                  <label for="floatingInputGroup1">Correo Electrónico</label>
                </div>
              </div>
            </div>
            <!-- Contraseña -->
            <div class="col-12 mb-3">
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-key"></i></span>
                <div class="form-floating">
                  <input type="password" class="form-control" id="floatingInputGroup1" placeholder="Contraseña"
                    required>
                  <label for="floatingInputGroup1">Contraseña</label>
                </div>
              </div>
            </div>
            <!-- Submit -->
            <div class="col-12 mb-3 text-center">
              <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
            </div>
          </form>
        </section>
        <section class="row mb-3 d-flex justify-content-center align-items-center text-center">
          <div class="col-12">
            <p class="mb-0">¿No tienes cuenta? <a href="/login" class="fw-bold">Registrate Aquí</a>
            </p>
          </div>
        </section>`;
       break;
    }

    switch (cardBarber) {
        case 'cabello':
        modalContent.innerHTML =
        `
          <div class="modal-header">
            <h5 class="modal-title">Modal title</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <p>Modal body text goes here.</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        `;
    }
  })
})