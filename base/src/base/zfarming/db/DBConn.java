package base.zfarming.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

@SuppressWarnings("resource")
public class DBConn {

	public static InputStream inputStream;
	public static Properties p;
	static {
		inputStream = DBConn.class.getClassLoader()
				.getResourceAsStream("config/db.properties");
		p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public static Connection connection = null;
	
	public static Connection getConn(){
		 try {
			Class.forName(p.getProperty("db.dbdriver"));
			Connection conn = DriverManager.getConnection(
					p.getProperty("db.jdbcUrl"), p.getProperty("db.username"),
					p.getProperty("db.password"));
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Connection getBoneConn() {
		
		BoneCP connectionPool = null;
		try {
			Class.forName(p.getProperty("db.dbdriver"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		try {
			// setup the connection pool
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(p.getProperty("db.jdbcUrl")); // jdbc
			config.setUsername(p.getProperty("db.username"));
			config.setPassword(p.getProperty("db.password"));
			// 设置每个分区中的最大连接数 30
			config.setMaxConnectionsPerPartition(30);
			// 设置每个分区中的最小连接数 10
			config.setMinConnectionsPerPartition(10);
			// 当连接池中的连接耗尽的时候 BoneCP一次同时获取的连接数
			config.setAcquireIncrement(5);
			//连接释放处理
			config.setPartitionCount(3);
			// 设置配置参数
			connectionPool = new BoneCP(config); // setup the connection pool

			connection = connectionPool.getConnection(); // fetch a connection

			connectionPool.shutdown(); // shutdown connection pool.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void releaseConn() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws SQLException {
		Connection conn = DBConn.getConn();
		String sql = "select * from sys_user";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
		}
	}
}
