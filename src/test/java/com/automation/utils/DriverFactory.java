package com.automation.utils;

import com.microsoft.playwright.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * DriverFactory — manages Selenium WebDriver and Playwright browser instances.
 * Supports Chrome, Firefox, Edge via system property: -Dbrowser=chrome/firefox/edge
 */
public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<Page> playwrightPage = new ThreadLocal<>();
    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();

    // ── Selenium Driver ────────────────────────────────────────────────────
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browserType = System.getProperty("browser", "chrome").toLowerCase();
            switch (browserType) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-notifications");
                    driver.set(new ChromeDriver(options));
            }
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    // ── Playwright Page ────────────────────────────────────────────────────
    public static Page getPlaywrightPage() {
        if (playwrightPage.get() == null) {
            playwright.set(Playwright.create());
            browser.set(playwright.get().chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
            ));
            playwrightPage.set(browser.get().newPage());
        }
        return playwrightPage.get();
    }

    public static void quitPlaywright() {
        if (browser.get() != null) browser.get().close();
        if (playwright.get() != null) playwright.get().close();
        playwrightPage.remove();
        browser.remove();
        playwright.remove();
    }
}
