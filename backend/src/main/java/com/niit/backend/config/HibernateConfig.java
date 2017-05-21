package com.niit.backend.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan(basePackages = { "com.niit.backend" })
@EnableTransactionManagement
public class HibernateConfig {
	static private String className="org.h2.Driver";
	static private String url ="jdbc:h2:tcp://localhost/~/Watches";
	static private String username ="sa";
	static private String password ="";
	static private String dialect ="org.hibernate.dialect.H2Dialect";

	@Bean(name={"datasource"})
	public DataSource getDataSource() {
		BasicDataSource bd = new BasicDataSource();
		bd.setDriverClassName(className);
		bd.setUrl(url);
		bd.setUsername(username);
		bd.setPassword(password);
		return bd;

	}

	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		System.out.println("getsession");
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.niit.backend");
		return builder.buildSessionFactory();
	}

	@Bean
	public Properties getHibernateProperties() {
		System.out.println("getproperties");
		Properties properties = new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;

	}
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("transaction");
		System.out.println("Session factory"+sessionFactory);
		HibernateTransactionManager txmgr=null;
	
		 txmgr=new HibernateTransactionManager(sessionFactory);
		System.out.println("transaction M:::::"+txmgr);
		
		
		return txmgr;
		
	}
	
	   @Bean(name="multipartResolver") 
	    public CommonsMultipartResolver getResolver() throws IOException{
	        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	         
	        //Set the maximum allowed size (in bytes) for each individual file.
	        resolver.setMaxUploadSizePerFile(5242880);//5MB
	         
	        //You may also set other available properties.
	         
	        return resolver;
	    }
	   
	   
	  
}
