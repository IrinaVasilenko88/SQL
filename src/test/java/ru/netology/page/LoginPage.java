package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import ru.netology.data.UserInfo;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton =  $("[data-test-id=action-login]");

    public VerificationPage validLogin(UserInfo userInfo) {
        loginField.setValue(DataGenerator.generateUserInfo().getLogin());
        passwordField.setValue(DataGenerator.generateUserInfo().getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public LoginPage generateInvalidLogin(UserInfo userInfo) {
        loginField.setValue(DataGenerator.getInvalidUserInfo().getLogin());
        passwordField.setValue(DataGenerator.getInvalidUserInfo().getPassword());
        loginButton.click();
        return new LoginPage();

    }

    public void blockNotification() {
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Система заблокирована"));
    }

    public void cleanFields(UserInfo userInfo){
        loginField.doubleClick().sendKeys(Keys.BACK_SPACE);
        passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
    }
}
