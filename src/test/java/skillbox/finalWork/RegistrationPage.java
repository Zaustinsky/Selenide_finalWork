package skillbox.finalWork;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    //elements
    SelenideElement usernameInput = $("#reg_username");
    SelenideElement emailInput = $("#reg_email");
    SelenideElement passwordInput = $("#reg_password");
    public SelenideElement registerButton = $("button.custom-register-button");
    public SelenideElement registerButton2 = $("button[name='register']");

    //actions
    // @Step("регистрация пользователя")
    public void register(String username, String email, String password){
        usernameInput.setValue(username);
        emailInput.setValue(email);
        passwordInput.setValue(password);
        registerButton2.click();
    }


}
