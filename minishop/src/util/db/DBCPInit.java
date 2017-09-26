package util.db;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {

	@Override
	public void init() throws ServletException{
		loadJDBCDriver();
		initConnectionPool();
	}
	
	private void loadJDBCDriver(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

		}catch(ClassNotFoundException e){
			throw new RuntimeException("fail to load JDBC Driver", e);
		}
	}
	
	private void initConnectionPool(){
		try{
			String jdbcUrl= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String username= "minishop";
			String pw= "minishop";
			
			ConnectionFactory connFactory= new DriverManagerConnectionFactory(jdbcUrl, username, pw);
			
			PoolableConnectionFactory poolableConnFactory= new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1 from dual");
			
			GenericObjectPoolConfig poolConfig= new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);	//Connection Pool �˻� �ֱ�
			poolConfig.setTestWhileIdle(true);	//Ǯ�� ���� ���� Ŀ�ؼ��� ��ȿ���� �˻����� ����
			poolConfig.setMinIdle(5);	//Ŀ�ؼ� �ּ� ����
			poolConfig.setMaxTotal(20);	//Ŀ�ؼ� �ִ� ����
			
			GenericObjectPool<PoolableConnection> connectionPool= new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver= (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("minishop", connectionPool);
			
			System.out.println("DBCPInit : dbǮ�� �ʱ�ȭ �Ϸ�");
		}catch(Exception e){
			throw new RuntimeException(e);
			
		}
	}
}
