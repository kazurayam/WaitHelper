package com.kazurayam.ks

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

public class WaitHelpers {

	/**
	 * wait for the DOM to stop changing.
	 * Uses a MutationObserver (JavaScript) to be notified of DOM modifications.
	 * "Changing" can include addition/removal/modification of any elements in the DOM
	 */
	public static void waitForDomModificationsToCease(WebDriver driver) {

		JavascriptExecutor jsx = (JavascriptExecutor)driver;
		jsx.executeScript("""
if (typeof observer === 'undefined') {
    window.domModifiedTime = Date.now();
    let targetNode = document.querySelector('body');
    let config = {
        attributes: true,
        childList: true,
        subtree: true
    };
    let callback = () => {
        window.domModifiedTime = Date.now();
    };
    let observer = new MutationObserver(callback);
    observer.observe(targetNode, config);
}
""");
		double modifiedTime = 0;
		double start = System.currentTimeMillis();
		while (modifiedTime != (Double)jsx.executeScript("return window.domModifiedTime") && System.currentTimeMillis() - start < 30000) {
			println ">>> modifiedTime=" + modifiedTime;
			println "<<< jsx returned window.domModifiedTime=" + jsx.executeScript("return window.domModifiedTime");
			modifiedTime = (Double)jsx.executeScript("return window.domModifiedTime");
			Thread.sleep(300);
		}
	}
}