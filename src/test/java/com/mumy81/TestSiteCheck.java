/*  Name 	   : n11.com Favorilerim,�r�n Favorilere Ekleme ve Kald�rma Test Otomasyonu
 *  Description: Bu class ile http://www.n11.com ' da uygun email ve parola ile�ye giri�i yap�l�r
 *  ve verilen kelimede arama yap�l�r. 2. sayfadaki 3. �r�n�n favori ekle butonuna bas�l�r.
 *  Favori Butonuna bas�lan �r�n�n div id ' sinde �r�n idsi bulunur , bu id saklan�r.
 *  Daha sonra favorilerim sayfas�na gitmek i�in iki defa hover t�klama yap�l�r.
 *  ve Favorilerim sayfas� a��l�r. Favorilere eklenen �r�n�n ID ' sine g�re kontrol yap�l�r.
 *  �r�n�n Favorilerim'de oldu�u onaylan�r. Daha sonra "Sil" linkiyle Favorilerim'den silinir.
 *  Tekrar Favorilerim listesi kontrol edilerek �r�n�n kald�r�ld���na dair onay verilir. 
 * 
 *  �NEML� !
 *  Hover eylelmleri s�ras�nda Firefox a��k iken mouse hi� hareket ettirilmemesi gerekli!
 * 
 * 
 */

package test.java.com.mumy81;

import main.java.com.mumy81.SiteCheck;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
// JUnit ' in testleri alfabeti artan s�raya g�re s�rmesi yukar�daki sat�rla
// sa�lan�r.
public class TestSiteCheck {

	@Rule
	// Bu sat�rla @Test lerin timeout s�releri 15 sn olarak ayarlan�r
	public Timeout timeout = new Timeout(15, TimeUnit.SECONDS);
	
	
	private static SiteCheck testApp;	
	
	@BeforeClass 
	public static void testBegin() {
	   
	   try {
		   testApp = new SiteCheck();
		} catch(Exception e) {
			fail("Test edilecek object ba�lat�l�rken hata olu�tu.");
			System.out.println(e);
		}
		
	}
	
	@AfterClass 
	public static void testClose() {
		System.out.println("Test tamamland�.");
	}

	@Test
	public void test00_setupDriver() throws Exception {
				
		try {
			testApp.setupDriver();
		} catch(Exception e) {
			fail("WebDriver ba�lat�l�rken hata olu�tu.");
			System.out.println(e);
		}
		
	}

	@Test
	public void test01_setupSite() throws Exception {
		
		
		try {
			testApp.setupSite();
		} catch(Exception e) {
			fail("Site ayarlan�rken hata olu�tu.");
			System.out.println(e);
		}
		
		}

	@Test
	// WebDriver'� kapat�r
	public void test11_quitDriver() throws Exception {
		
		try {
			testApp.quitDriver();
		} catch(Exception e) {
			fail("WebDriver kapat�l�rken hata olu�tu.");
			System.out.println(e);
		}
		
	}

	
	@Test
	// verilen url'ye yani http://www.n11.com ' u browserda getirir
	public void test02_goURL() throws Exception {
		
		try {
			testApp.goURL();
		} catch(Exception e) {
			fail("Siteye girerken hata olu�tu.");
			System.out.println(e);
		}
		}

	@Test
	// EMail ve Parola ile giri� yapar
	public void test03_Login() throws Exception {
		
		try {
			testApp.Login();
		} catch(Exception e) {
			fail("Login olurken hata olu�tu.");
			System.out.println(e);
		}
		
	}

	@Test
	// Verilen kelimede arama yapar. Kelime class'�n en ba��ndan de�i�tirebilir
	public void test04_Search() throws Exception {
		
		try {
			testApp.Search();
		} catch(Exception e) {
			fail("Arama yaparken hata olu�tu.");
			System.out.println(e);
		}
		
				}

	
		@Test
	// Aramadan sonraki �r�n listelemede verilen numaral� sayfaya gider
	public void test05_goToPage() throws Exception {
		try {
			testApp.goToPage(); 
		} catch(Exception e) {
			fail("Sayfaya giderken hata olustu.");
			System.out.println(e);
		}
	}

	@Test
	// Arama listelemesinde s�ras� verilen �r�n�n favori butonuna t�klar
	public void test06_clickFavBtn() throws Exception {
		try { 
			testApp.clickFavBtn(); 
			} catch(Exception e) {
				fail("Fav butonuna t�klarken hata olu�tu");
			System.out.println(e);
		}


	}

	@Test
	// Favorilerim sayfas�na direk link yok , bu y�zden hover men�den �nce
	// �stek Listem'e oradan da yine hover linkten Favorilerim'e t�klan�r
	public void test07_openMyFavList() throws Exception {
		try { 
			testApp.openMyFavList();
			} catch(Exception e) {
				fail("Favorilerimi a�arken hata olu�tu");
			System.out.println(e);
		}
		
		}

	@Test
	// Favori listesinde verilen �r�n�n oldu�unu/olmad���n� test eder
	public void test08_checkExistFavPro() throws Exception {
			try { 
				testApp.checkExistFavPro();
			} catch(Exception e) {
				fail("�r�n�n Favorilerimde oldu�u kontrol edilirken hata olu�tu.");
			System.out.println(e);
		}
	}

	@Test
	// �r�n favorilerimden silindikten sonra, �r��n art�k favorilerimde
	// olmad���n� kontrol eden method.
	public void test10_checkNotExistFavPro() throws Exception {
			
		try {
			testApp.checkNotExistFavPro();
		} catch(Exception e) {
			fail("�r�n�n Favorilerimde olmad��� kontrol edilirken hata olu�tu.");
			System.out.println(e);
		}
		
	}

	@Test
	// �r�n� favorilerim listesinden silmek i�in "Sil" linkine t�klar
	public void test09_removeFavPro() throws Exception {
		try {
			testApp.removeFavPro();
		} catch(Exception e) {
			fail("�r�n Favorilerim'den silinirken hata olu�tu.");
			System.out.println(e);
		}
		
		}

}