import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Feature("ДЗ модуля 5")
public class HW5 {
    @AndroidFindBy ( id = "activity_main_auto_scroll_scrollView_with_padding_button")
    MobileElement btnAutoScrollScrollViewWithPadding;

    @AndroidFindBy ( id = "activity_main_title")
    MobileElement mainScreenTitle;

    @AndroidFindBy (uiAutomator = "textContains(\"Simple Fragment\")" )
    MobileElement simpleFragment;

    @AndroidFindBy ( id = "tvText1")
    MobileElement btnTvText1;

    @AndroidFindBy ( id = "tvText20")
    MobileElement btnTvText20;

    @AndroidFindBy ( id = "activity_main_device_button")
    MobileElement btnDeviceSample;

    @AndroidFindBy ( id = "input")
    MobileElement fieldInput;

    @AndroidFindBy ( id = "activity_main_webview_sample_button")
    MobileElement btnWebView;

    @AndroidFindBy ( id = "activity_main_complex_compose_sample_button")
    MobileElement btnСompose;

    @AndroidFindBy ( id = "activity_compose_start")
    MobileElement btnStart;

    @AndroidFindBy ( id = "message")
    MobileElement message;

    @AndroidFindBy ( id = "button1")
    MobileElement btnOk;

    @AndroidFindBy (uiAutomator = "textContains(\"Dialog #2\")")
    MobileElement dialog2;

    @AndroidFindBy ( id = "activity_compose_finish")
    MobileElement finish;

    private final DriverFactory driverFactory = new DriverFactory();
    private AndroidDriver<?> driver;

    @Step("Настройка драйвера")
    @Before
    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUp();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Description("тестирование функционала приложения Kasspresso")
    @Story("Kasspresso тест")
    @Test
    public void sampleTests() throws InterruptedException {
        Assert.assertTrue(btnAutoScrollScrollViewWithPadding.isDisplayed());
        Assert.assertTrue(btnAutoScrollScrollViewWithPadding.isEnabled());
        Assert.assertFalse(btnAutoScrollScrollViewWithPadding.isSelected());

        btnAutoScrollScrollViewWithPadding.click();


        simpleFragment.isDisplayed();

        wait2secTvText1(MobileBy.id("tvText1"));
        btnTvText1.isDisplayed();

        swipe(Direction.LEFT);
        swipe(Direction.RIGHT);
        swipe(Direction.UP);

        btnTvText20.isDisplayed();
        wait3secTvText20(MobileBy.id("tvText20"));

        swipe(Direction.DOWN);



        driver.rotate(ScreenOrientation.PORTRAIT);

        driver.navigate().back();

        mainScreenTitle.isDisplayed();

        btnDeviceSample.isDisplayed();
        btnDeviceSample.click();

        fieldInput.isDisplayed();
        fieldInput.click();
        String text = "абракадабра";
        fieldInput.sendKeys(text);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

        driver.navigate().back();

        Assert.assertTrue(rotateToLandscape());

        driver.rotate(ScreenOrientation.PORTRAIT);

        btnWebView.isDisplayed();

    }

    @Step ("Явное ожидание")
    private void wait2secTvText1(By by){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("tvText1")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Step ("Явное ожидание")
    private void wait3secTvText20(By by){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("tvText20")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Step ("Явное ожидание")
    private void wait5secMessage(By by){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("message")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Step("Проверка поворота в ландскейп")
    private  boolean rotateToLandscape(){
        try {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    private enum Direction {
        LEFT,
        RIGHT,
        DOWN,
        UP,
    }


    @Step("Свайп {direction}")
    private void swipe(Direction direction) {
        int edge = 5;
        int press = 300;
        Dimension dims = driver.manage().window().getSize();
        PointOption<?> pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        PointOption<?> pointOptionEnd;
        switch (direction) {
            case RIGHT:
                pointOptionEnd = PointOption.point(dims.width - edge, dims.height / 2);
                break;
            case LEFT:
                pointOptionEnd = PointOption.point(edge, dims.height / 2);
                break;
            case UP:
                pointOptionEnd = PointOption.point(dims.width/2,edge);
                break;
            case DOWN:
                pointOptionEnd = PointOption.point(dims.width/2, dims.height - edge);
                break;
            default:
                throw new IllegalArgumentException("swipe is not supported");
        }
    new TouchAction<>(driver)
            .press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(press)))
            .moveTo(pointOptionEnd)
                .release()
                .perform();
    }

    @Step("Закрытие драйвера")
    @After
    public void tearDown() {
        driver.quit();
    }
}
