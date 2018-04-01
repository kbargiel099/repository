package hello;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class MyConfigurer {

   @Bean
   public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:8080");
		config.addAllowedOrigin("http://192.168.0.15:8080");
		//config.addAllowedOrigin("http://192.168.0.13:8080");
		config.addAllowedHeader("Access-Control-Allow-Origin");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
   }
	
   @Bean
   public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setUrl("jdbc:postgresql://localhost:5432/auctions");
        driver.setUsername("postgres");
    	driver.setPassword("postgres");
    	return driver;
   }
  
   @Bean
   @Autowired
   public DataSourceTransactionManager transactionManager() {
	   DataSourceTransactionManager txManager = new DataSourceTransactionManager();
	   txManager.setDataSource(dataSource());
	   return txManager;
   }
}