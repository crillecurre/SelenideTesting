package com.example.demo3;

import com.codeborne.selenide.*;
import com.google.common.collect.ImmutableMap;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.SetValueOptions.withText;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.*;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainPageTest {
    MainPage mainPage = new MainPage();


    @BeforeAll
    static void setup() {
        Configuration.holdBrowserOpen = true;
    }

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");

    }


    @Test
    public void findCourseSyllabus() {
        //Opens the webpage for ltu.se
        open("https://www.ltu.se/");

        //Accept cookies
        mainPage.cookieButton.click();
        //Verify that we are on the right page
        String url = WebDriverRunner.url();
        Assertions.assertEquals(url, "https://www.ltu.se/");

        mainPage.studentButton.shouldBe(visible).click();
        //Verify that we are on the right page
        String page = WebDriverRunner.url();
        Assertions.assertEquals(page, "https://www.ltu.se/student");

        mainPage.loggaIn.shouldBe(visible).click();

        // Read Facebook credentials from JSON file
        String email = null;
        String password = null;
        File jsonFile = new File("C:\\temp\\ltu.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();
        } catch (IOException e) {

        }

        //Send the credentials into the "användarid" and "lösenord" field
        mainPage.inputUsername.sendKeys(email);
        mainPage.inputPassword.sendKeys(password);

        //Press button to login
        mainPage.inputSubmit.click();

        //Press button "Kursrum"
        mainPage.kursrum.click();


        // Get the window handle of the main window
        /*String mainWindowHandle = WebDriverRunner.getWebDriver().getWindowHandle();

// Get the handles of all windows currently open
        Set<String> allWindowHandles = WebDriverRunner.getWebDriver().getWindowHandles();

// Switch to the popup window
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                WebDriverRunner.getWebDriver().switchTo().window(windowHandle);
                break;
            }
        }*/

        // Get the window handle of the main window
        String mainWindowHandle = getWebDriver().getWindowHandle();

// Get the handles of all windows currently open
        Set<String> allWindowHandles = getWebDriver().getWindowHandles();

// Switch to the first popup window
        String firstPopupWindowHandle = "";
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                firstPopupWindowHandle = windowHandle;
                getWebDriver().switchTo().window(firstPopupWindowHandle);
                break;
            }
        }

        //Press "Kurser" button in canvas when the button is visible

        mainPage.kurser.click();
        //mainPage.kurser.shouldBe(visible).click();

        //Press on Test av IT course
        mainPage.testavit.shouldBe(visible).click();

        //Press on "Moduler" when the button is visible
        mainPage.moduler.shouldBe(visible).click();

        //Find course syllabus choice in modules
        mainPage.courseSyllabus.shouldBe(visible).click();


// Switch to the second popup window
        String maiWindowHandle = getWebDriver().getWindowHandle();
        Set<String> alWindowHandles = getWebDriver().getWindowHandles();
        String secondPopupWindowHandle = "";
        for (String windowHandle : alWindowHandles) {
            if (!windowHandle.equals(maiWindowHandle) && !windowHandle.equals(mainWindowHandle)) {
                secondPopupWindowHandle = windowHandle;
                getWebDriver().switchTo().window(secondPopupWindowHandle);
                break;
            }
        }


        //open("https://www.ltu.se/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?kursView=kursplan&termin=V23)";
        //mainPage.pdf.click();
    }













    @Test
    void finalExaminationInformation() {

        //Opens the webpage for ltu.se
        open("https://www.ltu.se/");

        //Accept cookies
        mainPage.cookieButton.click();
        //Verify that we are on the right page
        String url = WebDriverRunner.url();
        Assertions.assertEquals(url, "https://www.ltu.se/");

        mainPage.studentButton.shouldBe(visible).click();
        //Verify that we are on the right page
        String page = WebDriverRunner.url();
        Assertions.assertEquals(page, "https://www.ltu.se/student");

        mainPage.loggaIn.shouldBe(visible).click();

        // Read Facebook credentials from JSON file
        String email = null;
        String password = null;
        File jsonFile = new File("C:\\temp\\ltu.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();
        } catch (IOException e) {

        }

        //Send the credentials into the "användarid" and "lösenord" field
        mainPage.inputUsername.sendKeys(email);
        mainPage.inputPassword.sendKeys(password);

        //Press button to login
        mainPage.inputSubmit.click();

        //Press button "Kursrum"
        mainPage.kursrum.click();


        // Get the window handle of the main window
        String mainWindowHandle = getWebDriver().getWindowHandle();

// Get the handles of all windows currently open
        Set<String> allWindowHandles = getWebDriver().getWindowHandles();

// Switch to the popup window
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                getWebDriver().switchTo().window(windowHandle);
                break;
            }
        }

        //Press "Kurser" button in canvas when the button is visible
        mainPage.kurser.shouldBe(visible).click();

        //Press on Test av IT course
        mainPage.testavit.click();

        //Press on "Moduler" when the button is visible
        mainPage.moduler.shouldBe(visible).click();

        //Press on button to get information about finalExamination
        mainPage.finalExamination.shouldBe(visible).click();

        /*The test is due to 30th of May between 9:00 and 14:00 so we want to verify that the
        Final Examination Information includes this
        */
        Assertions.assertTrue(mainPage.info.text().contains("30th"));
        Assertions.assertTrue(mainPage.info.text().contains("May"));
        Assertions.assertTrue(mainPage.info.text().contains("9:00"));
        Assertions.assertTrue(mainPage.info.text().contains("14:00"));


    }

    /*@Test
    void tryexamination() {
        Configuration.timeout = 5000;
        open("https://www.ltu.se/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?kursView=kursplan");

        // open("https://www.ltu.se/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215");

        mainPage.cookieButton.click();

        var css1 = $x("//a[@href='#' and text()='V23']");
        css1
                .shouldBe(visible)
                .click();
    }*/












    @Test
    void testingCourse() throws InterruptedException {


        Configuration.timeout = 4000;
        open("https://www.ltu.se/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?kursView=kursplan");
        mainPage.cookieButton.click();
        Thread.sleep(2000);

        for (int i = 1; i <= 100; i++) {
            System.out.println("Iteration number: " + i);
            open("https://www.ltu.se/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?kursView=kursplan");
            // Thread.sleep(1000);
            //getWebDriver().manage().window().maximize();
            //Thread.sleep(2000);
            var css1 = $x("//a[@href='#' and text()='V23']");
            css1
                    .shouldBe(visible)
                    .click();

            String expectedUrl = "https://www.ltu.se/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?kursView=kursplan&termin=V23";
            String actualUrl = driver().url();
            Assertions.assertEquals(expectedUrl, actualUrl);

        }
    }






































    @Test
    void studentTranscript () throws IOException {


        Configuration.downloadsFolder = "C:/Users/Christian Söderström/IdeaProjects/demo3/target/Intyg.pdf";


        //Opens the webpage for ltu.se
        open("https://www.ltu.se/");

        //Accept cookies
        mainPage.cookieButton.click();
        //Verify that we are on the right page
        String url = WebDriverRunner.url();
        Assertions.assertEquals(url, "https://www.ltu.se/");

        mainPage.studentButton.shouldBe(visible).click();
        //Verify that we are on the right page
        String page = WebDriverRunner.url();
        Assertions.assertEquals(page, "https://www.ltu.se/student");

        mainPage.loggaIn.shouldBe(visible).click();

        // Read Facebook credentials from JSON file
        String email = null;
        String password = null;
        File jsonFile = new File("C:\\temp\\ltu.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();
        } catch (IOException e) {

        }

        //Send the credentials into the "användarid" and "lösenord" field
        mainPage.inputUsername.sendKeys(email);
        mainPage.inputPassword.sendKeys(password);

        //Press button to login
        mainPage.inputSubmit.click();

        //Press on button intyg
        mainPage.intygButton.shouldBe(visible).click();

        // Get the window handle of the main window
        String mainWindowHandle = getWebDriver().getWindowHandle();

// Get the handles of all windows currently open
        Set<String> allWindowHandles = getWebDriver().getWindowHandles();

// Switch to the popup window
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                getWebDriver().switchTo().window(windowHandle);
                break;
            }
        }
        //Press that you want to login to Ladok
        mainPage.ladokInlog.shouldBe(visible).click();

        //Click on the search box
        mainPage.uniSearch.shouldBe(visible).click();

        //Set input to the search box
        mainPage.uniSearch.sendKeys("lule");

        //Press on the choice that comes up (Luleå University)
        mainPage.selectLule.shouldBe(visible).click();

        //Presses Menu in the right upper corner
        mainPage.ladokMenu.shouldBe(visible).click();

        //Presses the button to come to transcripts
        mainPage.certificates.shouldBe(visible).click();


        //Download the student transcript
        File downloadedFile = mainPage.downloadTranscript.download();
        if (downloadedFile.exists()) {
            downloadedFile.delete();
        }
        downloadedFile.createNewFile();

        // wait for the file to be downloaded
        //File downloadedFile = new File(Configuration.downloadsFolder + "/Intyg.pdf");


        // verify that the file exists in the folder

        Assertions.assertTrue(downloadedFile.exists());

    }


    @Test
    void createTranscript() throws InterruptedException {

        Configuration.downloadsFolder = "C:/Users/Christian Söderström/IdeaProjects/demo3/target/Intyg.pdf";


        //Opens the webpage for ltu.se
        open("https://www.ltu.se/");

        //Accept cookies
        mainPage.cookieButton.click();
        //Verify that we are on the right page
        String url = WebDriverRunner.url();
        Assertions.assertEquals(url, "https://www.ltu.se/");

        mainPage.studentButton.shouldBe(visible).click();
        //Verify that we are on the right page
        String page = WebDriverRunner.url();
        Assertions.assertEquals(page, "https://www.ltu.se/student");

        mainPage.loggaIn.shouldBe(visible).click();

        // Read Facebook credentials from JSON file
        String email = null;
        String password = null;
        File jsonFile = new File("C:\\temp\\ltu.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();
        } catch (IOException e) {

        }

        //Send the credentials into the "användarid" and "lösenord" field
        mainPage.inputUsername.sendKeys(email);
        mainPage.inputPassword.sendKeys(password);

        //Press button to login
        mainPage.inputSubmit.click();

        //Press on button intyg
        mainPage.intygButton.shouldBe(visible).click();

        // Get the window handle of the main window
        String mainWindowHandle = getWebDriver().getWindowHandle();

// Get the handles of all windows currently open
        Set<String> allWindowHandles = getWebDriver().getWindowHandles();

// Switch to the popup window
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                getWebDriver().switchTo().window(windowHandle);
                break;
            }
        }
        //Press that you want to login to Ladok
        mainPage.ladokInlog.shouldBe(visible).click();

        //Click on the search box
        mainPage.uniSearch.shouldBe(visible).click();

        //Set input to the search box
        mainPage.uniSearch.sendKeys("lule");

        //Press on the choice that comes up (Luleå University)
        mainPage.selectLule.shouldBe(visible).click();

        //Presses Menu in the right upper corner
        mainPage.ladokMenu.shouldBe(visible).click();

        //Presses the button to come to transcripts
        mainPage.certificates.shouldBe(visible).click();

        //Press on button to create certificate
        mainPage.createTranscript.shouldBe(visible).click();

        //Clicks on the menu to select which type of certificate you want to create
        mainPage.selectIntygstyp.shouldBe(visible).click();

        //Chooses official transcripts of records
        mainPage.records.shouldBe(visible).click();

        //Holds for 2 seconds
        Thread.sleep(2000);

        //Clicks on the cookie button so it disappears
        mainPage.cookieButton2.shouldBe(visible).click();

        // Scroll to the element and click to create a certificate
        executeJavaScript("arguments[0].scrollIntoView(true);", mainPage.buttonCreate2);
        mainPage.buttonCreate2.shouldBe(visible).click();


    }
}




































