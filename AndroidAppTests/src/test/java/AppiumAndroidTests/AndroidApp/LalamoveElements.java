package AppiumAndroidTests.AndroidApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LalamoveElements{
	
	
	public static By errMes = By.xpath("//android.widget.LinearLayout[2]/android.widget.TextView");
	public static By secondElemLanPage = By.xpath("//android.widget.RelativeLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]");
	public static By deliveryDetail =By.xpath("//android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView");
	public static By backButton = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

	
	
}
