package AppiumAndroidTests.AndroidApp;



import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CommonMethods {
	
	public static void toogleConnection(AndroidDriver<MobileElement> driver) {
		boolean data = driver.getConnection().isDataEnabled();
		boolean wifi = driver.getConnection().isWiFiEnabled();

		if ((data == true) && (wifi == true)) {
			System.out.println("Do Nothing");
		} else if ((data == false) && (wifi == true)) {
			driver.toggleData();
			System.out.println("Data Toggle");
		} else if ((data == true) && (wifi == false)) {
			System.out.println("Wifi Toggle");
			driver.toggleWifi();
			
		} else if ((data == false) && (wifi == false)) {
			driver.toggleData();
			driver.toggleWifi();
		}
	}
	
	public static void scroll(AndroidDriver<MobileElement> driver, double startPer,double endPer) {
	    Dimension dimention = driver.manage().window().getSize();
	    Double scrollHeightStart = dimention.getHeight()*startPer;
	    int scrollStart = scrollHeightStart.intValue();
	    Double scrollHeightend = dimention.getHeight()*endPer;
	    int scrollEnd = scrollHeightend.intValue();
	    TouchAction action = new TouchAction(driver);
	    action.press(PointOption.point(0, scrollStart))
	    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(0)))
	    .moveTo(PointOption.point(0, scrollEnd))
	    .release().perform();
	    
	}

	
	 
	public static List<MobileElement> getProgressBar(AndroidDriver<MobileElement> driver){
		return driver.findElements(By.className("android.widget.ProgressBar"));
	}
	
	public static void scrollUntilProgressBar(AndroidDriver<MobileElement> driver) {
		while(getProgressBar(driver).size()==0) {
			scroll(driver,0.8,0.2);
			if(getProgressBar(driver).size()>0) {
				break;
			}
		}
	}
}
