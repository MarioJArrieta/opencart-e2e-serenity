package com.marioarrieta.qa.opencart.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Página de detalle de producto. "Add to Cart" dispara una llamada AJAX
 * (checkout/cart/add) que actualiza el contador del carrito sin recargar la
 * página -- por eso la espera es sobre el mensaje de éxito, no sobre
 * navegación.
 */
public class ProductPage extends PageObject {

    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(25));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button-cart"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
    }
}
