package factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionFactory {

	private DataSource dataSource;
	
	public ConnectionFactory () {
		
		ComboPooledDataSource pooedDataSource = new ComboPooledDataSource();
		pooedDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=True&serverTimeZone=UTC");
		pooedDataSource.setUser("root");
		pooedDataSource.setPassword("4842");
		
		pooedDataSource.setMaxPoolSize(10);
		
		this.dataSource = pooedDataSource;
	}

	public Connection conexion () {
		
		try {
			return this.dataSource.getConnection();
		
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	}
}
