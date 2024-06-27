package com.superapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.genericutility.WaitUtility;
import com.superapp.report.ReportUtility;
import com.superapp.report.UtilityInstanceTransfer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ComparePlanPage 
{
	WaitUtility wait;
	AppiumDriver driver;
	ExtentTest logging =null;
	ReportUtility  report=null;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Compare Plan']")
	private WebElement android_comparePlanHeader;
	
	@AndroidFindBy(className="com.horcrux.svg.PathView")
	private WebElement android_comparePlanCloseSymbol;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Do you want to generate quote for this product?']")
	private WebElement android_comparePlanCloseDrawer;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Yes, Continue']")
	private WebElement android_comparePlanYesContinueButton;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='No, Exit']")
	private WebElement android_comparePlanExitButton;
	
		
	@AndroidFindBy(xpath="//android.widget.Button[@text='-Select-']")
	private WebElement android_selectdropdown;
	
	
	@AndroidFindBy(xpath="//android.view.View[@text='HDFC']")
	private WebElement android_hdfcvalue;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Compare Now']")
	private WebElement android_compareNowButton;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='Download download']")
	private WebElement android_downloadoption;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='Share share']")
	private WebElement android_shareoption;
	
	
	
	
	public ComparePlanPage(AndroidDriver driver) 
	{  
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
		wait= new WaitUtility();
		report=new ReportUtility();
		logging = UtilityInstanceTransfer.getExtentTest();
	}
	
	public String validateComparePlanHeader()
	{
		wait.explicitWaitVisibilityOf(driver, android_comparePlanHeader);
		String comparePlanHeader=android_comparePlanHeader.getText();
		report.info(logging, "Compare Plan Header is Displayed");
		return comparePlanHeader;
	}
	
	public void closeComparePlanSymbol()
	{
		wait.explicitWaitVisibilityOf(driver, android_comparePlanHeader);
		android_comparePlanCloseSymbol.click();
		report.info(logging, "Compare Plan X Symbol is clicked");
	}
	
	public String closeDrawerValidation()
	{	
		wait.explicitWaitVisibilityOf(driver, android_comparePlanCloseDrawer);
		String comparePlanCloseDrawer=android_comparePlanCloseDrawer.getText();
		report.info(logging, "Close Drawer is opened");
		return comparePlanCloseDrawer;
	}
	
	public void exitComparePlan()
	{	
		wait.explicitWaitVisibilityOf(driver, android_comparePlanExitButton);
		android_comparePlanExitButton.click();
		report.info(logging, "Yes, Exit button is clicked and Compare Plan page is exited");
	}
	
	public void continueComparePlan()
	{	
		wait.explicitWaitVisibilityOf(driver, android_comparePlanYesContinueButton);
		android_comparePlanYesContinueButton.click();
		report.info(logging, "Yes, Continue button is clicked and returns back to the Compare Plan page");
	}
	
	public void selectDropDownValue() {
		wait.explicitWaitVisibilityOf(driver,android_selectdropdown);
		android_selectdropdown.click();
		report.info(logging, "Click on select dropdown");
		wait.explicitWaitVisibilityOf(driver,android_hdfcvalue);
		android_hdfcvalue.click();
		report.info(logging, "Select value from dropdown");
	}
	
	public void clickOnCamparePlan() {
		wait.explicitWaitVisibilityOf(driver,android_compareNowButton);
		android_compareNowButton.click();
		report.info(logging, "Click on compareplan button");
	}
	public boolean isDownLoadOptionPresent() {
		wait.explicitWaitVisibilityOf(driver,android_downloadoption);
		return android_downloadoption.isDisplayed();
		
	}
	public boolean isShareOptionPresent() {
		wait.explicitWaitVisibilityOf(driver,android_shareoption);
		return android_shareoption.isDisplayed();
	}
}
