import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class page {
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;

    @FindBy(css = "a[href='https://github.com/about']")
    @CacheLookup
    private WebElement about;

    @FindBy(css = "a[href='https://developer.github.com']")
    @CacheLookup
    private WebElement api;

    @FindBy(css = "a[href='/blog']")
    @CacheLookup
    private WebElement blog1;

    @FindBy(css = "a[href='https://github.com/blog']")
    @CacheLookup
    private WebElement blog2;

    @FindBy(css = "a.js-selected-navigation-item.nav-item.nav-item-business")
    @CacheLookup
    private WebElement business;

    @FindBy(css = "a.text-purple.octicon-middle")
    @CacheLookup
    private WebElement businesses;

    @FindBy(css = "a.text-orange.octicon-middle")
    @CacheLookup
    private WebElement communities;

    @FindBy(css = "a[href='https://github.com/contact']")
    @CacheLookup
    private WebElement contactGithub;

    @FindBy(name = "q")
    @CacheLookup
    private WebElement createAPassword1;

    @FindBy(id = "user[password]")
    @CacheLookup
    private WebElement createAPassword2;

    @FindBy(id = "user[email]")
    @CacheLookup
    private WebElement enterYourEmailAddress;

    @FindBy(css = "a.js-selected-navigation-item.nav-item.nav-item-explore")
    @CacheLookup
    private WebElement explore;

    @FindBy(css = "-font div:nth-of-type(5) div.site-footer ul:nth-of-type(2) li:nth-of-type(6) a")
    @CacheLookup
    private WebElement help;

    @FindBy(css = "a.text-blue.octicon-middle")
    @CacheLookup
    private WebElement individuals;

    @FindBy(css = "a[href='//github.com/nasa']")
    @CacheLookup
    private WebElement nasa;

    @FindBy(css = "a.js-selected-navigation-item.nav-item.nav-item-opensource")
    @CacheLookup
    private WebElement openSource;

    private final String pageLoadedText = "Millions of developers use GitHub to build personal projects, support their businesses, and&nbsp;work together on open source technologies";

    private final String pageUrl = "/";

    @FindBy(css = "a.js-selected-navigation-item.nav-item.nav-item-personal")
    @CacheLookup
    private WebElement personal;

    @FindBy(id = "user[login]")
    @CacheLookup
    private WebElement pickAUsername;

    @FindBy(css = "a[href='/pricing']")
    @CacheLookup
    private WebElement pricing;

    @FindBy(css = "a[href='https://github.com/site/privacy']")
    @CacheLookup
    private WebElement privacy;

    @FindBy(css = "a[href='https://help.github.com/privacy']")
    @CacheLookup
    private WebElement privacyPolicy;

    @FindBy(css = "-font div:nth-of-type(7) span:nth-of-type(1) a")
    @CacheLookup
    private WebElement reload1;

    @FindBy(css = "-font div:nth-of-type(7) span:nth-of-type(2) a")
    @CacheLookup
    private WebElement reload2;

    @FindBy(css = "a.nav-item.header-search-link")
    @CacheLookup
    private WebElement searchGithub;

    @FindBy(css = "a[href='https://github.com/security']")
    @CacheLookup
    private WebElement security;

    @FindBy(css = "a[href='https://shop.github.com']")
    @CacheLookup
    private WebElement shop;

    @FindBy(css = "a.btn.site-header-actions-btn.mr-1")
    @CacheLookup
    private WebElement signIn;

    @FindBy(css = "a.btn.btn-primary.site-header-actions-btn")
    @CacheLookup
    private WebElement signUp;

    @FindBy(css = "button.btn.btn-primary.btn-large.f3.btn-block")
    @CacheLookup
    private WebElement signUpForGithub1;

    @FindBy(css = "-font div:nth-of-type(4) div:nth-of-type(1) div.container-responsive.position-relative div.clearfix.gut-lg div:nth-of-type(2) div:nth-of-type(2) a.btn.btn-primary.btn-large")
    @CacheLookup
    private WebElement signUpForGithub2;

    @FindBy(css = "a.btn.btn-block.btn-primary.btn-large")
    @CacheLookup
    private WebElement signUpForGithub3;

    @FindBy(css = "a.accessibility-aid.js-skip-to-content")
    @CacheLookup
    private WebElement skipToContent;

    @FindBy(css = "a[href='https://status.github.com/']")
    @CacheLookup
    private WebElement status;

    @FindBy(css = "-font header.site-header.js-details-container div.container-responsive div.site-header-menu nav:nth-of-type(2) a:nth-of-type(3)")
    @CacheLookup
    private WebElement support;

    @FindBy(css = "a[href='https://github.com/site/terms']")
    @CacheLookup
    private WebElement terms;

    @FindBy(css = "a[href='https://help.github.com/terms']")
    @CacheLookup
    private WebElement termsOfService;

    @FindBy(css = "a[href='https://training.github.com']")
    @CacheLookup
    private WebElement training;

    public page() {
    }

    public page(WebDriver driver) {
        this();
        this.driver = driver;
    }

    public page(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public page(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Click on About Link.
     *
     * @return the page class instance.
     */
    public page clickAboutLink() {
        about.click();
        return this;
    }

    /**
     * Click on Api Link.
     *
     * @return the page class instance.
     */
    public page clickApiLink() {
        api.click();
        return this;
    }

    /**
     * Click on Blog Link.
     *
     * @return the page class instance.
     */
    public page clickBlog1Link() {
        blog1.click();
        return this;
    }

    /**
     * Click on Blog Link.
     *
     * @return the page class instance.
     */
    public page clickBlog2Link() {
        blog2.click();
        return this;
    }

    /**
     * Click on Business Link.
     *
     * @return the page class instance.
     */
    public page clickBusinessLink() {
        business.click();
        return this;
    }

    /**
     * Click on Businesses Link.
     *
     * @return the page class instance.
     */
    public page clickBusinessesLink() {
        businesses.click();
        return this;
    }

    /**
     * Click on Communities Link.
     *
     * @return the page class instance.
     */
    public page clickCommunitiesLink() {
        communities.click();
        return this;
    }

    /**
     * Click on Contact Github Link.
     *
     * @return the page class instance.
     */
    public page clickContactGithubLink() {
        contactGithub.click();
        return this;
    }

    /**
     * Click on Explore Link.
     *
     * @return the page class instance.
     */
    public page clickExploreLink() {
        explore.click();
        return this;
    }

    /**
     * Click on Help Link.
     *
     * @return the page class instance.
     */
    public page clickHelpLink() {
        help.click();
        return this;
    }

    /**
     * Click on Individuals Link.
     *
     * @return the page class instance.
     */
    public page clickIndividualsLink() {
        individuals.click();
        return this;
    }

    /**
     * Click on Nasa Link.
     *
     * @return the page class instance.
     */
    public page clickNasaLink() {
        nasa.click();
        return this;
    }

    /**
     * Click on Open Source Link.
     *
     * @return the page class instance.
     */
    public page clickOpenSourceLink() {
        openSource.click();
        return this;
    }

    /**
     * Click on Personal Link.
     *
     * @return the page class instance.
     */
    public page clickPersonalLink() {
        personal.click();
        return this;
    }

    /**
     * Click on Pricing Link.
     *
     * @return the page class instance.
     */
    public page clickPricingLink() {
        pricing.click();
        return this;
    }

    /**
     * Click on Privacy Link.
     *
     * @return the page class instance.
     */
    public page clickPrivacyLink() {
        privacy.click();
        return this;
    }

    /**
     * Click on Privacy Policy Link.
     *
     * @return the page class instance.
     */
    public page clickPrivacyPolicyLink() {
        privacyPolicy.click();
        return this;
    }

    /**
     * Click on Reload Link.
     *
     * @return the page class instance.
     */
    public page clickReload1Link() {
        reload1.click();
        return this;
    }

    /**
     * Click on Reload Link.
     *
     * @return the page class instance.
     */
    public page clickReload2Link() {
        reload2.click();
        return this;
    }

    /**
     * Click on Search Github Link.
     *
     * @return the page class instance.
     */
    public page clickSearchGithubLink() {
        searchGithub.click();
        return this;
    }

    /**
     * Click on Security Link.
     *
     * @return the page class instance.
     */
    public page clickSecurityLink() {
        security.click();
        return this;
    }

    /**
     * Click on Shop Link.
     *
     * @return the page class instance.
     */
    public page clickShopLink() {
        shop.click();
        return this;
    }

    /**
     * Click on Sign In Link.
     *
     * @return the page class instance.
     */
    public page clickSignInLink() {
        signIn.click();
        return this;
    }

    /**
     * Click on Sign Up For Github Link.
     *
     * @return the page class instance.
     */
    public page clickSignUpForGithub1Link() {
        signUpForGithub1.click();
        return this;
    }

    /**
     * Click on Sign Up For Github Link.
     *
     * @return the page class instance.
     */
    public page clickSignUpForGithub2Link() {
        signUpForGithub2.click();
        return this;
    }

    /**
     * Click on Sign Up For Github Link.
     *
     * @return the page class instance.
     */
    public page clickSignUpForGithub3Link() {
        signUpForGithub3.click();
        return this;
    }

    /**
     * Click on Sign Up Link.
     *
     * @return the page class instance.
     */
    public page clickSignUpLink() {
        signUp.click();
        return this;
    }

    /**
     * Click on Skip To Content Link.
     *
     * @return the page class instance.
     */
    public page clickSkipToContentLink() {
        skipToContent.click();
        return this;
    }

    /**
     * Click on Status Link.
     *
     * @return the page class instance.
     */
    public page clickStatusLink() {
        status.click();
        return this;
    }

    /**
     * Click on Support Link.
     *
     * @return the page class instance.
     */
    public page clickSupportLink() {
        support.click();
        return this;
    }

    /**
     * Click on Terms Link.
     *
     * @return the page class instance.
     */
    public page clickTermsLink() {
        terms.click();
        return this;
    }

    /**
     * Click on Terms Of Service Link.
     *
     * @return the page class instance.
     */
    public page clickTermsOfServiceLink() {
        termsOfService.click();
        return this;
    }

    /**
     * Click on Training Link.
     *
     * @return the page class instance.
     */
    public page clickTrainingLink() {
        training.click();
        return this;
    }

    /**
     * Fill every fields in the page.
     *
     * @return the page class instance.
     */
    public page fill() {
        setCreateAPassword1PasswordField();
        setPickAUsernameTextField();
        setEnterYourEmailAddressTextField();
        setCreateAPassword2PasswordField();
        return this;
    }

    /**
     * Fill every fields in the page and submit it to target page.
     *
     * @return the page class instance.
     */
    public page fillAndSubmit() {
        fill();
        return submit();
    }

    /**
     * Set default value to Create A Password Password field.
     *
     * @return the page class instance.
     */
    public page setCreateAPassword1PasswordField() {
        return setCreateAPassword1PasswordField(data.get("CREATE_A_PASSWORD_1"));
    }

    /**
     * Set value to Create A Password Password field.
     *
     * @return the page class instance.
     */
    public page setCreateAPassword1PasswordField(String createAPassword1Value) {
        createAPassword1.sendKeys(createAPassword1Value);
        return this;
    }

    /**
     * Set default value to Create A Password Password field.
     *
     * @return the page class instance.
     */
    public page setCreateAPassword2PasswordField() {
        return setCreateAPassword2PasswordField(data.get("CREATE_A_PASSWORD_2"));
    }

    /**
     * Set value to Create A Password Password field.
     *
     * @return the page class instance.
     */
    public page setCreateAPassword2PasswordField(String createAPassword2Value) {
        createAPassword2.sendKeys(createAPassword2Value);
        return this;
    }

    /**
     * Set default value to Enter Your Email Address Text field.
     *
     * @return the page class instance.
     */
    public page setEnterYourEmailAddressTextField() {
        return setEnterYourEmailAddressTextField(data.get("ENTER_YOUR_EMAIL_ADDRESS"));
    }

    /**
     * Set value to Enter Your Email Address Text field.
     *
     * @return the page class instance.
     */
    public page setEnterYourEmailAddressTextField(String enterYourEmailAddressValue) {
        enterYourEmailAddress.sendKeys(enterYourEmailAddressValue);
        return this;
    }

    /**
     * Set default value to Pick A Username Text field.
     *
     * @return the page class instance.
     */
    public page setPickAUsernameTextField() {
        return setPickAUsernameTextField(data.get("PICK_A_USERNAME"));
    }

    /**
     * Set value to Pick A Username Text field.
     *
     * @return the page class instance.
     */
    public page setPickAUsernameTextField(String pickAUsernameValue) {
        pickAUsername.sendKeys(pickAUsernameValue);
        return this;
    }

    /**
     * Submit the form to target page.
     *
     * @return the page class instance.
     */
    public page submit() {
        clickSignUpForGithubButton();
        return this;
    }

    /**
     * Verify that the page loaded completely.
     *
     * @return the page class instance.
     */
    public page verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the page class instance.
     */
    public page verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }
}
