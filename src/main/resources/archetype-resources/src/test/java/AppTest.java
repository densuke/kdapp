package ${package};

import static org.junit.Assert.*;
import org.junit.Test;
import ${packageId};

public class AppTest {

	@Test
	public void test() {
		App a = new App();
		assertEquals("こんにちは世界!", a.getMessage());
	}

}

