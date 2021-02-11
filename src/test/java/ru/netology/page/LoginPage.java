package ru.netology.page;

import com.codeborne.selenide.Condition;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public VerificationPage validLogin() {
        $("[data-test-id=login] input").setValue(DataGenerator.generateUserInfo().getLogin());
        $("[data-test-id=password] input").setValue(DataGenerator.generateUserInfo().getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }

    public LoginPage generateInvalidLogin() {
        $("[data-test-id=login] input").setValue(DataGenerator.getInvalidUserInfo().getLogin());
        $("[data-test-id=password] input").setValue(DataGenerator.generateUserInfo().getPassword());
        $("[data-test-id=action-login]").click();
        return new LoginPage();

    }

    public void blockNotification() {
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Система заблокирована"));
    }
}
