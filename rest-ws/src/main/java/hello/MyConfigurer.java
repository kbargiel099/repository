package hello;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import module.DataSourceProvider;
import module.mail_manager.impl.SimpleMailManager;

@Configuration
public class MyConfigurer{

   @Bean
   public String schema() {
	   return "sys";
   }
   
   @Bean
   public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:8080");
		config.addAllowedOrigin("http://192.168.0.15:8080");
		config.addAllowedHeader("Access-Control-Allow-Origin");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
   }
  
   @Bean
   @Autowired
   public DataSourceTransactionManager transactionManager() {
	   DataSourceTransactionManager txManager = new DataSourceTransactionManager();
	   txManager.setDataSource(DataSourceProvider.dataSource("auctions"));
	   return txManager;
   }
   
   @Bean
   public JavaMailSender getJavaMailSender() {
       JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
       mailSender.setHost("smtp.gmail.com");
       mailSender.setPort(587);
        
       mailSender.setUsername("abstergo887@gmail.com");
       mailSender.setPassword("Jecky887");
        
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