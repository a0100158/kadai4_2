import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	public static void main(String[] args) {

		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mystudy_wb1", "root", "mirai910");
			String bookSql = "select * from book";
			PreparedStatement bookStatement =con.prepareStatement(bookSql);
			ResultSet bookresult = bookStatement.executeQuery();

			System.out.println("■本テーブルのデータ");

			while(bookresult.next()){
				System.out.print(bookresult.getString(1) + "  ");
				System.out.print(bookresult.getString(2) + "  ");
				System.out.print(bookresult.getString(3) + "  ");
				System.out.print(bookresult.getString(4) + "  ");
				System.out.print(bookresult.getString(5) + "  ");
				System.out.print(bookresult.getString(6) + "  ");
				System.out.print(bookresult.getString(7) + "  ");
				System.out.println(bookresult.getString(8));
			}

			System.out.println();
			System.out.println("■図書館テーブルのデータ");

			String librarySql = "select * from library";
			PreparedStatement libraryStatement =con.prepareStatement(librarySql);
			ResultSet libraryresult = libraryStatement.executeQuery();

			while(libraryresult.next()){
				System.out.print(libraryresult.getString(1) + "  ");
				System.out.println(libraryresult.getString(2));
			}


			System.out.println();
			System.out.println("■図書館毎にひも付いている本の件数");

			String codeBookSql = "SELECT library.library_name,count(*) FROM pegging inner join library on pegging.library_id = library.library_id group by library.library_name";
			PreparedStatement codeBookStatement =con.prepareStatement(codeBookSql);
			ResultSet codeBookresult = codeBookStatement.executeQuery();

			while(codeBookresult.next()){
				System.out.print(codeBookresult.getString(1) + "  ");
				System.out.println(codeBookresult.getString(2));
			}

		} catch (SQLException e) {
			System.out.println("接続できませんでした。");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("クローズできませんでした。");
				}
			}
		}
	}
}