//package com.came.stock.core.spring;
//
//import java.util.Properties;
//
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jndi.JndiObjectFactoryBean;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//public class DbCfgJndi {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(DbCfgJndi.class);
//	
//	@Bean
//	public DataSource stockDataSource() {
//		String jndi = "java:jboss/jdbc/stockjndi";
//		DataSource ds = (DataSource) conexionGenericoJndi(jndi).getObject();
//		try {
//			ds.setLoginTimeout(6000);
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage(), e);
//		}
//		
//		return ds;
//	}
//	
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(stockDataSource());
//		sessionFactory.setPackagesToScan("com.came.stock.model");
//		Properties hibernateProperties = new Properties();
//		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//		hibernateProperties.put("hibernate.show_sql", "true");
//		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
//		
//		hibernateProperties.put("hibernate.c3p0.min_size", "5");
//		hibernateProperties.put("hibernate.c3p0.max_size", "20");
//		hibernateProperties.put("hibernate.c3p0.timeout", "1000");
//		hibernateProperties.put("hibernate.c3p0.max_statements", "50");
//		hibernateProperties.put("hibernate.c3p0.idle_test_period", "3000");
//		
//		
//		sessionFactory.setHibernateProperties(hibernateProperties);
// 
//		return sessionFactory;
//	}
// 
//	@Bean
//	public HibernateTransactionManager transactionManager() {
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//		transactionManager.setSessionFactory(sessionFactory().getObject());
//		return transactionManager;
//	}
//	
//
//	private JndiObjectFactoryBean conexionGenericoJndi(String jndiName) {
//		JndiObjectFactoryBean jndiObjectFactoryBean = null;
//		try {
//			jndiObjectFactoryBean = new JndiObjectFactoryBean();
//			jndiObjectFactoryBean.setJndiName(jndiName);
//			jndiObjectFactoryBean.afterPropertiesSet();
//		} catch (NamingException e) {
//			LOGGER.error("Error al conectar con el datasource con nombre JNDI " + jndiName, e);
//		}
//		return jndiObjectFactoryBean;
//	}
//}
