package ${package};

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * Apache Derbyを使うサンプルです。
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
		Connection con = null;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e)
		{
			
		}
		try {
		 con = DriverManager.getConnection("jdbc:derby:sample;create=true");
		} catch(Exception e) {
			
		}
		String first = prop.getProperty("firstname");
		String last = prop.getProperty("lastname");
		System.out.println("プロパティ読み込み: " + first + " " + last);
		
		try {
			Statement st = con.createStatement();
	        try { 
				// テーブルの初期化 このサンプルでは毎回作り直しをしています
	            st.executeUpdate("DROP TABLE HOGE"); 
	        } catch (SQLException e) { 
	        	// テーブルが存在しないときはここにいきます。が、なければいいのでこのままスルーします
	        } 
	        System.err.println("create");
			st.executeUpdate("create table HOGE ( firstname varchar(32), lastname varchar(32) )");
	        System.err.println("insert");			
			PreparedStatement ps = con.prepareStatement("insert into HOGE values ( ? , ? )");
			ps.setString(1, first + "SQL");
			ps.setString(2, last + "SQL");
			ps.executeUpdate();
	        System.err.println("fetch");			
			
			ResultSet r = st.executeQuery("select firstname, lastname from HOGE");
			r.next();
			System.out.println("SQL取得結果: " + r.getString(1) + " " + r.getString(2));
		} catch(Exception ex) {
			System.out.println("caught");
			ex.printStackTrace();
			
		}

		System.out.println("こんにちは " + first + " " + last);	
		
		HelloVE h = new HelloVE();
		h.setVisible(true);
		try {
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
		} catch(Exception e) {
			System.out.println("OK");
		}
		
	}

}


