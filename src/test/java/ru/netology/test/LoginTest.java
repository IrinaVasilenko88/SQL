package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DbInteractionDbUtils;
import ru.netology.data.UserInfo;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;



import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    UserInfo userInfo = new UserInfo("vasya", "qwerty123");

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldLogin() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogin(userInfo);
        DashboardPage dashboardPage = verificationPage.validVerify(DbInteractionDbUtils.getVerificationCode());

    }

    @Test
    void shouldBlock() {
        val loginPage = new LoginPage();
        loginPage.generateInvalidLogin(userInfo);
        loginPage.cleanFields(userInfo);
        loginPage.generateInvalidLogin(userInfo);
        loginPage.cleanFields(userInfo);
        loginPage.generateInvalidLogin(userInfo);
        loginPage.blockNotification();
    }
    @AfterAll
    static void cleanUp() {
        DbInteractionDbUtils.clearTables();
    }
}


