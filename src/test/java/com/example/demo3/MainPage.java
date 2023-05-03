package com.example.demo3;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

// page_url = https://www.jetbrains.com/
public class MainPage {

    public SelenideElement cookieButton = $x("//*[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']");

    public SelenideElement buttonSearch = $x("//button[contains(@class, 'ltu-search-btn')]");
    public SelenideElement searchField =$x("//input[@type='search']");

    //public SelenideElement linkKursplan = $("a[href='https://www.ltu.se/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?termin=V23'][title='Våren 2024']");

    public SelenideElement focusMouseLink = $("a[class='focus--mouse']");

    public SelenideElement spanRen = $x("/html/body/main/div/div/div/div[2]/div/article/div[1]/section/div[4]/ul/li[2]/a/span[2]");

    public SelenideElement linkKursplan = $x("//a[contains(@href, '/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?termin=V23&kursView=kursplan')]");

    public SelenideElement kursplanLink = $(By.linkText("Kursplan"));

    public SelenideElement liKurspla = $x("/html/body/main/div/div/div/div[1]/nav/div[1]/ul/li[0]");

    public SelenideElement linkKursplan2 = $(byText("\n                                    Kursplan\n                                                                    "));

    public SelenideElement studentButton = $x("//a[text()='Student']");
    //public SelenideElement kursplanButton= $x("//a[normalize-space(@title)='Kursplan']");

    public SelenideElement kursplanButton = $("a.menu-link-text[href*='kursView=kursplan']");


    SelenideElement loginLink = $(By.cssSelector("a[href='https://portal.ltu.se/group/student/start'][class='button is-medium is-blue'][text()='Logga in']"));

    public SelenideElement linkLogga = $("a[class*='button']");

    public SelenideElement inputUsername = $x("//*[@id='username']");

    public SelenideElement inputPassword = $x("//*[@id='password']");

    public SelenideElement inputSubmit = $x("//input[@tabindex='6']");

    public SelenideElement kursrum = $x("//a[@title='Kurser i Canvas']");

    //public SelenideElement kurser = $x("//li[@class='menu-item ic-app-header__menu-list-item ic-app-header__menu-list-item--active']/button[@id='global_nav_courses_link']");

    //public SelenideElement kurser = $x("//button[contains(@id, 'global_nav_courses_link')]");

    public SelenideElement testavit = $x("/html/body/div[4]/span/span/div/div/div/div/div/ul[1]/li[8]/a");

    public SelenideElement moduler = $x("//a[@class='modules']");

    public SelenideElement finalExamination = $x("//a[contains(@title, 'Final')]");

    public SelenideElement info = $x("//div[contains(@class, 'clearfix')]");

    public SelenideElement loggaIn = $x("//div[contains(@class, 'is-6-tablet')][.//img[contains(@alt, 'Man,')]]");

    public SelenideElement kursMeny = $x("//div[@class='fOyUs_bGBk']");
    //public SelenideElement kurser = $x("/html/body/div[3]/header[2]/div[1]/ul/li[3]/button/div[1]/svg");

    //public SelenideElement kurser = $x("//div[@class='menu-item__text' and text()='Kurser']");

    public SelenideElement courseSyllabus = $x("/html/body/div[3]/div[2]/div[2]/div[3]/div[1]/div/div[4]/div[2]/div[1]/div[2]/ul/li[10]/div/div[1]/div[1]/span/a/span[1]");

    public SelenideElement firstLink = $x("//a[@href='#'][@aria-selected='false'][@data-termin-text='V23'][1]");

    public SelenideElement h4SelectSemesterHeader = $x("//*[@id='select-semester-header']");

    public SelenideElement kurser = $("button[id*='courses']");

    public SelenideElement pdf = $x("//img[@alt='PDF']");

    //SelenideElement v23 = $x("//select[@name='termin']/option[@value='V23']");


    public SelenideElement courseText = $x("//h2[contains(text(), 'Test av IT-system, 7,5 högskolepoäng')]");

    public SelenideElement intygButton = $x("//a[contains(@href, 'intyg')]");

    //public SelenideElement ladokInlog = $x("//a[contains(@aria-label, 'your')]");

    public SelenideElement uniSearch = $x("//input[@id='searchinput']");

    public SelenideElement selectLule = $x("//li[contains(@aria-label, 'University')]");

    public SelenideElement ladokMenu = $x("//button[contains(@aria-label, 'Meny')]");

    public SelenideElement ladokInlog = $x("//a[@href='/student/login?ret=/app/studentwebb']");

    public SelenideElement certificates = $x("//a[contains(@href, 'intyg')]");

    public SelenideElement downloadTranscript = $x("//a[@href='https://www.student.ladok.se/student/proxy/extintegration/internal/intyg/2ee4d0ab-e8b7-11ed-9037-c7d987484aba/pdf']");







//input[@id='searchinput']
    //a[contains(@aria-label, 'your')]




















}

