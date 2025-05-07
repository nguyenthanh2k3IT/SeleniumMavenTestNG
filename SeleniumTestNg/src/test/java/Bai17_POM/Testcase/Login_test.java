package Bai17_POM.Testcase;


import Bai17_POM.common.BaseTest;
import Bai17_POM.pages.Login_Pages;
import org.testng.annotations.Test;

public class Login_test extends BaseTest {
    Login_Pages loginPages;

    @Test
    public void loginSuccess(){
        loginPages = new Login_Pages(driver);
        loginPages.loginCRM("admin@example.com","123456");
        loginPages.verifyLoginSuccess();
    }

    @Test
    public void loginFailwithEmailInvalid(){
        loginPages = new Login_Pages(driver);
        loginPages.loginCRM("admin123456@example.com","123456");
        loginPages.verifyLoginSuccess();
    }

}