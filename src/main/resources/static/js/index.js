document.addEventListener("DOMContentLoaded", () => {
  console.log("Index.js cargado correctamente")

  const infoModal = document.getElementById("infoModal")
  const infoModalBody = document.getElementById("infoModalBody")
  const infoModalLabel = document.getElementById("infoModalLabel")

  if (!infoModal || !infoModalBody || !infoModalLabel) {
    console.error("Elementos del modal de info no encontrados")
    return
  }

  infoModal.addEventListener("show.bs.modal", (event) => {
    const button = event.relatedTarget
    if (!button) return

    const type = button.getAttribute("data-type")

    console.log("Mostrando modal info:", type) // Para debug

    if (type === "service") {
      const service = button.getAttribute("data-service")
      showServiceDetails(service)
    } else if (type === "product") {
      const product = button.getAttribute("data-product")
      showProductDetails(product)
    }
  })

  function showServiceDetails(service) {
    infoModalLabel.textContent = "Detalles del Servicio"

    const serviceDetails = {
      buzzcut: {
        title: "Buzz Cut",
        price: "$15.00",
        duration: "20 min",
        description: "Corte clásico y versátil, perfecto para un look limpio y moderno. Ideal para cualquier ocasión.",
        features: ["Corte con máquina", "Acabado profesional", "Estilo versátil", "Fácil mantenimiento"],
      },
      frenchcrop: {
        title: "French Crop",
        price: "$25.00",
        duration: "35 min",
        description: "Estilo europeo sofisticado con textura en la parte superior y laterales cortos.",
        features: ["Corte con tijera", "Texturizado superior", "Laterales degradados", "Estilo moderno"],
      },
      modcut: {
        title: "Mod Cut",
        price: "$22.00",
        duration: "30 min",
        description: "Inspirado en los años 60, este corte ofrece un look retro con un toque contemporáneo.",
        features: ["Estilo retro", "Corte preciso", "Look contemporáneo", "Versátil"],
      },
    }

    const details = serviceDetails[service] || serviceDetails["buzzcut"]

    infoModalBody.innerHTML = `
            <div class="text-center mb-4">
                <h4>${details.title}</h4>
                <div class="d-flex justify-content-center gap-4 mb-3">
                    <span class="badge bg-primary fs-6">${details.price}</span>
                    <span class="badge bg-secondary fs-6">${details.duration}</span>
                </div>
            </div>
            <p class="lead text-center mb-4">${details.description}</p>
            <h6>Incluye:</h6>
            <ul class="list-unstyled mb-4">
                ${details.features
                  .map(
                    (feature) => `
                    <li class="mb-2">
                        <i class="bi bi-check-circle-fill text-success me-2"></i>
                        ${feature}
                    </li>
                `,
                  )
                  .join("")}
            </ul>
            <div class="text-center">
                <button class="btn btn-primary" onclick="window.location.href='/citas'">
                    <i class="bi bi-calendar-check me-2"></i>
                    Reservar Cita
                </button>
            </div>
        `
  }

  function showProductDetails(product) {
    infoModalLabel.textContent = "Detalles del Producto"

    const productDetails = {
      cera: {
        title: "Cera Profesional",
        price: "$25.99",
        stock: "En stock",
        description:
          "Cera de alta calidad para un acabado natural con fijación duradera. Ideal para estilos texturizados.",
        features: ["Fijación media-fuerte", "Acabado natural", "Fácil aplicación", "Larga duración"],
        ingredients: ["Cera de abeja", "Aceites naturales", "Vitamina E"],
      },
      polvo: {
        title: "Polvo Texturizante",
        price: "$32.99",
        stock: "En stock",
        description: "Polvo voluminizador que aporta textura y cuerpo al cabello sin apelmazarlo.",
        features: ["Efecto volumen", "Textura mate", "Sin residuos", "Fácil lavado"],
        ingredients: ["Sílice", "Arcilla bentonita", "Extractos vegetales"],
      },
      gel: {
        title: "Gel Ultra Fijación",
        price: "$18.99",
        stock: "En stock",
        description: "Gel de fijación extrema para peinados que requieren máximo control y duración.",
        features: ["Fijación extrema", "Secado rápido", "Sin alcohol", "Resistente al agua"],
        ingredients: ["Polímeros fijadores", "Glicerina", "Extracto de aloe"],
      },
    }

    const details = productDetails[product] || productDetails["cera"]

    infoModalBody.innerHTML = `
            <div class="text-center mb-4">
                <h4>${details.title}</h4>
                <div class="d-flex justify-content-center gap-4 mb-3">
                    <span class="h4 text-primary">${details.price}</span>
                    <span class="badge bg-success fs-6">${details.stock}</span>
                </div>
            </div>
            <p class="lead text-center mb-4">${details.description}</p>
            <div class="row">
                <div class="col-md-6">
                    <h6>Características:</h6>
                    <ul class="list-unstyled mb-4">
                        ${details.features
                          .map(
                            (feature) => `
                            <li class="mb-2">
                                <i class="bi bi-check-circle-fill text-success me-2"></i>
                                ${feature}
                            </li>
                        `,
                          )
                          .join("")}
                    </ul>
                </div>
                <div class="col-md-6">
                    <h6>Ingredientes principales:</h6>
                    <ul class="list-unstyled mb-4">
                        ${details.ingredients
                          .map(
                            (ingredient) => `
                            <li class="mb-2">
                                <i class="bi bi-dot text-primary me-2"></i>
                                ${ingredient}
                            </li>
                        `,
                          )
                          .join("")}
                    </ul>
                </div>
            </div>
            <div class="text-center">
                <button class="btn btn-primary" onclick="window.location.href='/productos'">
                    <i class="bi bi-cart-plus me-2"></i>
                    Ver en Tienda
                </button>
            </div>
        `
  }
})
