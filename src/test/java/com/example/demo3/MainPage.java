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

    public SelenideElement studentButton = $x("//a[text()='Student']");


    public SelenideElement inputUsername = $x("//*[@id='username']");

    public SelenideElement inputPassword = $x("//*[@id='password']");

    public SelenideElement inputSubmit = $x("//input[@tabindex='6']");

    public SelenideElement kursrum = $x("//a[@title='Kurser i Canvas']");


    public SelenideElement testavit = $x("/html/body/div[4]/span/span/div/div/div/div/div/ul[1]/li[8]/a");

    public SelenideElement moduler = $x("//a[@class='modules']");

    public SelenideElement finalExamination = $x("//a[contains(@title, 'Final')]");

    public SelenideElement info = $x("//div[contains(@class, 'clearfix')]");

    public SelenideElement loggaIn = $x("//div[contains(@class, 'is-6-tablet')][.//img[contains(@alt, 'Man,')]]");



    public SelenideElement courseSyllabus = $x("/html/body/div[3]/div[2]/div[2]/div[3]/div[1]/div/div[4]/div[2]/div[1]/div[2]/ul/li[10]/div/div[1]/div[1]/span/a/span[1]");



    public SelenideElement intygButton = $x("//a[contains(@href, 'intyg')]");


    public SelenideElement uniSearch = $x("//input[@id='searchinput']");

    public SelenideElement selectLule = $x("//li[contains(@aria-label, 'University')]");

    public SelenideElement ladokMenu = $x("//button[contains(@aria-label, 'Meny')]");

    public SelenideElement ladokInlog = $x("//a[@href='/student/login?ret=/app/studentwebb']");

    public SelenideElement certificates = $x("//a[contains(@href, 'intyg')]");


    public SelenideElement createTranscript = $x("//button[@title='Skapa intyg']");

    public SelenideElement selectIntygstyp = $x("//*[@id='intygstyp']");

    public SelenideElement records = $x("//option[@value='1: Object']");


    public SelenideElement buttonCreate2 = $("html > body > ladok-root > div > main > div > ladok-skapa-intyg > ladok-card > div > div > ladok-card-body > div:nth-of-type(3) > div > form > div:nth-of-type(3) > div > ladok-skapa-intyg-knapprad > div > button:nth-of-type(1)");

    public SelenideElement cookieButton2 = $x("//button[@class='btn btn-light' and text()='Jag förstår']");

    public SelenideElement downloadTranscript2 = $x("//a[@href='https://www.student.ladok.se/student/proxy/extintegration/internal/intyg/d23104ce-e995-11ed-9037-c7d987484aba/pdf']");


    public SelenideElement pdf = $x("//a[@class='utbplan-pdf-link']");


    public SelenideElement kurser = $x("//*[@id='global_nav_courses_link']");

}

