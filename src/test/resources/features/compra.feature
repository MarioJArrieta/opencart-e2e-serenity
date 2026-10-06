# language: es
Característica: Compra como invitado en OpenCart
  Como clienta de la tienda OpenCart
  Quiero poder agregar productos al carrito y comprar como invitado
  Para completar mi pedido sin necesidad de crear una cuenta

  Escenario: Comprar dos productos y confirmar el pedido como invitado
    Dado que Laura ingresa a la tienda OpenCart
    Cuando agrega el producto "MacBook" al carrito
    Y agrega el producto "iPhone" al carrito
    Y visualiza el carrito de compras
    Entonces el carrito debe contener 2 producto(s)
    Cuando continúa al checkout
    Y selecciona la opción de compra como invitado
    Y completa los datos de facturación como invitado
    Y selecciona el método de envío estándar
    Y selecciona el método de pago contra entrega y acepta los términos y condiciones
    Y confirma el pedido
    Entonces debería ver el mensaje "Your order has been placed!"
