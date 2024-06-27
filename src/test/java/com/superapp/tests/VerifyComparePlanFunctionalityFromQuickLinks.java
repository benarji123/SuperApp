package com.superapp.tests;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.superapp.baseclass.BaseClass;
import com.superapp.pages.ApplicationLoginPage;
import com.superapp.pages.AttendanceDrawerPage;
import com.superapp.pages.CaroselScreenPage;
import com.superapp.pages.ComparePlanPage;
import com.superapp.pages.MSpaceHomePage;
import com.superapp.pages.MenuBottomLauncherNavigationPage;
import com.superapp.pages.PolicyLoginPage;
import com.superapp.pages.QuickLinksPage;
import com.superapp.pages.SSOLoginPage;
import com.superapp.utils.GenerateDynamicTestNG;
import com.superapp.utils.GestureUtility;
import com.superapp.utils.Record;

import io.appium.java_client.android.AndroidDriver;

public class VerifyComparePlanFunctionalityFromQuickLinks extends BaseClass {

	CaroselScreenPage caroselScreen = null;
	ApplicationLoginPage applicationLogin = null;
	SSOLoginPage ssoLogin = null;
	AttendanceDrawerPage attendanceDrawer= null;
	QuickLinksPage quickLink = null;
	MSpaceHomePage mspaceHomePage=null;
	PolicyLoginPage policyLoginPage=null;
	MenuBottomLauncherNavigationPage menuLauncher=null;
	MenuBottomLauncherNavigationPage comparePlanLauncher=null;
	ComparePlanPage comparePlanPage=null;
	GestureUtility gesture=null;
	AttendanceDrawerPage attendanceDrawerPage=null;
	@Record(author = "Narada")
	@Test(dataProvider = "Datadriven")
	public void comparePlanFunctionality(HashMap<String, String> data) throws InterruptedException
	{
		AndroidDriver adriver= (AndroidDriver) driver;
		
		caroselScreen = new CaroselScreenPage(adriver);
		applicationLogin = new ApplicationLoginPage(adriver);
		ssoLogin = new SSOLoginPage(adriver);
		attendanceDrawer= new AttendanceDrawerPage(adriver);
		quickLink = new QuickLinksPage(adriver);
		mspaceHomePage=new MSpaceHomePage(adriver);
		policyLoginPage=new PolicyLoginPage(adriver);
		menuLauncher=new MenuBottomLauncherNavigationPage(adriver);
		comparePlanLauncher=new MenuBottomLauncherNavigationPage(adriver);
		comparePlanPage=new ComparePlanPage(adriver);
		gesture=new GestureUtility(adriver);
		
		caroselScreen.applicationPrivacySettings();

		Assert.assertEquals(applicationLogin.applicationLoginPage(),"Login");
		applicationLogin.applicationLogin();

		Assert.assertEquals(ssoLogin.ssoLoginPage(), true);
		System.out.println(data.get("SSO ID"));
		System.out.println(data.get("Password"));
		ssoLogin.ssoLoginDetails(data.get("SSO ID"),data.get("Password"));
		
		Assert.assertEquals(ssoLogin.validateBiometricPopUp(), true);
		ssoLogin.completeBiometric();
		attendanceDrawerPage=new AttendanceDrawerPage(adriver);
		
		Assert.assertEquals(mspaceHomePage.skipGestureInHomePage(), true);
		mspaceHomePage.skipGesture();
		
		attendanceDrawerPage.markAttendanceLater();
		
		//gesture.scrollGesture(900, 1200, 200, 1200);
		 Dimension d = adriver.manage().window().getSize();
	      
	        int startx = (int) (d.width * 0.8);
	        int starty = d.height / 2;
	        int endx = (int) (d.width * 0.05);
	        int endy = starty;
	        
	        swipeAction(startx, starty,endx,endy,adriver) ;
		
		    quickLink.comparePlans();
		
		    Assert.assertEquals("Compare Plan",comparePlanPage.validateComparePlanHeader());
		    		  	    
		    comparePlanPage.selectDropDownValue();
		    
		    comparePlanPage.clickOnCamparePlan();
		    
		    assertEquals(true, comparePlanPage.isDownLoadOptionPresent());
		    
		    assertEquals(true, comparePlanPage.isShareOptionPresent());

	}

	@DataProvider(name="Datadriven")
	public Object[][] getLoginValues()
	{

		return GenerateDynamicTestNG .getTestDataa("LoginData", "Login");
	}
	
	//method to left and right swipe on the screen based on coordinates
		public void swipeAction(int startx, int starty,int endx,int endy,AndroidDriver adriver) {
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
	        Sequence swipe = new Sequence(finger, 1)
	            .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
	            .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	            .addAction(new Pause(finger, Duration.ofMillis(1000))) // Adjust pause duration if necessary
	            .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endx, endy))
	            .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	        adriver.perform(Collections.singletonList(swipe));
			
			
			
		}
}
