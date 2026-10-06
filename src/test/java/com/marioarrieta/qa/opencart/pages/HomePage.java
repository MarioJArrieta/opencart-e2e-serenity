package com.marioarrieta.qa.opencart.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

/**
 * Home de la tienda. Los productos del catálogo demo de OpenCart tienen un
 * product_id fijo (verificado contra el sitio real): MacBook=43, iPhone=40.
 *
 * Nota: se navega con open(URL) explícito en vez de @DefaultUrl porque las
 * page objects inyectadas con @Steps quedan envueltas en un proxy de
 * ByteBuddy que no siempre resuelve las anotaciones de clase -- open(URL)
 * no depende de esa resolución.
 */
public class HomePage extends PageObject {

    private static final String URL = "http://opencart.abstracta.us/";

    private static final Map<String, String> PRODUCT_IDS = Map.of(
        "MacBook", "43",
        "iPhone", "40"
    );

    public void openHomePage() {
        getDriver().get(URL);
        acceptCookieBannerIfPresent();
    }

    private void acceptCookieBannerIfPresent() {
        // El sitio demo de Abstracta no siempre muestra banner de cookies,
        // pero si aparece algún overlay bloqueante, esto evita que tape los clics.
        if (getDriver().findElements(By.cssSelector(".modal.in .close")).size() > 0) {
            findBy(".modal.in .close").click();
        }
    }

    public WebElement productLink(String productName) {
        String productId = PRODUCT_IDS.get(productName);
        if (productId == null) {
            throw new IllegalArgumentException("Producto no reconocido en el catálogo demo: " + productName);
        }
        return getDriver().findElement(By.cssSelector("a[href*='product_id=" + productId + "']"));
    }

    public void goToProduct(String productName) {
        productLink(productName).click();
    }
}
