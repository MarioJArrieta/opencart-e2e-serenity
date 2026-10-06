PRUEBA E2E AUTOMATIZADA - OPENCART (http://opencart.abstracta.us/)
====================================================================

Automatiza el flujo de compra como invitado descrito en el ejercicio:
  1. Agregar dos productos al carrito (MacBook e iPhone)
  2. Visualizar el carrito
  3. Completar el checkout como invitado ("Guest Checkout")
  4. Finalizar la compra hasta ver "Your order has been placed!"

Stack: Java 17 + Maven + Serenity BDD + Cucumber + Selenium WebDriver 4
(Chrome headless). Patron de diseño: Page Object clasico.


REQUISITOS
-----------
- Java 17 o superior (JAVA_HOME configurado)
- Maven 3.9+
- Google Chrome instalado (Selenium Manager descarga el chromedriver
  compatible automaticamente, no hace falta instalarlo a mano)
- Conexion a internet (el test corre contra el sitio demo publico
  http://opencart.abstracta.us/, no hay mocks ni servidor local)


COMO EJECUTAR
--------------
Desde la raiz del proyecto:

    mvn test

Esto:
  - Compila el proyecto
  - Corre el escenario de Cucumber contra Chrome en modo headless
  - Genera el reporte de Serenity en target/site/serenity/ (tambien
    queda una copia ya generada en la carpeta reporte/ de este repo,
    por si se quiere ver sin volver a ejecutar nada)

Para ver el reporte generado, abrir en el navegador:

    reporte/index.html

(o target/site/serenity/index.html despues de correr mvn test)

Tiempo aproximado de ejecucion: 25-40 segundos (el sitio demo es
publico y compartido, la latencia de cada paso del checkout varia).


MODO NO-HEADLESS (ver el navegador mientras corre)
----------------------------------------------------
Editar src/test/resources/serenity.conf y quitar la linea
"--headless=new;" del bloque "chrome { switches = ... }".


ESTRUCTURA DEL PROYECTO
-------------------------
src/test/resources/features/compra.feature
    Escenario en Gherkin (español) que describe el flujo de negocio.

src/test/java/.../stepdefinitions/CompraStepDefinitions.java
    Conecta cada paso del feature con las Page Objects.

src/test/java/.../pages/
    HomePage, ProductPage, CartPage, CheckoutPage, ConfirmationPage
    (Page Object clasico, un objeto por pantalla/paso del flujo).

src/test/java/.../runners/CucumberTestSuite.java
    Runner JUnit4 + CucumberWithSerenity.

src/test/resources/serenity.conf
    Configuracion del WebDriver (Chrome headless) y reportes.

reporte/
    Copia ya generada del reporte HTML de Serenity (ultima corrida
    exitosa), para revisar sin tener que ejecutar el proyecto.

conclusiones.txt
    Hallazgos y decisiones tecnicas tomadas durante el ejercicio.
