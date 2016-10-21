package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SqlUtil{
	
	//windows
	final static String sqlUrl = "jdbc:mysql://123.207.93.178:3306/webproject";
//	管理员用户名
	final static String rootName = "root";
//	管理员密码
	final static String rootPwd ="wuzhaorui05.";
	
	public static Connection getCon() throws SQLException{
		try{   
		    //加载MySql的驱动类   
		    Class.forName("com.mysql.jdbc.Driver") ;   
		    }catch(ClassNotFoundException e){   
		    System.out.println("找不到驱动程序类 ，加载驱动失败！");   
		    e.printStackTrace() ;   
		    }   
		return DriverManager.getConnection(sqlUrl, rootName, rootPwd);
	}
	
	
}
