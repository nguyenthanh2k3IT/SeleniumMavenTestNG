package Bai17_POM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Login_Pages {
    private WebDriver webDriver;

    //Khai báo driver cục bộ trong chính class này
    private WebDriver driver;
    private WebDriverWait wait;

    //Khai báo các element dạng đối tượng By (phương thức tìm kiếm)
    private By headerPage = By.xpath("//h1[normalize-space()='Login']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[@id='alerts']");

    //Khai báo hàm xây dựng, để truyền driver từ bên ngoài vào chính class này sử dụng
    public Login_Pages(WebDriver driver) {
        this.driver = driver;
        //driver = _driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private void setEmail(String email) {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmail));
        driver.findElement(inputEmail).sendKeys(email);
    }

    private void setPassword(String password) {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(inputPassword));
        driver.findElement(inputPassword).sendKeys(password);
    }

    private void clickLoginButton() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(buttonLogin));
        driver.findElement(buttonLogin).click();
    }

    public void verifyLoginSuccess() {
        Assert.assertFalse(driver.getCurrentUrl().contains("authentication"), "FAIL. Vẫn đang ở trang Login");
    }

    public void verifyLoginFail() {
        Assert.assertTrue(driver.getCurrentUrl().contains("authentication"), "FAIL. Không còn ở trang Login");
        Assert.assertTrue(driver.findElement(errorMessage).isDisplayed(), "Error message NOT displays");
        Assert.assertEquals(driver.findElement(errorMessage).getText(), "Invalid email or password", "Content of error massage NOT match.");
    }

    //Các hàm xử lý cho chính trang này
    public void loginCRM(String email, String password) {
        //https://crm.anhtester.com/admin/authentication
        driver.get("https://crm.anhtester.com/admin/authentication"); //Gọi từ class ConfigData dạng biến static
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }
}
