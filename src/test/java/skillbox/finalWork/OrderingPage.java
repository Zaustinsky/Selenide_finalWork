package skillbox.finalWork;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class OrderingPage {

    // elements
    public SelenideElement firstNameInput = $("#billing_first_name");
    public SelenideElement lastNameInput = $("#billing_last_name");
    public SelenideElement comboCountrySelect = $(".select2-selection__arrow");
    //public SelenideElement countrySelect = $("span[title='Russia");
    public SelenideElement streetInput = $("#billing_address_1");
    public SelenideElement townInput = $("#billing_city");
    public SelenideElement stateInput = $("#billing_state");
    public SelenideElement postCodeInput = $("#billing_postcode");
    public SelenideElement phoneNumberInput = $("#billing_phone");
    public SelenideElement calendarInput = $("#order_date");
    public SelenideElement commentsInput = $("#order_comments");
    public SelenideElement paymentsMethodInput = $("#payment_method_cod");
    public SelenideElement checkBoxInput = $("#terms");
    public SelenideElement checkoutButton = $("#place_order");
    public SelenideElement verifyOrder = $(".woocommerce-notice.woocommerce-notice--success.woocommerce-thankyou-order-received");
    public SelenideElement verifyEmail = $(".woocommerce-order-overview__email.email strong");
    public SelenideElement verifyTotalSumm = $(".woocommerce-order-overview__total.total bdi");





    // actions
    //@Step("ввод анкетных данных пользователя {firstName}")
    public void addOrderingList(String firstName, String lastName, String street, String town,
                                String state, String postcode, String phoneNumber, String date, String comments) {

        firstNameInput.setValue(firstName);
        lastNameInput.setValue(lastName);
        comboCountrySelect.click();
        //countrySelect.setValue(country);
        streetInput.click();
        streetInput.setValue(street);
        townInput.click();
        townInput.setValue(town);
        stateInput.click();
        stateInput.setValue(state);
        postCodeInput.click();
        postCodeInput.setValue(postcode);
        phoneNumberInput.click();
        phoneNumberInput.setValue(phoneNumber);
        calendarInput.setValue(String.valueOf(date));
        commentsInput.click();
        commentsInput.setValue(comments);
        paymentsMethodInput.click();
        checkBoxInput.click();
        checkoutButton.click();
    }

}
