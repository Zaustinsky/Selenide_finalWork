package skillbox.finalWork;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BasketPage {

    public SelenideElement pizza = $("tbody tr:nth-of-type(1) .product-name a");
    public SelenideElement cacao = $("tbody tr:nth-of-type(2) .product-name a");
    public SelenideElement addAmountPizza = $("tbody tr:nth-of-type(1) input[inputmode='numeric']");
    public SelenideElement addAmountCacao = $("tbody tr:nth-of-type(2) input[inputmode='numeric']");
    public SelenideElement updateBasketButton = $("button[value='Обновить корзину']");
    public SelenideElement totalSumm = $(".order-total bdi");
    public SelenideElement subtotalSumm = $(".cart-subtotal bdi");
    SelenideElement inputCouponCode = $("#coupon_code");
    SelenideElement saleCoupon = $("button[name='apply_coupon']");
    SelenideElement verifyCoupon = $(".cart-discount.coupon-givemehalyava .woocommerce-Price-amount.amount");
    public SelenideElement errorMessage = $("ul.woocommerce-error li");




    public void addSaleCoupon(String name){
        inputCouponCode.setValue(name);
        saleCoupon.click();
    }

    public void verifySaleCoupon(){
        verifyCoupon.shouldBe(Condition.visible).shouldHave(Condition.text("45,00₽"));
    }

    public void verifySaleCouponTwice(){
        verifyCoupon.shouldNotBe(Condition.visible).shouldNotHave(Condition.text("45,00₽"));
    }

}
