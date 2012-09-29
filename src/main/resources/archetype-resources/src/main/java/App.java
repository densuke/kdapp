package ${package};

import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{

	public static void main(String[] args) {
		/* プロパティファイルの読み込み */
		final Properties prop = new Properties();
		try {
			prop.load(App.class.getClassLoader().getResourceAsStream("Sample.properties"));
		} catch(Exception e) {
			System.err.println("プロパティファイルの読み込みに失敗しました");
			e.printStackTrace();
		}

		String first = prop.getProperty("firstname");
		String last = prop.getProperty("lastname");

		System.out.println("こんにちは " + first + " " + last);	
	}

}

