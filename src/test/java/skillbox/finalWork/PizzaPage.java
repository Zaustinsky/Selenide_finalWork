package skillbox.finalWork;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PizzaPage {

    public SelenideElement pizza = $(".products.columns-4 li:nth-of-type(2) .button");
    public SelenideElement addCacaoToBasket = $("form.cart button[type='submit']");
    public SelenideElement priceCacao = $(".woocommerce-Price-amount.amount");
    public SelenideElement detailButton = $(".woocommerce-message a");
    public SelenideElement detailButton2 = $(".products.columns-4 li:nth-of-type(2) a[title='Подробнее']");
}
