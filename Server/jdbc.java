package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc {
	 static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

	    static final String USER = "";
	    static final String PASS = "";
	    public static String[][] form=new String [5][100];
	    
 public static void fill() throws SQLException{
	 try {
		 Class.forName(JDBC_DRIVER);
	    	try(Connection conn=DriverManager.getConnection(DB_URL, USER, PASS)){
	    		try(Statement stmt =conn.createStatement();){
	    		try(ResultSet rs =stmt.executeQuery("SELECT id,userID,name,price,description FROM goods ")){
	    			   int i=0;
	    			   while(rs.next()&&i<10) {
	    					form[0][i]=rs.getString(1);
	    					form[1][i]=rs.getString(2);
	    					form[2][i]=rs.getString(3);
	    					form[3][i]=rs.getString(4);
	    					form[4][i]=rs.getString(5);
	    					i++;
	    				}
	    		}
	    		}
	    	}
	 }catch( ClassNotFoundException  e) {
		 e.printStackTrace();
	 }
 }    		
 
 public static void addGoods(int id,int userID,String name,double price,String desc) throws SQLException {
	 try {Class.forName(JDBC_DRIVER);
	try( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
		   try( PreparedStatement ps = conn.prepareStatement(
		            "INSERT INTO goods (id, userID, name,price,description) VALUES (?,?,?,?,?)")){
		    	ps.setObject(1, id);
		    	ps.setObject(2, userID);
		    	ps.setObject(3, name);
		    	ps.setObject(4, price);
		    	ps.setObject(5,desc);
		    	 int n = ps.executeUpdate();
		   }
	}
		    }catch(ClassNotFoundException e) {
		    	e.printStackTrace();
		    }
		}
 
	  public static void deleteGoods(int id) throws SQLException {
		  try {Class.forName(JDBC_DRIVER);
		 try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
			   try( PreparedStatement ps = conn.prepareStatement("DELETE FROM goods WHERE id=?")){
			        ps.setObject(1,id); 
			        int n = ps.executeUpdate();
			   }}
			    }catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
	  
public static void main(String[] args) {
	try {
		jdbc.fill();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(form[2][3]);
}

}