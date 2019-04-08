import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by OYurkiv on 10/1/2018.
 */
public class DriverFactory {

    final static Logger LOG = Logger.getLogger(DriverFactory.class);

    final static Map<Integer, String> chUserAgentMap = new HashMap<Integer, String>()
    {{
        put(1, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
        put(2, "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/44.0.2403.155 Safari/537.36");
        put(3, "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        put(4, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36");
        put(5, "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36");
        put(6, "Mozilla/5.0 (Windows NT 6.4; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2225.0 Safari/537.36");
        put(7, "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2224.3 Safari/537.36");
        put(8, "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.93 Safari/537.36");
        put(9, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36");
        put(10, "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2225.0 Safari/537.36");
        put(11, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
        put(12, "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
        put(13, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        put(14, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        put(15, "Mozilla/5.0 (Windows NT 5.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
        put(16, "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
        put(17, "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
        put(18, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        put(19, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        put(20, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
        put(21, "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        put(22, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
    }};

    final static Map<Integer, String> ffUserAgentMap = new HashMap<Integer, String>()
    {{
        put(1, "Mozilla/5.0 (X11; Linux i686; rv:64.0) Gecko/20100101 Firefox/64.0");
        put(2, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:64.0) Gecko/20100101 Firefox/64.0");
        put(3, "Mozilla/5.0 (X11; Linux i586; rv:63.0) Gecko/20100101 Firefox/63.0");
        put(4, "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:63.0) Gecko/20100101 Firefox/63.0");
        put(5, "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.10; rv:62.0) Gecko/20100101 Firefox/62.0");
        put(6, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:10.0) Gecko/20100101 Firefox/62.0");
        put(7, "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.13; ko; rv:1.9.1b2) Gecko/20081201 Firefox/60.0");
        put(8, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Firefox/58.0.1");
        put(9, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/58.0");
        put(10, "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:52.59.12) Gecko/20160044 Firefox/52.59.12");
        put(11, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0");
        put(12, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1");
        put(13, "Mozilla/5.0 (Windows NT 5.1; rv:36.0) Gecko/20100101 Firefox/36.0");
        put(14, "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
        put(15, "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
        put(16, "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0");
        put(17, "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0");
        put(18, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
        put(19, "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
        put(20, "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0");
        put(21, "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
        put(22, "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0");
    }};

    final static Map<Integer, String> chVersionsMap = new HashMap<Integer, String>()
    {{
        put(1, "67.0");
        put(2, "68.0");
        put(3, "69.0");
        put(4, "70.0");
        put(5, "71.0");
        put(6, "72.0");
    }};

    public static WebDriver driver;
    public static WebDriver getInstance(ProxyObject proxyObject, String browser) throws Exception {
        String targetProxy = String.format("%s:%s", proxyObject.getProxyIp(), proxyObject.getProxyPort());
        LOG.warn(String.format("Creating instance of driver with IP : %s,%s,%s ; BROWSER : %s ;", proxyObject.getProxyIp(), proxyObject.getProxyPort(), proxyObject.getProxyType(), browser));
       if (browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",
                    "src/main/resources/chromedriverv2.46.exe");
            ChromeOptions chOptions = new ChromeOptions();
            if(proxyObject.getProxyType().equals("0")) {
                chOptions.addArguments("--proxy-server=http://" + targetProxy);
            }else if (proxyObject.getProxyType().equals("4") || proxyObject.getProxyType().equals("5")) {
                chOptions.addArguments("--proxy-server=socks5://" + targetProxy);
            }
           chOptions.addArguments(String.format("--user-agent='%s'", chUserAgentMap.get((int )(Math.random() * 21 + 1))));
           //chOptions.addArguments("--incognito");
//           Map<String, Object> preferences = new HashMap<>();
//           preferences.put("enable_do_not_track", true);
//           chOptions.setExperimentalOption("prefs",preferences);
            //options.setBinary("");
            //if (driver == null) {
            driver = new ChromeDriver(chOptions);
           //chOptions.setCapability("enableVNC", true);
           //chOptions.setCapability(CapabilityType.BROWSER_VERSION, chVersionsMap.get((int )(Math.random() * 5 + 1)));
           //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),chOptions);
            //}
        }
        else if (browser.equals("firefox")) {
           System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriverv0.24.0.exe");
           FirefoxOptions ffOptions = new FirefoxOptions();
           //FirefoxBinary firefoxBinary = new FirefoxBinary(new File("C:/Users/OYurkiv/Downloads/FirefoxPortable/FirefoxPortable.exe"));
           //desiredCapabilities.setCapability(FirefoxDriver.BINARY, "C:/Users/OYurkiv/Downloads/FirefoxPortable/FirefoxPortable.exe");
           if(proxyObject.getProxyType().equals("0")) {
//               Proxy proxy = new Proxy();
//               proxy.setHttpProxy(targetProxy)
//                       .setFtpProxy(targetProxy)
//                       .setSslProxy(targetProxy);
//               DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
//               desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);
//               desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//               desiredCapabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//               ffOptions = new FirefoxOptions(desiredCapabilities);
               ffOptions.addPreference("network.proxy.type", 1);
               ffOptions.addPreference("network.proxy.http", String.format("%s",proxyObject.getProxyIp()));
               ffOptions.addPreference("network.proxy.http_port", Integer.parseInt(proxyObject.getProxyPort()));
               ffOptions.addPreference("network.proxy.ssl", String.format("%s",proxyObject.getProxyIp()));
               ffOptions.addPreference("network.proxy.ssl_port", Integer.parseInt(proxyObject.getProxyPort()));
               ffOptions.addPreference("network.proxy.ftp", String.format("%s",proxyObject.getProxyIp()));
               ffOptions.addPreference("network.proxy.ftp_port", Integer.parseInt(proxyObject.getProxyPort()));
               ffOptions.setAcceptInsecureCerts(true);
           }else if(proxyObject.getProxyType().equals("4") || proxyObject.getProxyType().equals("5")) {
               ffOptions.addPreference("network.proxy.type", 1);
               ffOptions.addPreference("network.proxy.socks", String.format("%s",proxyObject.getProxyIp()));
               ffOptions.addPreference("network.proxy.socks_port", Integer.parseInt(proxyObject.getProxyPort()));
               ffOptions.addPreference("network.proxy.socks_version", Integer.parseInt(proxyObject.getProxyType()));
               ffOptions.setAcceptInsecureCerts(true);
           }
           ffOptions.addPreference("general.useragent.override", ffUserAgentMap.get((int )(Math.random() * 21 + 1)));
           ffOptions.addPreference("browser.privatebrowsing.autostart", true);
           //ffOptions.addPreference("privacy.trackingprotection.enabled", true);
           ffOptions.addPreference("network.captive-portal-service.enabled", false);
           //options.setBinary("C:/Users/OYurkiv/Downloads/FirefoxPortable/FirefoxPortable.exe");
           driver = new FirefoxDriver(ffOptions);
           //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),ffOptions);
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
            return driver;
    }

    public static void closeDriver(){
        if (driver != null) {
            //driver.close();
            driver.quit();
        }
    }

}
