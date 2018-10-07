package app.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import module.mail_manager.impl.SimpleMailManager;

@Configuration
public class Configurer{
	
   @Bean
   public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin(module.Properties.PORTAL_URL);
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
        driver.setUrl(module.Properties.DATABASE_URL);
        driver.setUsername(module.Properties.DATABASE_USERNAME);
    	driver.setPassword(module.Properties.DATABASE_PASSWORD);
    	return driver;
   }
   
   @Bean
   @Autowired
   public DataSourceTransactionManager transactionManager() {
	   DataSourceTransactionManager txManager = new DataSourceTransactionManager();
	   txManager.setDataSource(dataSource());
	   return txManager;
   }
   
   @Bean
   public JavaMailSender getJavaMailSender() {
       JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
       mailSender.setHost(module.Properties.EMAIL_HOST);
       mailSender.setPort(module.Properties.EMAIL_PORT);
        
       mailSender.setUsername(module.Properties.EMAIL_USERNAME);
       mailSender.setPassword(module.Properties.EMAIL_PASSWORD);
        
       Properties props = mailSender.getJavaMailProperties();
       props.put("mail.transport.protocol", "smtp");
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.debug", "true");
        
       return mailSender;
   }
   
   @Bean
   @Autowired
   public SimpleMailManager getSimpleMailManager() {
       SimpleMailManager mailManager = new SimpleMailManager();
       mailManager.setMailSender(getJavaMailSender());
       mailManager.setTemplateMessage(new SimpleMailMessage());
        
       return mailManager;
   }
}