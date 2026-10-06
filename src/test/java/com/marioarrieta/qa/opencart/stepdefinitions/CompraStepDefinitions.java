package com.marioarrieta.qa.opencart.stepdefinitions;

import com.marioarrieta.qa.opencart.pages.CartPage;
import com.marioarrieta.qa.opencart.pages.CheckoutPage;
import com.marioarrieta.qa.opencart.pages.ConfirmationPage;
import com.marioarrieta.qa.opencart.pages.HomePage;
import com.marioarrieta.qa.opencart.pages.ProductPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.WithTag;

import static org.assertj.core.api.Assertions.assertThat;

@WithTag("Ejercicio 1 - OpenCart E2E")
public class CompraStepDefinitions {

    @Steps
    HomePage homePage;

    @Steps
    ProductPage productPage;

    @Steps
    CartPage cartPage;

    @Steps
    CheckoutPage checkoutPage;

    @Steps
    ConfirmationPage confirmationPage;

    @Dado("que Laura ingresa a la tienda OpenCart")
    public void laura_ingresa_a_la_tienda() {
        homePage.openHomePage();
    }

    @Cuando("agrega el producto {string} al carrito")
    public void agrega_el_producto_al_carrito(String producto) {
        homePage.goToProduct(producto);
        productPage.addToCart();
        homePage.openHomePage();
    }

    @Y("visualiza el carrito de compras")
    public void visualiza_el_carrito_de_compras() {
        cartPage.openCartPage();
    }

    @Entonces("el carrito debe contener {int} producto\\(s)")
    public void el_carrito_debe_contener_productos(int cantidadEsperada) {
        assertThat(cartPage.itemCount()).isEqualTo(cantidadEsperada);
    }

    @Cuando("continúa al checkout")
    public void continua_al_checkout() {
        cartPage.goToCheckout();
    }

    @Y("selecciona la opción de compra como invitado")
    public void selecciona_compra_como_invitado() {
        checkoutPage.selectGuestCheckout();
    }

    @Y("completa los datos de facturación como invitado")
    public void completa_datos_de_facturacion() {
        checkoutPage.fillGuestBillingDetails(
            "Laura",
            "Gomez",
            "laura.qa.automation@example.com",
            "3000000000",
            "Calle 10 # 20-30",
            "Bogota",
            "110111"
        );
    }

    @Y("selecciona el método de envío estándar")
    public void selecciona_metodo_de_envio_estandar() {
        checkoutPage.selectStandardShippingMethod();
    }

    @Y("selecciona el método de pago contra entrega y acepta los términos y condiciones")
    public void selecciona_metodo_de_pago_y_acepta_terminos() {
        checkoutPage.selectCashOnDeliveryAndAcceptTerms();
    }

    @Y("confirma el pedido")
    public void confirma_el_pedido() {
        checkoutPage.confirmOrder();
    }

    @Entonces("debería ver el mensaje {string}")
    public void deberia_ver_el_mensaje(String mensajeEsperado) {
        assertThat(confirmationPage.getConfirmationHeading()).isEqualTo(mensajeEsperado);
    }
}
