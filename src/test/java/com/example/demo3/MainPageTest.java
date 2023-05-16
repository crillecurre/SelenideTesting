package com.example.demo3;

import com.codeborne.selenide.*;

import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Set;


import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.*;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainPageTest {
    MainPage mainPage = new MainPage();
    private static final Logger logger = LoggerFactory.getLogger(MainPageTest.class);


    @BeforeAll
    public static void setUpAll() {
        //Configuration.browserSize = "1280x800";

        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.fastSetValue = true;
        Configuration.proxyEnabled = false;
        Configuration.savePageSource = false;

    }


    @Test
    public void findCourseSyllabus() throws IOException {
        //Set the default folder for downloaded files
        Configuration.downloadsFolder = "C:/Users/Christian Söderström/IdeaProjects/demo3/target/files";

        //Opens the webpage for ltu.se
        open("https://www.ltu.se/");
        logger.info("Startup succesful");
        //Accept cookies
        mainPage.cookieButton.click();
        //Verify that we are on the right page
        String url = WebDriverRunner.url();
        assertEquals(url, "https://www.ltu.se/");

        mainPage.studentButton.shouldBe(visible).click();
        //Verify that we are on the right page
        String page = WebDriverRunner.url();
        assertEquals(page, "https://www.ltu.se/student");

        mainPage.loggaIn.shouldBe(visible).click();


           String site = WebDriverRunner.url();
           assertEquals(site, "https://weblogon.ltu.se/cas/login?service=https%3A%2F%2Fportal.ltu.se%2Fc%2Fportal%2Flogin%3Fredirect%3D%252Fgroup%252Fstudent%252Fstart%26p_l_id%3D1076063");
           logger.info ("URLs matching");

        // Read Facebook credentials from JSON file
        String email = null;
        String password = null;
        File jsonFile = new File("C:\\temp\\ltu.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();
            logger.info("Loading of credentials succesful");
        } catch (IOException e) {
            logger.error("Could not find the credentials");
        }
        try {
            //Send the credentials into the "användarid" and "lösenord" field
            mainPage.inputUsername.sendKeys(email);
            mainPage.inputPassword.sendKeys(password);
            //Press button to login
            mainPage.inputSubmit.click();
            logger.info("Succesfully logged in");
        }
        catch (Exception e) {
            logger.error("Login unsuccesful");
        }

        //Press button "Kursrum"
        mainPage.kursrum.click();

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
        //maximize the window
        getWebDriver().manage().window().maximize();


        try {
            //Press "Kurser" button in canvas when the button is visible
            mainPage.kurser.click();

            //Press on Test av IT course
            mainPage.testavit.shouldBe(visible).click();

            //Press on "Moduler" when the button is visible
            mainPage.moduler.shouldBe(visible).click();

            //Find course syllabus choice in modules
            mainPage.courseSyllabus.shouldBe(visible).click();
            logger.info ("Succesfully found button to get linked to course syllabus");
        }
        catch (Exception a) {
           logger.error("Something went wrong here");
        }

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

        //Selector for the "V23" button
        var css1 = $x("//a[@href='#' and text()='V23']");
        //Clicks on the "V23" button
        executeJavaScript("arguments[0].click()", css1);


        String expectedUrl = "https://www.ltu.se/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?kursView=kursplan&termin=V23";
        String actualUrl = driver().url();
        assertEquals(expectedUrl, actualUrl);
        logger.info("URLs are matching");


        File downloadedFile = null;
        try {
            downloadedFile = mainPage.pdf.shouldBe(visible).download();

            if (downloadedFile.exists()) {
                downloadedFile.delete();
            }
            downloadedFile.createNewFile();
            logger.info("Pressed on the button to download file");
        } catch (FileNotFoundException e) {
            logger.error("Could not find/download the file");
        }


            // verify that the file exists in the folder
            assertTrue(downloadedFile.exists());
            logger.info("The file can be find in the folder for the downloaded file");


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
        logger.info("URl is matching");


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
            logger.info("Succesfully found and read the credentials");
        } catch (IOException e) {
            logger.error("Could not find or read the credentials");

        }
        try {
        //Send the credentials into the "användarid" and "lösenord" field
        mainPage.inputUsername.sendKeys(email);
        mainPage.inputPassword.sendKeys(password);

        //Press button to login
        mainPage.inputSubmit.click();

        //Verify that it is the right url
        String site = WebDriverRunner.url();
        Assertions.assertEquals(site, "https://portal.ltu.se/group/student/start");
        logger.info("Urls are matching and the login was succesful");

        } catch (AssertionError b) {
            logger.error("The urls are not matching" + b.getMessage());
            throw b; // rethrow the exception so that the test fails
        }
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
        // maximize the window
        getWebDriver().manage().window().maximize();

        try {
            //Press "Kurser" button in canvas when the button is visible
            mainPage.kurser.shouldBe(visible).click();

            //Press on Test av IT course
            mainPage.testavit.click();

            //Press on "Moduler" when the button is visible
            mainPage.moduler.shouldBe(visible).click();

            //Press on button to get information about finalExamination
            mainPage.finalExamination.shouldBe(visible).click();

            //Take a screenshot of the current page
            File screenshot = Screenshots.takeScreenShotAsFile();

            //Save the screenshot to a file, overwrite if already existing
            FileUtils.copyFile(screenshot, new File("C:\\Users\\Christian Söderström\\IdeaProjects\\demo3\\target\\screenshots\\final_examination.jpeg"), true);

            logger.info("Successfully found the page for final examination information and saved a screenshot to target/screenshots/final_examination.jpeg");

        } catch (Exception e) {
            logger.error("Could not find the right page: " + e.getMessage());
        }


        /*The test is due to 30th of May between 9:00 and 14:00 so we want to verify that the
        Final Examination Information includes this
        */
        try {
            Assertions.assertTrue(mainPage.info.text().contains("30th"));
            Assertions.assertTrue(mainPage.info.text().contains("May"));
            Assertions.assertTrue(mainPage.info.text().contains("9:00"));
            Assertions.assertTrue(mainPage.info.text().contains("14:00"));
            logger.info("Final examination information contains the correct date and time");
        } catch (AssertionError e) {
            logger.error("Final examination information does not contain the correct date and time: " + e.getMessage());
            throw e; // rethrow the exception so that the test fails
        }

    }



    @Test
    void studentTranscript () throws IOException {

        Configuration.downloadsFolder = "C:/Users/Christian Söderström/IdeaProjects/demo3/target/files";


        //Opens the webpage for ltu.se
        open("https://www.ltu.se/");

        //Accept cookies
        mainPage.cookieButton.click();

        //Verify that we are on the right page
        String url = WebDriverRunner.url();
        Assertions.assertEquals(url, "https://www.ltu.se/");
        logger.info("URl is matching");

        mainPage.studentButton.shouldBe(visible).click();

        //Verify that we are on the right page
        String page = WebDriverRunner.url();
        Assertions.assertEquals(page, "https://www.ltu.se/student");

        mainPage.loggaIn.shouldBe(visible).click();

        //Read Facebook credentials from JSON file
        String email = null;
        String password = null;
        File jsonFile = new File("C:\\temp\\ltu.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();
            logger.info("Fetching credentials succesful");
        } catch (IOException e) {
            logger.error("Could not load the credentials from the file");
        }

        try {
            //Send the credentials into the "användarid" and "lösenord" field
            mainPage.inputUsername.sendKeys(email);
            mainPage.inputPassword.sendKeys(password);

            //Press button to login
            mainPage.inputSubmit.click();

            //Verify that it is the right url
            String site = WebDriverRunner.url();
            Assertions.assertEquals(site, "https://portal.ltu.se/group/student/start");
            logger.info("Urls are matching and the login was succesful");

        } catch (AssertionError b) {
            logger.error("The urls are not matching" + b.getMessage());
            throw b; // rethrow the exception so that the test fails
        }

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


        try {
            // Press that you want to login to Ladok
            mainPage.ladokInlog.shouldBe(visible).click();

            // Click on the search box
            mainPage.uniSearch.shouldBe(visible).click();

            // Set input to the search box
            mainPage.uniSearch.sendKeys("lule");

            // Press on the choice that comes up (Luleå University)
            mainPage.selectLule.shouldBe(visible).click();

            // Presses Menu in the right upper corner
            mainPage.ladokMenu.shouldBe(visible).click();

            // Presses the button to come to transcripts
            mainPage.certificates.shouldBe(visible).click();

            logger.info("Successfully navigated to Ladok transcripts");
        } catch (Exception e) {
            logger.error("Failed to navigate to Ladok transcripts: " + e.getMessage());
        }



        //Download the student transcript, overwrite if already existing
        File downloadedFile = mainPage.downloadTranscript2.download();
        if (downloadedFile.exists()) {
            downloadedFile.delete();
        }
        downloadedFile.createNewFile();

        //verify that the file exists in the folder
        Assertions.assertTrue(downloadedFile.exists());
            logger.info("The downloaded file exists in the desired folder");

    }


    @Test
    void createTranscript() {


        //Opens the webpage for ltu.se
        open("https://www.ltu.se/");

        //Accept cookies
        mainPage.cookieButton.click();
        //Verify that we are on the right page
        String url = WebDriverRunner.url();
        Assertions.assertEquals(url, "https://www.ltu.se/");
        logger.info("Urls are matching");

        mainPage.studentButton.shouldBe(visible).click();
        //Verify that we are on the right page
        String page = WebDriverRunner.url();
        Assertions.assertEquals(page, "https://www.ltu.se/student");
        logger.info("Urls are matching");

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
            logger.info("Fetching credentials succesful");
        } catch (IOException e) {
            logger.error("Could not load the credentials from the file");
        }

        try {
            //Send the credentials into the "användarid" and "lösenord" field
            mainPage.inputUsername.sendKeys(email);
            mainPage.inputPassword.sendKeys(password);

            //Press button to login
            mainPage.inputSubmit.click();


            //Verify that it is the right url
            String site = WebDriverRunner.url();
            Assertions.assertEquals(site, "https://portal.ltu.se/group/student/start");
            logger.info("Urls are matching and the login was succesful");

        } catch (AssertionError b) {
            logger.error("The urls are not matching" + b.getMessage());
            throw b; // rethrow the exception so that the test fails
        }


        //Press on button "intyg"
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
        try {
            // Press that you want to login to Ladok
            mainPage.ladokInlog.shouldBe(visible).click();

            // Click on the search box
            mainPage.uniSearch.shouldBe(visible).click();

            // Set input to the search box
            mainPage.uniSearch.sendKeys("lule");

            // Press on the choice that comes up (Luleå University)
            mainPage.selectLule.shouldBe(visible).click();

            // Presses Menu in the right upper corner
            mainPage.ladokMenu.shouldBe(visible).click();

            // Presses the button to come to transcripts
            mainPage.certificates.shouldBe(visible).click();

            logger.info("Successfully navigated to Ladok transcripts");
        } catch (Exception e) {
            logger.error("Failed to navigate to Ladok transcripts: " + e.getMessage());
        }

        try {
            //Press on button to create certificate
            mainPage.createTranscript.shouldBe(visible).click();

            //Clicks on the menu to select which type of certificate you want to create
            mainPage.selectIntygstyp.shouldBe(visible).click();

            //Chooses official transcripts of records
            mainPage.records.shouldBe(visible).click();

            //Holds for 2 seconds
            sleep(2000);

            //Clicks on the cookie button so it disappears
            mainPage.cookieButton2.shouldBe(visible).click();

            // Scroll to the element and click to create a certificate
            executeJavaScript("arguments[0].scrollIntoView(true);", mainPage.buttonCreate2);
            mainPage.buttonCreate2.shouldBe(visible).click();

            logger.info("Successfully created certificate");

        } catch (Exception e) {
            logger.error("Error creating certificate: " + e.getMessage());
        }

    }

}
