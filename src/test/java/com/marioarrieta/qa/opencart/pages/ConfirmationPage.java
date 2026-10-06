package com.marioarrieta.qa.opencart.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmationPage extends PageObject {

    public String getConfirmationHeading() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        // "Confirm Order" dispara un POST AJAX (checkout/confirm/save) que, si
        // tiene éxito, redirige por JS a checkout/success -- hay que esperar
        // a que esa navegación ocurra antes de leer el <h1>, o se puede leer
        // el de la pantalla anterior ("Checkout") por una condición de carrera.
        wait.until(ExpectedConditions.urlContains("route=checkout/success"));

        // El theme de OpenCart usa un <h1> para el logo del header ("Your Store")
        // ANTES que el <h1> real de contenido -- hay que acotar a #content.
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content h1")))
            .getText();
    }
}
