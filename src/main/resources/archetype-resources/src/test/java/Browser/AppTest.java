package ${groupId}.client;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import ${groupId}.App;

public class AppTest {

	WebDriver driver;
	String baseURL = "http://localhost:9999/${artifactId}";

	@Before
	public void setUp() {
		driver = new HtmlUnitDriver();
	}

	@Test
	public void test() {

		driver.get(baseURL);
		assertEquals("挨拶をしましょう", driver.getTitle());
	}

	@Test
	public void msgTest() {
		driver.get(baseURL);
		String msg = driver.findElement(By.id("hw")).getText();
		assertEquals((new App()).getMessage(), msg);
	}

	@Test
	public void loadImage() {
		driver.get(baseURL);
		String msg = driver.findElement(By.id("logo")).getAttribute("src");
		assertEquals("images/logo.jpg", msg);
	}

}


