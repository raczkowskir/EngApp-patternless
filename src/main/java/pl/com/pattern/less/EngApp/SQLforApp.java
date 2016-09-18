package pl.com.pattern.less.EngApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLforApp {

	// This class do not contain its own view
	// it is using for database managing

	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:biblioteka.db";

	private Connection conn;
	private Statement stat;
	private int count;

	// the constructor - responsible for getting connection with data
	// and invoke method which creates tables
	public SQLforApp() {
		try {
			Class.forName(SQLforApp.DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Brak sterownika JDBC");
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(DB_URL);
			stat = conn.createStatement();

		} catch (SQLException e) {
			System.err.println("Problem z otwarciem polaczenia");
			e.printStackTrace();
		}

		createTables();
	}

	// the method which creates tables
	public boolean createTables() {
		String createList1 = "CREATE TABLE IF NOT EXISTS list1 (id_word INTEGER PRIMARY KEY AUTOINCREMENT, engWord varchar(255), plWord varchar(255))";
		String createList2 = "CREATE TABLE IF NOT EXISTS list2 (id_word INTEGER PRIMARY KEY AUTOINCREMENT, engWord varchar(255), plWord varchar(255))";
		String createList3 = "CREATE TABLE IF NOT EXISTS list3 (id_word INTEGER PRIMARY KEY AUTOINCREMENT, engWord varchar(255), plWord varchar(255))";
		String createList4 = "CREATE TABLE IF NOT EXISTS list4 (id_word INTEGER PRIMARY KEY AUTOINCREMENT, engWord varchar(255), plWord varchar(255))";
		String createList5 = "CREATE TABLE IF NOT EXISTS list5 (id_word INTEGER PRIMARY KEY AUTOINCREMENT, engWord varchar(255), plWord varchar(255))";

		try {
			stat.execute(createList1);
			stat.execute(createList2);
			stat.execute(createList3);
			stat.execute(createList4);
			stat.execute(createList5);

		} catch (SQLException e) {
			System.err.println("Blad przy tworzeniu tabeli");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// the method which deletes table and creates again the empty one
	public boolean clearTable(String tableName) {
		String createList = "CREATE TABLE IF NOT EXISTS " + tableName
				+ " (id_word INTEGER PRIMARY KEY AUTOINCREMENT, engWord varchar(255), plWord varchar(255))";
		try {
			stat.execute("DROP TABLE " + tableName);
			stat.execute(createList);
			System.out.println("Wyczyszczono tabele: " + tableName);

		} catch (SQLException e) {
			System.err.println("Blad przy czyszczeniu tabeli");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteWord(String tableName, String engWord) {
		String deleteFrom = "DELETE FROM " + tableName + " WHERE engWord = '" + engWord + "'";
		try {
			stat.execute(deleteFrom);
		} catch (SQLException e) {
			System.err.println("Blad przy usuwaniu slowa");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insertWord(String tableName, String engWord, String plWord) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement("insert into " + tableName + " values (NULL, ?, ?);");

			prepStmt.setString(1, engWord);
			prepStmt.setString(2, plWord);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad dodawaniu słowka");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	// the method used for selecting particular fields from database
	// - used by buttons CHECK, NEXT, BACK
	public String selectWord(String tableName, String columnName, int wordId) {
		String outcome = "";
		System.out.println(columnName);
		try {
			// ResultSet result = stat.executeQuery("SELECT * FROM "+tableName+"
			// WHERE id_word='"+wordId+"'");
			ResultSet result = stat.executeQuery("SELECT * FROM " + tableName);

			// the loop which help set cursor on current row
			// - setting current row by using result.absolute(int rowNuber); is
			// throwing: "java.sql.SQLException: ResultSet is TYPE_FORWARD_ONLY"

			for (int i = 0; i < wordId; i++) {
				result.next();
			}
			String engWord;
			String plWord;

			plWord = result.getString("plWord");
			engWord = result.getString("engWord");

			System.out.println("result of getRow(): " + result.getRow());

			if (columnName.equals("engWord")) {
				outcome = engWord;
			} else {
				outcome = plWord;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return outcome;
	}

	// the method for checking how many rows the table have
	public int countWords(String tableName) {
		try {
			Statement stmt3 = conn.createStatement();
			ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) AS total FROM " + tableName);
			while (rs3.next()) {
				count = rs3.getInt("total");
			}

		} catch (SQLException e) {
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println("Oto volumen: " + count);

		}
		return count;
	}
}
