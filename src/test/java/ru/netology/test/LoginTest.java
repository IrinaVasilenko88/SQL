package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DbInteractionDbUtils;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    @BeforeEach
    void setUp() throws SQLException {
        open("http://localhost:9999");
    }

    @Test
    void shouldLogin() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogin();
        DashboardPage dashboardPage = verificationPage.validVerify(DbInteractionDbUtils.getVerificationCode());
        dashboardPage.getHeading();
    }

    @Test
    void shouldBlock() {
        val loginPage = new LoginPage();
        loginPage.generateInvalidLogin();
        loginPage.generateInvalidLogin();
        loginPage.generateInvalidLogin();
        loginPage.blockNotification();
    }
    @AfterAll
    void cleanUp() throws SQLException{
        DbInteractionDbUtils.clearTables();
    }
}


