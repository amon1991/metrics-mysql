package com.amon.personshare.metrics_mysql;
import java.io.Serializable;
import java.sql.*;

public class BaseDao implements Serializable {

	private static final long serialVersionUID = 1L;

	//连接驱动
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	//静态代码块
	static {
		try {
			// 加载驱动
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 获取数据库连接
	 */
	public Connection getConnection(String URL,String USERNAME, String PASSWORD) {
		Connection conn = null;
		try{
			conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * 关闭数据库连接，注意关闭的顺序
	 */
	public void close(PreparedStatement rs,Connection conn) {
		if(rs!=null){
			try{
				rs.close();
				rs=null;
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try{
				conn.close();
				conn=null;
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

}
