package com.marioarrieta.qa.opencart.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;

public class CartPage extends PageObject {

    private static final String URL = "http://opencart.abstracta.us/index.php?route=checkout/cart";
    // La tabla de items reales vive dentro del form de "checkout/cart/edit";
    // el resto de tablas de #content (subtotal, totales) no deben contarse.
    private static final By CART_ROWS = By.cssSelector("form[action*='cart/edit'] table tbody tr");
    private static final By CHECKOUT_LINK = By.cssSelector("a[href*='route=checkout/checkout']");

    public void openCartPage() {
        getDriver().get(URL);
    }

    public int itemCount() {
        List<?> rows = getDriver().findElements(CART_ROWS);
        return rows.size();
    }

    public void goToCheckout() {
        getDriver().findElements(CHECKOUT_LINK).get(0).click();
    }
}
