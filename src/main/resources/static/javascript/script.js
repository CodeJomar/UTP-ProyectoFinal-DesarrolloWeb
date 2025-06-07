document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('modal');
    const modalTitle = modal.querySelector('.modal-title');
    const modalBody = modal.querySelector('.modal-body');
    const modalFooter = modal.querySelector('.modal-footer');

    modal.addEventListener('show.bs.modal', function (event) {
        let button = event.relatedTarget;
        let cardBarber = button.getAttribute('data-card-barber');

        switch (cardBarber) {
            case 'comentario':
                modalTitle.innerHTML =
                    `
                <h5>Comentanos tu experiencia</h5>
                `;

                modalBody.innerHTML =
                    `
                <form class="container">
                    <p>Queremos saber cómo te sentistes utilizando la página o la atención al cliente recibida</p>
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
                        <label for="floatingTextarea2">Comentario</label>
                    </div>
                    <div class="text-center mt-3">
                        <button type="submit" class="btn btn-outline-primary">Enviar</button>
                     </div>
                </form>
                `;

                modalFooter.innerHTML =
                    `
                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cerrar</button>
                `;
                break;
            case 'cabello':
                modalTitle.innerHTML =
                    `
                <h5>¿Cómo saber si me va a quedar bien?</h5>
                `;

                modalBody.innerHTML =
                    `
                <p>lorem ipsum dolor sit amet consectetur adipisicing elit. Quisquam, voluptatibus.</p>
                `;

                modalFooter.innerHTML =
                    `
                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cerrar</button>
                `;
                break;

            case 'producto':
                modalTitle.innerHTML =
                    `
                <h5>Caracteristicas del producto</h5>
                `;

                modalBody.innerHTML =
                    `
                <p>lorem ipsum dolor sit amet consectetur adipisicing elit. Quisquam, voluptatibus.</p>
                `;

                modalFooter.innerHTML =
                    `
                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-outline-success">Comprar</button>
                `;
                break;
            case 'citas':
                modalTitle.innerHTML =
                    `
                    <h5 class="modal-title">Reserva tu Cita</h5>
                `;
                modalBody.innerHTML =
                    `
                <form class="container">
                    <input type="text" class="form-control mb-2" placeholder="Nombre">
                    <input type="email" class="form-control mb-2" placeholder="Correo">
                    <input type="datetime-local" class="form-control mb-2">
                </form>
                `;

                modalFooter.innerHTML =
                    `
                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cerrar</button>
                <button type="submit" class="btn btn-outline-primary">Reservar</button>
                `;
                break;

        }
    })
});