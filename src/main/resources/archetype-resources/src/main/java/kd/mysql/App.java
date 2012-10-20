package ${package}.mysql;

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

	private static final String PROP = "MySQL.properties";

	public static void main(String[] args) {
		/* プロパティファイルの読み込み */
		final Properties prop = new Properties();
		try {
			prop.load(App.class
							.getClassLoader()
							.getResourceAsStream(PROP));
		} catch(Exception e) {
			System.err.println("プロパティファイル("+PROP+")の読み込みに失敗しました");
			e.printStackTrace();
			System.exit(1);
		}
		Connection con = null;
		String driverClass = prop.getProperty("driver");
		try {
			Class.forName(driverClass);
		} catch (Exception e)
		{
			System.err.println("ドライバ" + driverClass + "の読込に失敗しました");
			System.exit(1);
		}

		String connectURL = prop.getProperty("dburl");
		String user = prop.getProperty("username");
		String pass = prop.getProperty("password");
		try {
		 con = DriverManager.getConnection(connectURL,
				 							user, pass);
		} catch(Exception e) {
			System.err.println("データベース接続("+connectURL+")に失敗しました。");
			System.exit(1);
		}

		final String first = "ふが";
		final String last = "ほげ";

		Statement st = null;
		PreparedStatement ps = null;
		ResultSet r = null;
		try {
			st = con.createStatement();
	        try {
				// テーブルの初期化 このサンプルでは毎回作り直しをしています
	            st.executeUpdate("DROP TABLE IF EXISTS HOGE");
	        } catch (SQLException e) {
	        	System.err.println("既存テーブル削除処理でエラーが発生しました");
	        	con.close();
	        	System.exit(1);
	        }

	        st.executeUpdate("create table HOGE ( firstname varchar(32), lastname varchar(32) )");
			 ps = con.prepareStatement("insert into HOGE values ( ? , ? )");
			ps.setString(1, first + "SQL");
			ps.setString(2, last + "SQL");
			ps.executeUpdate();

			r = st.executeQuery("select firstname, lastname from HOGE");
			r.next();
			System.out.println("SQL取得結果: " + r.getString(1) + " " + r.getString(2));
		} catch(Exception ex) {
			System.out.println("DB処理中にエラーが発生しました、後始末して終了します");
			ex.printStackTrace();
			try {
				if(r != null) r.close();
				if(ps != null) ps.close();
				if(st != null) st.close();
				con.close();
			} catch(Exception e) {
				// Nothing todo.
			} finally {
				System.exit(1);
			}
		}

		System.out.println("こんにちは " + first + " " + last);

		try {
			r.close();
			ps.close();
			st.close();
			con.close();
		} catch(Exception e) {
			// Nothing todo.
		}
	}

}


