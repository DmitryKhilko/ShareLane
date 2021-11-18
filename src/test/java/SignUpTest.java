import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest {
    //Путь к chromedriver.exe
    public String pathChromedriver = "src/test/resources/chromedriver.exe";
    //Адрес страницы регистрации
    public String pageRegistration = "https://www.sharelane.com/cgi-bin/register.py";

    //xpath-запрос, который находит на странице регистрации поле ввода "ZIP code"
    public String xpathZipCode = "//input[@name='zip_code']";
    //xpath-запрос, который находит на странице регистрации кнопку "Continue"
    public String xpathcmdContinue = "//input[@value='Continue']";
    //xpath-запрос, который находит на странице регистрации сообщение об ошибке "Oops, error on page. ZIP code should have 5 digits"
    public String xpathmsgError = "//span[@class = 'error_message']";
    //xpath-запрос, который находит на странице регистрации кнопку "Register"
    public String xpathcmdRegister = "//input[@value='Register']";

    //Позитивный тест на ввод в поле "Zip code" 5 цифр
    @Test
    public void zipCodeShoulAccert5Digits() {

        //Значение, которое необходимо ввести в поле ввода "ZIP code"
        String valueZipCode = "12345";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py
        driver.get(pageRegistration);

        //Ввести в поле ввода "ZIP code" 5 цифр
        WebElement inputZipCode = driver.findElement(By.xpath(xpathZipCode));
        inputZipCode.sendKeys(valueZipCode);

        //Нажать кнопку Continue
        WebElement cmdContinue = driver.findElement(By.xpath(xpathcmdContinue));
        cmdContinue.click();

        //Убедится, что перешли на страницу ввода регистрационных данных пользователя:
        // на странице есть кнопка "Register"
        boolean isDisplaed = driver.findElement(By.xpath(xpathcmdRegister)).isDisplayed();
        Assert.assertTrue(isDisplaed);

        //Закрыть браузер
        driver.quit();
    }

    //Негативный тест на пустое поле "Zip code"
    //На странице должно отобразится сообщение "Oops, error on page. ZIP code should have 5 digits"
    @Test
    public void zipCodeShoulAccertNoDigits() {

        //Значение, которое необходимо ввести в поле ввода "ZIP code"
        String valueZipCode = "";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py
        driver.get(pageRegistration);

        //Ввести в поле ввода "ZIP code" 4 цифры
        WebElement inputZipCode = driver.findElement(By.xpath(xpathZipCode));
        inputZipCode.sendKeys(valueZipCode);

        //Нажать кнопку Continue
        WebElement cmdContinue = driver.findElement(By.xpath(xpathcmdContinue));
        cmdContinue.click();

        //Убедится, что на странице отобразилось сообщение об ошибке о том, что необходимо ввести 5 чисел
        boolean isDisplaed = driver.findElement(By.xpath(xpathmsgError)).isDisplayed();
        Assert.assertTrue(isDisplaed, "На странице не отобразилось сообщение об ошибке ввода");

        //Закрыть браузер
        driver.quit();
    }

    //Негативный тест на ввод в поле "Zip code" 4 цифр
    //На странице должно отобразится сообщение "Oops, error on page. ZIP code should have 5 digits"
    @Test
    public void zipCodeShoulAccert4Digits() {

        //Значение, которое необходимо ввести в поле ввода "ZIP code"
        String valueZipCode = "1234";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py
        driver.get(pageRegistration);

        //Ввести в поле ввода "ZIP code" 4 цифры
        WebElement inputZipCode = driver.findElement(By.xpath(xpathZipCode));
        inputZipCode.sendKeys(valueZipCode);

        //Нажать кнопку Continue
        WebElement cmdContinue = driver.findElement(By.xpath(xpathcmdContinue));
        cmdContinue.click();

        //Убедится, что на странице отобразилось сообщение об ошибке о том, что необходимо ввести 5 чисел
        boolean isDisplaed = driver.findElement(By.xpath(xpathmsgError)).isDisplayed();
        Assert.assertTrue(isDisplaed, "На странице не отобразилось сообщение об ошибке ввода");

        //Закрыть браузер
        driver.quit();
    }

    //Негативный тест на ввод в поле "Zip code" 6 цифр
    //На странице должно отобразится сообщение "Oops, error on page. ZIP code should have 5 digits"
    @Test
    public void zipCodeShoulAccert6Digits() {

        //Значение, которое необходимо ввести в поле ввода "ZIP code"
        String valueZipCode = "123456";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py
        driver.get(pageRegistration);

        //Ввести в поле ввода "ZIP code" 6 цифр
        WebElement inputZipCode = driver.findElement(By.xpath(xpathZipCode));
        inputZipCode.sendKeys(valueZipCode);

        //Нажать кнопку Continue
        WebElement cmdContinue = driver.findElement(By.xpath(xpathcmdContinue));
        cmdContinue.click();

        //Убедится, что на странице отобразилось сообщение об ошибке о том, что необходимо ввести 5 чисел
        boolean isDisplaed = driver.findElement(By.xpath(xpathmsgError)).isDisplayed();
        Assert.assertTrue(isDisplaed, "На странице не отобразилось сообщение об ошибке ввода");

        //Закрыть браузер
        driver.quit();
    }
}
