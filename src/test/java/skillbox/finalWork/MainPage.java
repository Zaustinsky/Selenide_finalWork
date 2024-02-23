package skillbox.finalWork;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public SelenideElement menu = $("#menu-item-389");
    public SelenideElement submenu = $(".sub-menu #menu-item-390");
    public SelenideElement search = $(".search-form input");
    public SelenideElement searchButton = $(".search-form button");
    public SelenideElement myAccount = $("#menu-primary-menu #menu-item-30");
    public SelenideElement ordering = $("#menu-primary-menu #menu-item-31");
    public SelenideElement userName = $(".my-account span");
    public SelenideElement action = $("#menu-primary-menu #menu-item-396");
    public SelenideElement quitFromAccount = $(".account");


    public void setSearch(String name){
        search.setValue(name);
    }

}
