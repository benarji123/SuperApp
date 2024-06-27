package com.superapp.tests;

import java.util.HashMap;

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

public class VerifyComparePlanQuickLinkTest extends BaseClass
{

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

	@Record(author = "Narada")
	@Test(dataProvider = "Datadriven")
	public void comparePlanMenuLauncherTest(HashMap<String, String> data) throws InterruptedException
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
		
		Assert.assertEquals(mspaceHomePage.skipGestureInHomePage(), true);
		mspaceHomePage.skipGesture();
		
		gesture.scrollGesture(900, 1200, 200, 1200);
		
		quickLink.comparePlans();
		
		Assert.assertEquals("Compare Plan",comparePlanPage.validateComparePlanHeader());

	}

	@DataProvider(name="Datadriven")
	public Object[][] getLoginValues()
	{

		return GenerateDynamicTestNG .getTestDataa("LoginData", "Login");
	}
	
}
