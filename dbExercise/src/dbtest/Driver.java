package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
//		getConnection();
		createTable();
//		post();
		get();

	}
	
	//select statement
	public static ArrayList<String> get() {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT firstName, lastName FROM students");
			
			ResultSet result = statement.executeQuery();
			ArrayList<String> array = new ArrayList<String>();
			
			while(result.next()) {
				String firstN = result.getString("firstName");
				String lastN = result.getString("lastName");
				System.out.println(firstN + " " + lastN);
				array.add(firstN + " " + lastN);
			}
			System.out.println("All records have been selected.");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static void post() {
		final String firstN = "Ayumi";
		final String lastN = "Tanaka";
		
		try {
			Connection con = getConnection();
			PreparedStatement posted = con.prepareStatement("INSERT INTO students (firstName, lastName) VALUES (' "+firstN+" ',' "+lastN+" ')");
			posted.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			System.out.println(firstN + " " + lastN + " has been added to the Student Table.");
		}
	}
	
	public static void createTable() {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS students (id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, firstName varchar(255), lastName varchar(255))");
			create.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Function complete");
		}
	}
	
	public static Connection getConnection() {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost/testdb";
			String username = "****";
			String password = "********";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connect to Database");
			return conn;
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}

}
