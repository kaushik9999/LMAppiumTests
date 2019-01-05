package AppiumAndroidTests.AndroidApp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import junit.framework.Assert;

public class LalamoveTests {

	AndroidDriver<MobileElement> driver;
	@BeforeMethod
	public void setUp() {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		// Set android deviceName desired capabilitys. Set your device name.
		capabilities.setCapability("deviceName", "Nexus6");
		capabilities.setCapability(CapabilityType.VERSION, "8.1.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", "D:/app-tech-android-challenge-20180918.apk");
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// immplicit wait
	}

	@Test(enabled =true, priority=1)
	public void dataWifiOffTest()  {

		boolean data = driver.getConnection().isDataEnabled();
		boolean wifi = driver.getConnection().isWiFiEnabled();
		// check connection and switch it off
		if ((data == true) && (wifi == true)) {
			driver.toggleData();
			driver.toggleWifi();
		} else if ((data == false) && (wifi == true)) {
			System.out.println("Wifi Toggled");
			driver.toggleWifi();
		} else if ((data == true) && (wifi == false)) {
			driver.toggleData();
			System.out.println("Data Toggled");
		} else if ((data == false) && (wifi == false)) {
			System.out.println("Do nothing");
		}
		//reset app after connection reset
		driver.resetApp();
		String errText =driver.findElement(LalamoveElements.errMes).getText();// get error text
		System.out.println(errText);
		assertTrue(errText.equals("Error! Pull down to refresh"));// Asset the text being displayed is correct and error pop up

	}
	@Test(enabled =true, priority=5)
	public void goTodeliveryDetailTest()  {
		CommonMethods.toogleConnection(driver);// connection check
		driver.resetApp();// app reset post connection
		driver.findElement(LalamoveElements.secondElemLanPage).click();// click on second list element
        String dDText = driver.findElement(LalamoveElements.deliveryDetail).getText();
        assertEquals("Delivery Detail", dDText);// assert if delivery detail is present
        
	}
	@Test(enabled=true,priority=10)
	public void deliveryListTest() {
		CommonMethods.toogleConnection(driver);
		driver.resetApp();
		//CommonMethods.scroll(driver);
		List<MobileElement> list1; 
		List<MobileElement> list =driver.findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup"));
		list1 =list;
		 int counter=0;
		while ((driver.findElements(By.className("android.widget.ProgressBar")).size()==0)||list1.size()<50) {
			CommonMethods.scroll(driver,0.8,0.2);
			list1.addAll(driver.findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup")));
			System.out.println(list1.size());
			if(list1.size()>100) {
				break;
			}
			if(counter>100) {
				break;
			}
			counter++;
		}
		Assert.assertTrue(list1.size()>50);
	}
	@Test(enabled=true, priority=15)
	public void deliveryDetailBackTest()  {
		CommonMethods.toogleConnection(driver);
		driver.resetApp();
		driver.findElement(LalamoveElements.secondElemLanPage).click();
        MobileElement dD = driver.findElement(LalamoveElements.deliveryDetail);
        assertTrue(dD.isDisplayed());
        driver.findElement(LalamoveElements.backButton).click();
        MobileElement dL = driver.findElement(LalamoveElements.deliveryDetail);
        assertTrue(dL.isDisplayed());
		
	}
	
	@Test(enabled =true, priority=20)
	public void swipeDownProgressBar() {
		CommonMethods.toogleConnection(driver);
		driver.resetApp();
		MobileElement dD = driver.findElement(LalamoveElements.deliveryDetail);
        assertTrue(dD.isDisplayed());
		CommonMethods.scroll(driver,0.2,0.8);
		MobileElement e = driver.findElement(By.xpath("//android.widget.RelativeLayout/android.view.ViewGroup/android.widget.ImageView"));
		assertTrue(e.isDisplayed());
	}
	@Test(enabled =true,priority=25)
	public void longPressToDelete() throws InterruptedException {
		CommonMethods.toogleConnection(driver);
		driver.resetApp();
		MobileElement element = driver.findElement(LalamoveElements.secondElemLanPage);
		TouchAction tc = new TouchAction(driver);
		tc.longPress(LongPressOptions.longPressOptions()
				.withElement(ElementOption.element(element)))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		.perform()
		.release();
		Thread.sleep(1000);	
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
