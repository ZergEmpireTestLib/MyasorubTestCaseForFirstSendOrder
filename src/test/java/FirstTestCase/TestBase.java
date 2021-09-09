package FirstTestCase;

import com.codeborne.selenide.*;
import com.codeborne.selenide.conditions.Visible;
import com.codeborne.selenide.junit.TextReport;
import com.google.sitebricks.client.Web;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.*;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestBase {


    @Before
    public void setUp() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("videoName", "myasorub20.mp4");
        capabilities.setCapability("name", "XYI");
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "91.0");

        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true

        ));
        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(
                    new URL("http://192.168.1.17:8080/wd/hub"),
                    capabilities
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void end() {
        closeWebDriver();
    }

    WebDriver driver;
    @Rule
    public TextReport report = new TextReport();

    @Step("Открываю сайт")
    public void openURL() {
        open("https://myasoroob.ru/");
    }

    @Step("Выбираю рандомный город. Выбор городов реализован через Drop Down")

    public void RestSelect() {

        /*$(By.xpath("//div[contains(@class, \"desktop\")]/div[@class = \"top-rest-select\"]/div[@class = \"current-rest\"]")).shouldBe(visible);*/
        List<SelenideElement> terminalSwitch = elements(By.xpath("//div[contains(@class, \"desktop\")]/div [@class = \"top-rest-select\"]/ul[@class = \"dropdown\"]/li/a"));
        int i = (int) (Math.random() * terminalSwitch.size());
        terminalSwitch.get(i).click();
    }


    @Step("Перехожу в рандомный пункт меню для оформления тестового заказа")
    public void mathRandomHead() {
       /* List<SelenideElement> mathRandomHead = elements(By.xpath("//a[@href= \"/menu/frityur\"]")); *//*and not (contains(@href, "/menu/sousy"*//*
        int i = (int) (Math.random() * mathRandomHead.size());
        mathRandomHead.get(i).click(); */
        $(By.xpath("//a[@href= \"/menu/frityur\"]")).click();
    }

    @Step("Добавляем в корзину карточку товара")
    public void PickRandCards() {
        List<SelenideElement> clickRandomCards = elements(By.xpath("//button[contains(@class, \"add-to-basket\")]"));
        int i = (int) (Math.random() * clickRandomCards.size());
        clickRandomCards.get(i).click();
    }

    @Step("Переходим в корзину")

    public void GoBasket() {
        $(By.xpath("//a[contains(@class, \"btn-basket\")]")).click();
        $x("//a[contains(@class, \"btn-basket\")]").click();
    }

    @Step("Выбираем тип доставки самовывоз")
    public void SelectDeliveryTypePickUp() {
        $(By.xpath("//label[@class = \"last\"]")).click();
    }



    @Step("Выбираем пункт самовывоза")
    public void SelectTerminalForPickUp() {
    /*$(By.id("order_terminal-no-shipment")).selectOption();*/
        List <SelenideElement> options = elements(By.xpath("//select[@id = \"order_terminal-no-shipment\"]/option"));
        int i = (int) (Math.random() * options.size());
        options.get(i).click();
    }

}


