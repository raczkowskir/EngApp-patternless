package pl.com.pattern.less.EngApp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLforApp {

	   public static final String DRIVER = "org.sqlite.JDBC";
	    public static final String DB_URL = "jdbc:sqlite:biblioteka.db";
	 
	    private Connection conn;
	    private Statement stat;
	    private int count;
	 
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
	
	
	 public boolean createTables()  {
	        String createList1 = "CREATE TABLE IF NOT EXISTS list1 (id_word INTEGER PRIMARY KEY AUTOINCREMENT, engWord varchar(255), plWord varchar(255))";
	        String createList2 = "CREATE TABLE IF NOT EXISTS list2 (id_word INTEGER PRIMARY KEY AUTOINCREMENT, engWord varchar(255), plWord varchar(255))";
	        try {
	            stat.execute(createList1);
	            stat.execute(createList2);
	            
	        } catch (SQLException e) {
	            System.err.println("Blad przy tworzeniu tabeli");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	 
	 public boolean clearTable(String tableName)  {
	        String createList = "CREATE TABLE IF NOT EXISTS "+tableName+" (id_word INTEGER PRIMARY KEY AUTOINCREMENT, engWord varchar(255), plWord varchar(255))";
	            try {
	        	stat.execute("DROP TABLE "+tableName);
	            stat.execute(createList);
	            System.out.println("Wyczyszczono tabele: "+tableName);
	            
	        } catch (SQLException e) {
	            System.err.println("Blad przy czyszczeniu tabeli");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	 
	 public boolean dropIndex(String tableName,int iterator )  {
	        	            try {
	        	            	
	        	stat.execute("DROP ROW "+tableName);
	            System.out.println("USUNIETO INDEX: "+iterator);
	            
	        } catch (SQLException e) {
	            System.err.println("Blad przy: DROP INDEX");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	 
	 public boolean deleteWord(String tableName, int iterator)  {
	        String deleteFrom = "DELETE FROM "+tableName+" WHERE id_word = '"+iterator+"'";
	            try {
            	System.out.println("Usuniento słowo: "+ selectWord(tableName,"engWord", iterator ));
	        	stat.execute(deleteFrom);
	        	System.out.println("Aktualne słowo na tej pozycji: "+ selectWord(tableName,"engWord", iterator ));
	        	System.out.println("Iterator: " +iterator);
	        	System.out.println("word_id: " +selectWord(tableName,"id_word", iterator )+ " puste?");
	        } catch (SQLException e) {
	            System.err.println("Blad przy usuwaniu slowa");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	 
	   public boolean insertWord(String tableName, String engWord, String plWord) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "insert into "+tableName+" values (NULL, ?, ?);");
           
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
	   
	   public String selectWord(String tableName, String columnName, int wordId ) {
		   String outcome ="";
		   System.out.println(columnName);
	        try {
	            ResultSet result = stat.executeQuery("SELECT * FROM "+tableName+" WHERE id_word='"+wordId+"'");
	        	
	            // jezeli result.getRow() zwraca 1 to znaczy za wiersz istnieje jezeli zwroci 0 to nie istnieje
	            //result.next(); przesowa o jeden do przodu
	            //previous();
	            String engWord;
	            String plWord;
	            	            while(result.next()) {
	            	            	
	            	plWord = result.getString("plWord");
	                engWord = result.getString("engWord");                            
	                
	                if(columnName.equals("engWord")){
	    	        	outcome=engWord;	        	
	    	        }
	                else{
	                	outcome=plWord;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return outcome;
	    }
	   
	   public int countWords(String tableName) {
		//   String volume="";
	        try {
	        	Statement stmt3 = conn.createStatement();
	        	ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) AS total FROM "+tableName);
	        	    while(rs3.next()){
	        	   count = rs3.getInt("total");
	        	//   volume = count+"";
	        	    }   
	            
	        } catch (SQLException e) {
	        	System.out.println(e.getClass());
	        	System.out.println(e.getMessage());
	            e.printStackTrace();
	        }
	        finally{
	        	System.out.println("Oto volumen: " + count);
		       
	        }
	        return count;
	    }
}
