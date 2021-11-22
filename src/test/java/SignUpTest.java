import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest {

    //Путь к chromedriver.exe
    public String pathChromedriver = "src/test/resources/chromedriver.exe";

    //*****************************************************************************************************************
    //Адреса web-страниц
    //*****************************************************************************************************************
    //Адрес страницы регистрации (ввод Zip-кода)
    public String pageZipRegistration = "https://www.sharelane.com/cgi-bin/register.py";
    //Адрес страницы регистрации (ввод ФИО, email, пароля)
    public String pageRegistration = "https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345";

    //*****************************************************************************************************************
    //Web-элементы на странице pageZipRegistration и pageRegistration
    //*****************************************************************************************************************
    //xpath-запрос, который находит на странице регистрации поле ввода "ZIP code"
    public String xpathZipCode = "//input[@name='zip_code']";
    //xpath-запрос, который находит на странице регистрации кнопку "Continue"
    public String xpathcmdContinue = "//input[@value='Continue']";
    //xpath-запрос, который находит на странице регистрации сообщение об ошибке "Oops, error on page. ZIP code should have 5 digits"
    public String xpathmsgZipError = "//span[text()='Oops, error on page. ZIP code should have 5 digits']";

    //xpath-запрос, который находит на странице регистрации поле ввода "First Name"
    public String xpathFirstName = "//input[@name='first_name']";
    //xpath-запрос, который находит на странице регистрации поле ввода "Last Name"
    public String xpathLastName = "//input[@name='last_name']";
    //xpath-запрос, который находит на странице регистрации поле ввода "Email"
    public String xpathEmail = "//input[@name='email']";
    //xpath-запрос, который находит на странице регистрации поле ввода "Password"
    public String xpathPassword = "//input[@name='password1']";
    //xpath-запрос, который находит на странице регистрации поле ввода "Confirm Password"
    public String xpathConfirmPassword = "//input[@name='password2']";
    //xpath-запрос, который находит на странице регистрации кнопку "Register"
    public String xpathcmdRegister = "//input[@value='Register']";
    //xpath-запрос, который находит на странице регистрации надпись об ошибке "Oops, error on page. Some of your fields have invalid data or email was previously used"
    public String xpatmsgRegisterError = "//span[text()='Oops, error on page. Some of your fields have invalid data or email was previously used']";
    //xpath-запрос, который находит на странице после успешной регистрации пользователя сообщение "Account is created!"
    public String xpatmsgRegister = "//span[text()='Account is created!']";

    //*****************************************************************************************************************
    //Тесты
    //*****************************************************************************************************************

    //Позитивный тест на ввод в поле "Zip code" 5 цифр
    @Test
    public void zipCodeShoulAccert5Digits() {

        //Значение, которое необходимо ввести в поле ввода "ZIP code"
        String valueZipCode = "12345";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py
        driver.get(pageZipRegistration);

        //Ввести в поле ввода "ZIP code" 5 цифр
        WebElement inputZipCode = driver.findElement(By.xpath(xpathZipCode));
        inputZipCode.sendKeys(valueZipCode);

        //Нажать кнопку Continue
        WebElement cmdContinue = driver.findElement(By.xpath(xpathcmdContinue));
        cmdContinue.click();

        //Убедится, что перешли на страницу ввода регистрационных данных пользователя:
        //на странице есть кнопка "Register"
        boolean isDisplaed = driver.findElement(By.xpath(xpathcmdRegister)).isDisplayed();
        Assert.assertTrue(isDisplaed,"Не произошел переход на страницу ввода регистрационных данных пользователя");

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
        driver.get(pageZipRegistration);

        //Ввести в поле ввода "ZIP code" 4 цифры
        WebElement inputZipCode = driver.findElement(By.xpath(xpathZipCode));
        inputZipCode.sendKeys(valueZipCode);

        //Нажать кнопку Continue
        WebElement cmdContinue = driver.findElement(By.xpath(xpathcmdContinue));
        cmdContinue.click();

        //Убедится, что на странице отобразилось сообщение об ошибке о том и сообщение об ошибке содержит текст "Oops, error on page. ZIP code should have 5 digits"
        boolean isDisplaed = driver.findElement(By.xpath(xpathmsgZipError)).isDisplayed();
        Assert.assertTrue(isDisplaed, "На странице не отобразилось сообщение об ошибке ввода");
        Assert.assertEquals(driver.findElement(By.xpath(xpathmsgZipError)).getText(),"Oops, error on page. ZIP code should have 5 digits","Неправильное сообщение об ошибке ввода Zip-кода");

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
        driver.get(pageZipRegistration);

        //Ввести в поле ввода "ZIP code" 4 цифры
        WebElement inputZipCode = driver.findElement(By.xpath(xpathZipCode));
        inputZipCode.sendKeys(valueZipCode);

        //Нажать кнопку Continue
        WebElement cmdContinue = driver.findElement(By.xpath(xpathcmdContinue));
        cmdContinue.click();

        //Убедится, что на странице отобразилось сообщение об ошибке о том и сообщение об ошибке содержит текст "Oops, error on page. ZIP code should have 5 digits"
        boolean isDisplaed = driver.findElement(By.xpath(xpathmsgZipError)).isDisplayed();
        Assert.assertTrue(isDisplaed, "На странице не отобразилось сообщение об ошибке ввода");
        Assert.assertEquals(driver.findElement(By.xpath(xpathmsgZipError)).getText(),"Oops, error on page. ZIP code should have 5 digits","Неправильное сообщение об ошибке ввода Zip-кода");

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
        driver.get(pageZipRegistration);

        //Ввести в поле ввода "ZIP code" 6 цифр
        WebElement inputZipCode = driver.findElement(By.xpath(xpathZipCode));
        inputZipCode.sendKeys(valueZipCode);

        //Нажать кнопку Continue
        WebElement cmdContinue = driver.findElement(By.xpath(xpathcmdContinue));
        cmdContinue.click();

        //Убедится, что на странице отобразилось сообщение об ошибке о том и сообщение об ошибке содержит текст "Oops, error on page. ZIP code should have 5 digits"
        boolean isDisplaed = driver.findElement(By.xpath(xpathmsgZipError)).isDisplayed();
        Assert.assertTrue(isDisplaed, "На странице не отобразилось сообщение об ошибке ввода");
        Assert.assertEquals(driver.findElement(By.xpath(xpathmsgZipError)).getText(),"Oops, error on page. ZIP code should have 5 digits","Неправильное сообщение об ошибке ввода Zip-кода");

        //Закрыть браузер
        driver.quit();
    }

    //Позитивный тест на попытку регистрации на сайте при всех полях страницы регистрации, заполненных корректными данными
    @Test
    public void allCorrectInputFieldsRegister() {

        //Значение, которое необходимо ввести в поля ввода на странице ввода регистрационных данных пользователя
        String valueFirstName = "Dmitry";
        String valueLastName = "Khilko";
        String valueEmail = "123@mail.ru";
        String valuePassword = "123456789";
        String valueConfirmPassword = "123456789";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        driver.get(pageRegistration);

        //Ввести значение в поле ввода "First Name"
        WebElement inputFirstName = driver.findElement(By.xpath(xpathFirstName));
        inputFirstName.sendKeys(valueFirstName);
        //Ввести значение в поле ввода "Last Name"
        WebElement inputLastName = driver.findElement(By.xpath(xpathLastName));
        inputLastName.sendKeys(valueLastName);
        //Ввести значение в поле ввода "Email"
        WebElement inputEmail = driver.findElement(By.xpath(xpathEmail));
        inputEmail.sendKeys(valueEmail);
        //Ввести значение в поле ввода "Password"
        WebElement inputPassword = driver.findElement(By.xpath(xpathPassword));
        inputPassword.sendKeys(valuePassword);
        //Ввести значение в поле ввода "Confirm Password"
        WebElement inputConfirmPassword = driver.findElement(By.xpath(xpathConfirmPassword));
        inputConfirmPassword.sendKeys(valueConfirmPassword);
        //Нажать кнопку Register
        WebElement cmdRegister = driver.findElement(By.xpath(xpathcmdRegister));
        cmdRegister.click();

        //Убедится, что перешли на страницу с сообщением об успешной регистрации пользователя
        boolean isDisplaed = driver.findElement(By.xpath(xpatmsgRegister)).isDisplayed();
        Assert.assertTrue(isDisplaed,"На странице не отобразилось сообщение об успешной регистрации пользователя");

        //Закрыть браузер
        driver.quit();
    }

    //Негативный тест на попытку регистрации на сайте при всех пустых полях страницы регистрации
    @Test
    public void allEmptyInputFieldsRegister() {

        //Значение, которое необходимо ввести в поля ввода на странице ввода регистрационных данных пользователя
        String valueFirstName = "";
        String valueLastName = "";
        String valueEmail = "";
        String valuePassword = "";
        String valueConfirmPassword = "";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        driver.get(pageRegistration);

        //Ввести значение в поле ввода "First Name"
        WebElement inputFirstName = driver.findElement(By.xpath(xpathFirstName));
        inputFirstName.sendKeys(valueFirstName);
        //Ввести значение в поле ввода "Last Name"
        WebElement inputLastName = driver.findElement(By.xpath(xpathLastName));
        inputLastName.sendKeys(valueLastName);
        //Ввести значение в поле ввода "Email"
        WebElement inputEmail = driver.findElement(By.xpath(xpathEmail));
        inputEmail.sendKeys(valueEmail);
        //Ввести значение в поле ввода "Password"
        WebElement inputPassword = driver.findElement(By.xpath(xpathPassword));
        inputPassword.sendKeys(valuePassword);
        //Ввести значение в поле ввода "Confirm Password"
        WebElement inputConfirmPassword = driver.findElement(By.xpath(xpathConfirmPassword));
        inputConfirmPassword.sendKeys(valueConfirmPassword);
        //Нажать кнопку Register
        WebElement cmdRegister = driver.findElement(By.xpath(xpathcmdRegister));
        cmdRegister.click();

        //Убедится, что на странице выведено сообщение о неккоректности введенных регистрационных данных пользователя
        boolean isDisplaed = driver.findElement(By.xpath(xpatmsgRegisterError)).isDisplayed();
        Assert.assertTrue(isDisplaed,"На странице не отобразилось сообщение о некорректности введенных регистрационных данных пользователя");

        //Закрыть браузер
        driver.quit();
    }

    //Негативный тест на попытку регистрации на сайте при пустом поле "First Name" и заполненных корректными значениями
    // остальных полей
    @Test
    public void onlyEmptyFieldFirstNameRegister() {
        //Значение, которое необходимо ввести в поля ввода на странице ввода регистрационных данных пользователя
        String valueFirstName = "";
        String valueLastName = "Khilko";
        String valueEmail = "123@mail.ru";
        String valuePassword = "123456789";
        String valueConfirmPassword = "123456789";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        driver.get(pageRegistration);

        //Ввести значение в поле ввода "First Name"
        WebElement inputFirstName = driver.findElement(By.xpath(xpathFirstName));
        inputFirstName.sendKeys(valueFirstName);
        //Ввести значение в поле ввода "Last Name"
        WebElement inputLastName = driver.findElement(By.xpath(xpathLastName));
        inputLastName.sendKeys(valueLastName);
        //Ввести значение в поле ввода "Email"
        WebElement inputEmail = driver.findElement(By.xpath(xpathEmail));
        inputEmail.sendKeys(valueEmail);
        //Ввести значение в поле ввода "Password"
        WebElement inputPassword = driver.findElement(By.xpath(xpathPassword));
        inputPassword.sendKeys(valuePassword);
        //Ввести значение в поле ввода "Confirm Password"
        WebElement inputConfirmPassword = driver.findElement(By.xpath(xpathConfirmPassword));
        inputConfirmPassword.sendKeys(valueConfirmPassword);
        //Нажать кнопку Register
        WebElement cmdRegister = driver.findElement(By.xpath(xpathcmdRegister));
        cmdRegister.click();

        //Убедится, что на странице выведено сообщение о неккоректности введенных регистрационных данных пользователя
        boolean isDisplaed = driver.findElement(By.xpath(xpatmsgRegisterError)).isDisplayed();
        Assert.assertTrue(isDisplaed,"На странице не отобразилось сообщение о некорректности введенных регистрационных данных пользователя");

        //Закрыть браузер
        driver.quit();
    }

    //Позитивный тест на попытку регистрации на сайте при пустом поле "Last Name" и заполненных корректными значениями
    // остальных полей
    @Test
    public void onlyEmptyFieldLastNameRegister() {

        //Значение, которое необходимо ввести в поля ввода на странице ввода регистрационных данных пользователя
        String valueFirstName = "Dmitry";
        String valueLastName = "";
        String valueEmail = "123@mail.ru";
        String valuePassword = "123456789";
        String valueConfirmPassword = "123456789";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        driver.get(pageRegistration);

        //Ввести значение в поле ввода "First Name"
        WebElement inputFirstName = driver.findElement(By.xpath(xpathFirstName));
        inputFirstName.sendKeys(valueFirstName);
        //Ввести значение в поле ввода "Last Name"
        WebElement inputLastName = driver.findElement(By.xpath(xpathLastName));
        inputLastName.sendKeys(valueLastName);
        //Ввести значение в поле ввода "Email"
        WebElement inputEmail = driver.findElement(By.xpath(xpathEmail));
        inputEmail.sendKeys(valueEmail);
        //Ввести значение в поле ввода "Password"
        WebElement inputPassword = driver.findElement(By.xpath(xpathPassword));
        inputPassword.sendKeys(valuePassword);
        //Ввести значение в поле ввода "Confirm Password"
        WebElement inputConfirmPassword = driver.findElement(By.xpath(xpathConfirmPassword));
        inputConfirmPassword.sendKeys(valueConfirmPassword);
        //Нажать кнопку Register
        WebElement cmdRegister = driver.findElement(By.xpath(xpathcmdRegister));
        cmdRegister.click();

        //Убедится, что перешли на страницу с сообщением об успешной регистрации пользователя
        boolean isDisplaed = driver.findElement(By.xpath(xpatmsgRegister)).isDisplayed();
        Assert.assertTrue(isDisplaed,"На странице не отобразилось сообщение об успешной регистрации пользователя");

        //Закрыть браузер
        driver.quit();
    }

    //Негативный тест на попытку регистрации на сайте при пустом поле "Email" и заполненных корректными значениями
    // остальных полей
    @Test
    public void onlyEmptyFieldEmailRegister() {

        //Значение, которое необходимо ввести в поля ввода на странице ввода регистрационных данных пользователя
        String valueFirstName = "Dmitry";
        String valueLastName = "Khilko";
        String valueEmail = "";
        String valuePassword = "123456789";
        String valueConfirmPassword = "123456789";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        driver.get(pageRegistration);

        //Ввести значение в поле ввода "First Name"
        WebElement inputFirstName = driver.findElement(By.xpath(xpathFirstName));
        inputFirstName.sendKeys(valueFirstName);
        //Ввести значение в поле ввода "Last Name"
        WebElement inputLastName = driver.findElement(By.xpath(xpathLastName));
        inputLastName.sendKeys(valueLastName);
        //Ввести значение в поле ввода "Email"
        WebElement inputEmail = driver.findElement(By.xpath(xpathEmail));
        inputEmail.sendKeys(valueEmail);
        //Ввести значение в поле ввода "Password"
        WebElement inputPassword = driver.findElement(By.xpath(xpathPassword));
        inputPassword.sendKeys(valuePassword);
        //Ввести значение в поле ввода "Confirm Password"
        WebElement inputConfirmPassword = driver.findElement(By.xpath(xpathConfirmPassword));
        inputConfirmPassword.sendKeys(valueConfirmPassword);
        //Нажать кнопку Register
        WebElement cmdRegister = driver.findElement(By.xpath(xpathcmdRegister));
        cmdRegister.click();

        //Убедится, что на странице выведено сообщение о неккоректности введенных регистрационных данных пользователя
        boolean isDisplaed = driver.findElement(By.xpath(xpatmsgRegisterError)).isDisplayed();
        Assert.assertTrue(isDisplaed,"На странице не отобразилось сообщение о некорректности введенных регистрационных данных пользователя");

        //Закрыть браузер
        driver.quit();
    }

    //Негативный тест на попытку регистрации на сайте при пустом поле "Password" и заполненных корректными значениями
    // остальных полей
    @Test
    public void onlyEmptyFieldPasswordRegister() {

        //Значение, которое необходимо ввести в поля ввода на странице ввода регистрационных данных пользователя
        String valueFirstName = "Dmitry";
        String valueLastName = "Khilko";
        String valueEmail = "123@mail.ru";
        String valuePassword = "";
        String valueConfirmPassword = "123456789";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        driver.get(pageRegistration);

        //Ввести значение в поле ввода "First Name"
        WebElement inputFirstName = driver.findElement(By.xpath(xpathFirstName));
        inputFirstName.sendKeys(valueFirstName);
        //Ввести значение в поле ввода "Last Name"
        WebElement inputLastName = driver.findElement(By.xpath(xpathLastName));
        inputLastName.sendKeys(valueLastName);
        //Ввести значение в поле ввода "Email"
        WebElement inputEmail = driver.findElement(By.xpath(xpathEmail));
        inputEmail.sendKeys(valueEmail);
        //Ввести значение в поле ввода "Password"
        WebElement inputPassword = driver.findElement(By.xpath(xpathPassword));
        inputPassword.sendKeys(valuePassword);
        //Ввести значение в поле ввода "Confirm Password"
        WebElement inputConfirmPassword = driver.findElement(By.xpath(xpathConfirmPassword));
        inputConfirmPassword.sendKeys(valueConfirmPassword);
        //Нажать кнопку Register
        WebElement cmdRegister = driver.findElement(By.xpath(xpathcmdRegister));
        cmdRegister.click();

        //Убедится, что на странице выведено сообщение о неккоректности введенных регистрационных данных пользователя
        boolean isDisplaed = driver.findElement(By.xpath(xpatmsgRegisterError)).isDisplayed();
        Assert.assertTrue(isDisplaed,"На странице не отобразилось сообщение о некорректности введенных регистрационных данных пользователя");

        //Закрыть браузер
        driver.quit();
    }

    //Негативный тест на попытку регистрации на сайте при пустом поле "Confirm Password" и заполненных корректными
    // значениями остальных полей
    @Test
    public void onlyEmptyFieldConfirmPasswordRegister() {
        //Значение, которое необходимо ввести в поля ввода на странице ввода регистрационных данных пользователя
        String valueFirstName = "Dmitry";
        String valueLastName = "Khilko";
        String valueEmail = "123@mail.ru";
        String valuePassword = "123456789";
        String valueConfirmPassword = "";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        driver.get(pageRegistration);

        //Ввести значение в поле ввода "First Name"
        WebElement inputFirstName = driver.findElement(By.xpath(xpathFirstName));
        inputFirstName.sendKeys(valueFirstName);
        //Ввести значение в поле ввода "Last Name"
        WebElement inputLastName = driver.findElement(By.xpath(xpathLastName));
        inputLastName.sendKeys(valueLastName);
        //Ввести значение в поле ввода "Email"
        WebElement inputEmail = driver.findElement(By.xpath(xpathEmail));
        inputEmail.sendKeys(valueEmail);
        //Ввести значение в поле ввода "Password"
        WebElement inputPassword = driver.findElement(By.xpath(xpathPassword));
        inputPassword.sendKeys(valuePassword);
        //Ввести значение в поле ввода "Confirm Password"
        WebElement inputConfirmPassword = driver.findElement(By.xpath(xpathConfirmPassword));
        inputConfirmPassword.sendKeys(valueConfirmPassword);
        //Нажать кнопку Register
        WebElement cmdRegister = driver.findElement(By.xpath(xpathcmdRegister));
        cmdRegister.click();

        //Убедится, что на странице выведено сообщение о неккоректности введенных регистрационных данных пользователя
        boolean isDisplaed = driver.findElement(By.xpath(xpatmsgRegisterError)).isDisplayed();
        Assert.assertTrue(isDisplaed,"На странице не отобразилось сообщение о некорректности введенных регистрационных данных пользователя");

        //Закрыть браузер
        driver.quit();
    }

    //Негативный тест на попытку регистрации на сайте при вводе разных значений в полях "Password" и "Confirm Password"
    // и заполненных корректными значениями остальных полей
    @Test
    public void notEcualFieldsPasswordConfirmPasswordRegister() {
        //Значение, которое необходимо ввести в поля ввода на странице ввода регистрационных данных пользователя
        String valueFirstName = "Dmitry";
        String valueLastName = "Khilko";
        String valueEmail = "123@mail.ru";
        String valuePassword = "123456789";
        String valueConfirmPassword = "1234567899";

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        driver.get(pageRegistration);

        //Ввести значение в поле ввода "First Name"
        WebElement inputFirstName = driver.findElement(By.xpath(xpathFirstName));
        inputFirstName.sendKeys(valueFirstName);
        //Ввести значение в поле ввода "Last Name"
        WebElement inputLastName = driver.findElement(By.xpath(xpathLastName));
        inputLastName.sendKeys(valueLastName);
        //Ввести значение в поле ввода "Email"
        WebElement inputEmail = driver.findElement(By.xpath(xpathEmail));
        inputEmail.sendKeys(valueEmail);
        //Ввести значение в поле ввода "Password"
        WebElement inputPassword = driver.findElement(By.xpath(xpathPassword));
        inputPassword.sendKeys(valuePassword);
        //Ввести значение в поле ввода "Confirm Password"
        WebElement inputConfirmPassword = driver.findElement(By.xpath(xpathConfirmPassword));
        inputConfirmPassword.sendKeys(valueConfirmPassword);
        //Нажать кнопку Register
        WebElement cmdRegister = driver.findElement(By.xpath(xpathcmdRegister));
        cmdRegister.click();

        //Убедится, что на странице выведено сообщение о неккоректности введенных регистрационных данных пользователя
        boolean isDisplaed = driver.findElement(By.xpath(xpatmsgRegisterError)).isDisplayed();
        Assert.assertTrue(isDisplaed,"На странице не отобразилось сообщение о некорректности введенных регистрационных данных пользователя");

        //Закрыть браузер
        driver.quit();
    }
}
