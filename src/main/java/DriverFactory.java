import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;

public class DriverFactory {
    private AndroidDriver driver;

    public AndroidDriver<?> setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability(DEVICE_NAME,"emulator-5554");
        desiredCapabilities.setCapability(APP_PACKAGE,"com.kaspersky.kaspressample");
        desiredCapabilities.setCapability(APP_ACTIVITY,"com.kaspersky.kaspressample.MainActivity");




        URL remoteUrl = new URL("http://localhost:4723/wd/hub");


        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return driver;
    }
}

