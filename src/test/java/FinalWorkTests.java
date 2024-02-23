import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.DesiredCapabilities;
import skillbox.finalWork.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;


@Epic("Тесты на функционал заказа пиццы на сайте интернет-магазина")
@Feature("Добавление заказа в корзину пользователем, с последующей регистрацией пользователя")
@Story("Разделение тестов на основной: (тест 1) и 2 фичи (тесты 2 и 3)")
@DisplayName("Тест заказа пиццы, регистрация пользователя, проверки введенной информации + тест 2-х фич")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FinalWorkTests {

    @BeforeAll
    @DisplayName("Инициализируем плагин Allure-Selenide")
    static void init(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        //подключение сервера Selenoid для запуска тестов на удаленном компьютере
        Configuration.remote="http://85.119.145.3:4444/wd/hub";
        Configuration.browser="firefox";
        Configuration.browserVersion="93.0";

        //задание опций Selenoid для возможности записи и просмотра видео запускаемых тестов
        var caps = new DesiredCapabilities();
        var options = Map.of("enableVnc", true, "enableVideo", true);
        caps.setCapability("selenoid:options", options);
        Configuration.browserCapabilities = caps;
        }
    public static void setupBrowser() {
            Configuration.browser = "firefox";
        }

        @Test
        @DisplayName("Добавление заказа в корзину, с последующей регистрацией пользователя")
        @Description(value = """
            1. Искать товар и положить в корзину
            2. Добавить еще по одной позиции к каждому пункту заказа
            3. Зарегистрировать нового пользователя
            4. Проверить, что товар в корзине
            5. Оформить заказ
            6. Проверить данные заказа""")
        public void orderingPizza() {

           //arrange
           setupBrowser();
           open("http://pizzeria.skillbox.cc/"); //переход на страницу сайта
           clearBrowserCookies();

           //act
           new MainPage().menu.shouldHave(Condition.visible).hover();
           new MainPage().submenu.click();
           new PizzaPage().pizza.click();
           new MainPage().setSearch("Какао");
           new MainPage().searchButton.click();
           //assert
           new PizzaPage().addCacaoToBasket.shouldHave(Condition.visible).click();
           new PizzaPage().priceCacao.shouldHave(Condition.text("300,00₽"));
           new PizzaPage().detailButton.click();
           new BasketPage().pizza.shouldHave(Condition.text("Пицца \"Ветчина и грибы"));
           new BasketPage().cacao.shouldHave(Condition.text("Напиток \"Какао с маршмеллоу"));
           //act
           new BasketPage().addAmountPizza.setValue("2");
           new BasketPage().addAmountCacao.setValue("2");
           new BasketPage().updateBasketButton.click();
           //assert
           // проверка, что заказы увеличились на +1
           new BasketPage().addAmountPizza.shouldHave(Condition.value("2"));
           new BasketPage().addAmountCacao.shouldHave(Condition.value("2"));
           new BasketPage().subtotalSumm.shouldHave(Condition.text("1500"));
           new MainPage().myAccount.hover().click();
           new RegistrationPage().registerButton.scrollTo().click();
            //act
            //генерация нового пользователя
            var username = "sel" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMhhHHmmss"));
            var email = username + "@ya.ru";
            new RegistrationPage().register(username, email, "Selenide123");
            new MainPage().userName.shouldHave(Condition.text(username));
            new MainPage().ordering.click();
            new OrderingPage().addOrderingList("Дмитрий", "Заустинский", "Надежденская, 5", "Минск",
                    "Минская", "222161", "+37529777777", "20.02.2024", "Доставьте завтра");
            //assert
            new OrderingPage().verifyOrder.shouldBe(Condition.visible).shouldHave(Condition.text("Спасибо! Ваш заказ был получен."));
            new OrderingPage().verifyEmail.shouldBe(Condition.visible).shouldHave(Condition.text(email));
            new OrderingPage().verifyTotalSumm.shouldBe(Condition.visible).shouldHave(Condition.text("1500,00₽"));

           sleep(5000);
           closeWebDriver();


       }

    @Test
    @DisplayName("Фича проверки работоспособности скидки на сайте")
    @Description(value = """
            1. Искать товар и положить в корзину
            2. Перейти в корзину и ввести данные скидочного купона
            3. Убедиться, что общая сумма уменьшилась на 10%""")
    public void useSaleCoupon() {

        //arrange
        setupBrowser();
        open("http://pizzeria.skillbox.cc/"); //переход на страницу сайта
        clearBrowserCookies();

        //act
        new MainPage().menu.shouldHave(Condition.visible).hover();
        new MainPage().submenu.click();
        new PizzaPage().pizza.click();
        new PizzaPage().detailButton2.click();
        new BasketPage().addSaleCoupon("GIVEMEHALYAVA");
        new BasketPage().verifySaleCoupon();


        sleep(5000);
        closeWebDriver();
    }

    @Test
    @DisplayName("Фича проверки ввода некорректных данных по скидке")
    @Description(value = """
            1. Искать товар и положить в корзину
            2. Перейти в корзину и ввести некорректные данные по скидочному купону 
            3. Убедиться, что высветилось сообщение об ошибке и сумма заказа не изменилась""")
    public void useIncorrectSaleCoupon() {

        //arrange
        setupBrowser();
        open("http://pizzeria.skillbox.cc/"); //переход на страницу сайта
        clearBrowserCookies();

        //act
        new MainPage().menu.shouldHave(Condition.visible).hover();
        new MainPage().submenu.click();
        new PizzaPage().pizza.click();
        new PizzaPage().detailButton2.click();
        new BasketPage().addSaleCoupon("NOCOUPON");
        new BasketPage().errorMessage.shouldBe(Condition.visible).shouldHave(Condition.text("Неверный купон."));
        new BasketPage().totalSumm.shouldHave(Condition.visible).shouldHave(Condition.text("450,00"));
//        new MainPage().quitFromAccount.click();


        sleep(5000);
        closeWebDriver();

    }

    @Test
    @DisplayName("Фича проверки ввода данных скидочного купона несколько раз")
    @TmsLink("188046")
    @Issue("issues/188046")
    @Link(url="http://pizzeria.skillbox.cc/checkout/", name = "Сайт по заказу пиццы")
    @Description(value = """
            1. Перейти в меню "Акции" и проверить наличие скидочного купона
            2. Создать нового пользователя
            3. Сделать первый заказ с промокодом GIVEMEHALYAVA на странице «Акции»
            4. Сделать второй заказ с этим же промокодом
            5. Проверить, что промокод второй раз не сработал""")
    public void useSaleCouponMoreThanOnce() {

        //arrange
        setupBrowser();
        open("http://pizzeria.skillbox.cc/"); //переход на страницу сайта
        clearBrowserCookies();

        //act
        new MainPage().action.click();
        new ActionPage().verifyActionText.shouldBe(Condition.visible).shouldHave(Condition.text("GIVEMEHALYAVA"));
        new MainPage().myAccount.hover().click();
        new RegistrationPage().registerButton.scrollTo().click();

        var username = "sel" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMhhHHmmss"));
        var email = username + "@ya.ru";
        new RegistrationPage().register(username, email, "Selenide123");

        new MainPage().menu.shouldHave(Condition.visible).hover();
        new MainPage().submenu.click();
        new PizzaPage().pizza.click();
        new PizzaPage().detailButton2.click();
        new BasketPage().addSaleCoupon("GIVEMEHALYAVA");
        new BasketPage().verifySaleCoupon();
        new MainPage().ordering.click();
        new OrderingPage().addOrderingList("Дмитрий", "Заустинский", "Надежденская, 5", "Минск",
                "Минская", "222161", "+37529777777", "20.02.2024", "Доставьте завтра");
        //assert
        new OrderingPage().verifyOrder.shouldBe(Condition.visible).shouldHave(Condition.text("Спасибо! Ваш заказ был получен."));
        new OrderingPage().verifyEmail.shouldBe(Condition.visible).shouldHave(Condition.text(email));

        new MainPage().menu.shouldHave(Condition.visible).hover();
        new MainPage().submenu.click();
        new PizzaPage().pizza.click();
        new PizzaPage().detailButton2.click();
        new BasketPage().addSaleCoupon("GIVEMEHALYAVA");
        new BasketPage().verifySaleCouponTwice();



        sleep(5000);
        closeWebDriver();

    }

}
