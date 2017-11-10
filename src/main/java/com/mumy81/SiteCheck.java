/*  Name 	   : n11.com Favorilerim,�r�n Favorilere Ekleme ve Kald�rma Test Otomasyonu
 *  Description: Bu class ile http://www.n11.com ' da uygun email ve parola ile�ye giri�i yap�l�r
 *  ve verilen kelimede arama yap�l�r. 2. sayfadaki 3. �r�n�n favori ekle butonuna bas�l�r.
 *  Favori Butonuna bas�lan �r�n�n div id ' sinde �r�n idsi bulunur , bu id saklan�r.
 *  Daha sonra favorilerim sayfas�na gitmek i�in iki defa hover t�klama yap�l�r.
 *  ve Favorilerim sayfas� a��l�r. Favorilere eklenen �r�n�n ID ' sine g�re kontrol yap�l�r.
 *  �r�n�n Favorilerim'de oldu�u onaylan�r. Daha sonra "Sil" linkiyle Favorilerim'den silinir.
 *  Tekrar Favorilerim listesi kontrol edilerek �r�n�n kald�r�ld���na dair onay verilir. 
 * 
 * 
 * 
 * 
 */

package main.java.com.mumy81;

import java.util.concurrent.TimeUnit;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
// JUnit ' in testleri alfabeti artan s�raya g�re s�rmesi yukar�daki sat�rla
// sa�lan�r.
public class SiteCheck {

	@Rule
	// Bu sat�rla @Test lerin timeout s�releri 15 sn olarak ayarlan�r
	public Timeout timeout = new Timeout(15, TimeUnit.SECONDS);

	private static WebDriver driver;
	// �r�n div'inin idsi , i�inde �r�n kendine has IDsi bulunuyor
	// Alttaki 2 de�i�ken, birden fazla methodda kullan�ld���ndan class i�inde
	// tan�mlanm��t�r.
	private static String favProDivID;
	private static WebElement favProDiv; // �r�n Div WebElement'i
	private static String url, email, password, keyword, page;
	private static int product;

	@Test
	public void test00_setupDriver() throws Exception {
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setCapability("version", "latest");
		firefoxOptions.setCapability("platform", Platform.WINDOWS);
		firefoxOptions.setCapability("name", "Testing n11 Favori Butonu ve Favori Kald�rma ");

		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

		SiteCheck.driver = new FirefoxDriver(firefoxOptions);
		SiteCheck.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		url = "http://www.n11.com";
		email = "muhammedcelik@gmail.com";
		password = "mumy8192";
		keyword = "samsung";
		page = "2";
		product = 3;

	}

	@Test
	// WebDriver'� kapat�r
	public void test10_quitDriver() throws Exception {
		driver.quit();
	}

	public void close() {
		driver.quit();
	}

	@Test
	// verilen url'ye yani http://www.n11.com ' u browserda getirir
	public void test01_goURL() throws Exception {
		driver.get(url);
		Assert.assertEquals(url + "adresine giri� ba�ar�s�z", driver.getTitle(), "n11.com - Al��veri�in U�urlu Adresi");

	}

	@Test
	// EMail ve Parola ile giri� yapar
	public void test02_Login() throws Exception {

		driver.findElement(By.linkText("Giri� Yap")).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("loginButton")).click();

		Assert.assertNotEquals("�ye giri�inde hata olu�tu", driver.getCurrentUrl(), "https://www.n11.com/giris-yap");

		System.out.println("�ye giri�i yap�ld�");

	}

	@Test
	// Verilen kelimede arama yapar. Kelime class'�n en ba��ndan de�i�tirebilir
	public void test03_Search() throws Exception {

		driver.findElement(By.id("searchData")).sendKeys(keyword);
		driver.findElement(By.className("searchBtn")).click();

		Assert.assertEquals("Arama ger�ekle�irken hata olu�tu.", "https://www.n11.com/arama?q=" + keyword,
				driver.getCurrentUrl());

		System.out.println(keyword + " aramas� ba�ar�yla yap�ld�.");

	}

	// Ekran� WebElement ' in koordinatlar�na getirir
	// Hover linklere t�klarken gerekli oluyor
	public void setCoor(By loc) {
		WebElement elm = driver.findElement(loc);
		Coordinates coordinate = ((Locatable) elm).getCoordinates();
		coordinate.onPage();
		coordinate.inViewPort();

	}

	// Mouse'u parametlerde verilen ilk WebElement'in �st�ne getirir ve ikinci
	// WebElement'e t�klar
	public void hoverClick(By loc1, By loc2) {

		try {
			setCoor(loc1);
			Actions builder = new Actions(driver);
			// String previousURL = driver.getCurrentUrl();
			// WebDriverWait wait = new WebDriverWait(driver, 10);

			System.out.println("Hover eylemi ger�ekle�irken mouse'u hareket ettirmeyiniz");
			builder.moveToElement(driver.findElement(loc1)).moveToElement(driver.findElement(loc2)).click().perform();
			// WebDriverWait wait = new WebDriverWait(driver, 2);
			// wait.until(ExpectedConditions.elementToBeClickable(loc2));

		} catch (Exception e) {

		}
	}

	@Test
	// Aramadan sonraki �r�n listelemede verilen numaral� sayfaya gider
	public void test04_goToPage() throws Exception {
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[3]/a[" + page + "]")).click();
		Assert.assertEquals("�stenilen sayfa a��lamad�.",
				driver.findElement(By.name("currentPage")).getAttribute("value"), page);
		System.out.println("�r�n aramada" + page + " . sayfa �uan a��k halde.");

	}

	@Test
	// Arama listelemesinde s�ras� verilen �r�n�n favori butonuna t�klar
	public void test05_clickFavBtn() throws Exception {
		// Favori butonununa ve �r�n ID sine xPath yoluyla ula��r
		// ve verilen s�aradaki �r�n�n ID'sini saklar ve favori butonuna t�klar.
		String ProXPath = "/html/body/div[1]/div/div/div/div[2]/section[2]/div[2]/ul/li[" + product + "]/div";

		WebElement ProDiv = driver.findElement(By.xpath(ProXPath));
		favProDivID = ProDiv.getAttribute("id");
		System.out.println(favProDivID);

		ProDiv.findElement(By.className("followBtn")).click();

	}

	@Test
	// Favorilerim sayfas�na direk link yok , bu y�zden hover men�den �nce
	// �stek Listem'e oradan da yine hover linkten Favorilerim'e t�klan�r
	public void test06_openMyFavList() throws Exception {

		hoverClick(By.className("myAccountHolder"), By.linkText("�stek Listem"));
		Thread.sleep(1000);
		hoverClick(By.className("favorites"), By.linkText("FAVOR�LERE G�T"));
		Thread.sleep(1000);
	}

	@Test
	// Favori listesinde verilen �r�n�n oldu�unu/olmad���n� test eder
	public void test07_checkExistFavPro() throws Exception {

		try {
			favProDiv = driver.findElement(By.id(favProDivID));
			Assert.assertTrue("Favori listesinde ilgili �r�n bulunamad�", favProDiv.isDisplayed());
			System.out.println("�r�n favorilerim listesinde.");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Assert.fail("�r�n favorilerim listesinde art�k bulunmuyor");
			// System.out.println("�r�n favorilerim listesinde art�k bulunmuyor");
		}

	}

	@Test
	// �r�n favorilerimden silindikten sonra, �r��n art�k favorilerimde
	// olmad���n� kontrol eden method.
	public void test09_checkNotExistFavPro() throws Exception {

		try {
			/*
			 * Burada i�lem bekleme s�resini 0.1 sn indiriyoruz , b�ylece �r�n�n div ' ini
			 * bulamad���nda beklemeden i�leme devam ediyor. Daha sonra i�lem bekleme
			 * s�resini tekrar 10 sn'ye ��kar�yoruz
			 */
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
			favProDiv = driver.findElement(By.id(favProDivID));
			Assert.assertTrue("Favori listesinde ilgili �r�n bulunamad�", favProDiv.isDisplayed());
			System.out.println("�r�n hala favorilerim listesinde.");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("�r�n art�k favorilerim listesinde bulunmuyor.");
		}
	}

	@Test
	// �r�n� favorilerim listesinden silmek i�in "Sil" linkine t�klar
	public void test08_removeFavPro() throws Exception {
		favProDiv.findElement(By.className("deleteProFromFavorites")).click();
		driver.navigate().refresh();
	}

	public static void main(String[] args) throws Exception {

		/*
		 * Eclipse IDE kullan�ld���nda test00_ �eklinde ba�layan methodlar Eclipse
		 * i�indeki JUnit ile s�rayla s�r�l�r. Maven ile olu�turulan executable jar ile
		 * testlerin ger�ekle�mesi kolay olmas� i�in main method un i�inde s�rayla test
		 * object olu�turup methodlar�n� �a��rd�m. Otomasyon testinin ne �ekilde
		 * s�r�lece�ini bilmedi�im i�in bu �ekilde de denedim.
		 */

		SiteCheck n11 = new SiteCheck();
		n11.test00_setupDriver();
		n11.test01_goURL();
		n11.test02_Login();
		n11.test03_Search();
		n11.test04_goToPage();
		n11.test05_clickFavBtn();
		n11.test06_openMyFavList();
		n11.test07_checkExistFavPro();
		n11.test08_removeFavPro();
		n11.test09_checkNotExistFavPro();
		n11.test10_quitDriver();

	}

}