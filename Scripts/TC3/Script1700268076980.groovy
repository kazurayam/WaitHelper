import static com.kazurayam.ks.WaitHelpers.waitForDomModificationsToCease

import org.openqa.selenium.WebDriver

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

/**
 * TC3: automate signing-in to Microsoft Azure DevOps with MutationObserver
 */

TestObject makeTestObject(String id, String xpathExpression) {
	TestObject tObj = new TestObject(id)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpathExpression)
	return tObj
}

String url = "https://dev.azure.com/${GlobalVariable.ACCOUNT}"
WebUI.openBrowser(url)
WebUI.setViewPortSize(800, 800)
WebDriver driver = DriverFactory.getWebDriver()

// to make sure the page is loaded
waitForDomModificationsToCease(driver)

// type the EMAIL as my DevOps account id
TestObject loginfmt = makeTestObject("loginfmt", "//input[@name='loginfmt']")
WebUI.sendKeys(loginfmt, GlobalVariable.EMAIL)
waitForDomModificationsToCease(driver)   // wait for the DOM to be static

// click the Next button
TestObject nextButton = makeTestObject("Next", "//input[@id='idSIButton9']")
WebUI.click(nextButton)
waitForDomModificationsToCease(driver)

// type the password of my DevOps account
TestObject passwd = makeTestObject("Passwd", "//input[@name='passwd']")
WebUI.sendKeys(passwd, GlobalVariable.PASSWD)
waitForDomModificationsToCease(driver)

// click the Sing-in button
TestObject signinButton = makeTestObject("SignIn", "//input[@id='idSIButton9']")
WebUI.click(signinButton)
waitForDomModificationsToCease(driver)

// click the Yes button
TestObject yesButton = makeTestObject("Yes", "//input[@id='idSIButton9']")
WebUI.click(yesButton)
waitForDomModificationsToCease(driver)

WebUI.delay(3)
WebUI.closeBrowser()
