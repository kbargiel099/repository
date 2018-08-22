package module;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceProvider {
	
   public static DataSource dataSource(String database) {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setUrl("jdbc:postgresql://localhost:5432/" + database);
        driver.setUsername("postgres");
    	driver.setPassword("postgres");
    	return driver;
   }
}
