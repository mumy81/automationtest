package main.java.com.mumy81;

public class n11testApp {

	public static void main(String[] args) throws Exception {

		/*
		 * Eclipse IDE kullan�ld���nda test00_ �eklinde ba�layan methodlar Eclipse
		 * i�indeki JUnit ile s�rayla s�r�l�r. Maven ile olu�turulan executable jar ile
		 * testlerin s�r�lmesi kolay olmas� i�in main methodun i�inde test
		 * object olu�turup, methodlar�n� �a��r�yoruz. .
		 */

		SiteCheck n11 = new SiteCheck();
		n11.setupDriver();
		// n11.test01_setupSite();
		n11.setURL("http://www.n11.com");
		n11.setUser("muhammedcelik@gmail.com","n11deneme");
		n11.setInputs("samsung", "2", 3); // parametreler : kelime, sayfa, �r�n s�ras�
		n11.goURL();
		n11.Login();
		n11.Search();
		n11.goToPage();
		n11.clickFavBtn();
		n11.openMyFavList();
		n11.checkExistFavPro();
		n11.removeFavPro();
		n11.checkNotExistFavPro();
		n11.quitDriver();
								
	}

}
