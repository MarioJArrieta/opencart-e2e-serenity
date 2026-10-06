package com.marioarrieta.qa.opencart.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * El checkout de OpenCart es un wizard de un solo acordeón (#accordion) cuyos
 * pasos se cargan por AJAX dentro del mismo DOM (no hay navegación de página
 * entre pasos). Por eso cada paso espera explícitamente a que el elemento del
 * SIGUIENTE paso aparezca, en vez de confiar en un timeout fijo.
 *
 * Selectores e IDs verificados contra http://opencart.abstracta.us/ antes de
 * escribir este código (ver conclusiones.txt).
 *
 * Los botones del wizard se clickean vía JavaScript (en vez de
 * WebElement.click()) porque el stack Bitnami de este sitio demo inyecta una
 * insignia fija ("bitnami-corner-image") que, según el alto de cada paso del
 * acordeón, puede quedar superpuesta sobre el botón y provocar
 * ElementClickInterceptedException -- un hallazgo real del sitio, no un
 * problema del test (ver conclusiones.txt).
 */
public class CheckoutPage extends PageObject {

    private WebDriverWait webDriverWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(25));
    }

    private void clickViaJs(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    // ---- Paso 1: opción de checkout ----
    public void selectGuestCheckout() {
        WebElement guestRadio = webDriverWait().until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='account'][value='guest']"))
        );
        clickViaJs(guestRadio);

        WebElement accountButton = webDriverWait().until(
            ExpectedConditions.elementToBeClickable(By.id("button-account"))
        );
        clickViaJs(accountButton);
    }

    // ---- Paso 2: datos de facturación (invitado) ----
    public void fillGuestBillingDetails(String firstName, String lastName, String email,
                                         String phone, String address, String city, String postcode) {
        webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname")))
            .sendKeys(firstName);
        getDriver().findElement(By.id("input-payment-lastname")).sendKeys(lastName);
        getDriver().findElement(By.id("input-payment-email")).sendKeys(email);
        getDriver().findElement(By.id("input-payment-telephone")).sendKeys(phone);
        getDriver().findElement(By.id("input-payment-address-1")).sendKeys(address);
        getDriver().findElement(By.id("input-payment-city")).sendKeys(city);
        getDriver().findElement(By.id("input-payment-postcode")).sendKeys(postcode);

        Select country = new Select(getDriver().findElement(By.id("input-payment-country")));
        country.selectByVisibleText("United States");

        // La lista de "Region / State" se recarga por AJAX según el país elegido.
        WebElement zoneSelect = webDriverWait().until(
            ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-zone"))
        );
        webDriverWait().until(driver -> new Select(zoneSelect).getOptions().size() > 1);
        new Select(zoneSelect).selectByIndex(1);

        WebElement guestButton = webDriverWait().until(
            ExpectedConditions.elementToBeClickable(By.id("button-guest"))
        );
        clickViaJs(guestButton);
    }

    // ---- Paso 4: método de envío ----
    public void selectStandardShippingMethod() {
        WebElement shippingRadio = webDriverWait().until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='shipping_method'][value='flat.flat']"))
        );
        clickViaJs(shippingRadio);

        WebElement shippingButton = webDriverWait().until(
            ExpectedConditions.elementToBeClickable(By.id("button-shipping-method"))
        );
        clickViaJs(shippingButton);
    }

    // ---- Paso 5: método de pago ----
    public void selectCashOnDeliveryAndAcceptTerms() {
        WebElement paymentRadio = webDriverWait().until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='payment_method'][value='cod']"))
        );
        clickViaJs(paymentRadio);

        WebElement agreeCheckbox = webDriverWait().until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='agree']"))
        );
        clickViaJs(agreeCheckbox);

        WebElement paymentButton = webDriverWait().until(
            ExpectedConditions.elementToBeClickable(By.id("button-payment-method"))
        );
        clickViaJs(paymentButton);
    }

    // ---- Paso 6: confirmar pedido ----
    public void confirmOrder() {
        WebElement confirmButton = webDriverWait().until(
            ExpectedConditions.elementToBeClickable(By.id("button-confirm"))
        );
        clickViaJs(confirmButton);
    }
}
