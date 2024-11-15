package br.com.desafiojava;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.desafiojava.constants.DesafioJavaConstants;

@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({ "br.com.desafiojava.*" })
@PropertySource("classpath:application.yml")
public class DesafioJavaJpa {

	@Value("${jndi-name}")
	private String jndiName;

	@Bean(name = DesafioJavaConstants.DESAFIO_JAVA_TRANSACTION_MANAGER)
	public PlatformTransactionManager transactionManager() throws Exception {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}

	@Bean(name = DesafioJavaConstants.DESAFIO_JAVA_ENTITY_MANAGER)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName(DesafioJavaConstants.DESAFIO_JAVA_PERSISTENCE_UNIT);
		factoryBean.setDataSource(dataSource());
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setPackagesToScan(DesafioJavaConstants.DESAFIO_JAVA_PACKAGE_TO_SCAN);
		factoryBean.setJpaProperties(getProperties());

		return factoryBean;

	}

	private Properties getProperties() {

		Properties prop = new Properties();
		prop.setProperty("hibernate.show_sql", "true");
		prop.setProperty("hibernate.use_sql_comments", "true");
		prop.setProperty("hibernate.type", "trace");
		prop.setProperty("hibernate.format_sql", "true");
		prop.setProperty("hibernate.archive.autodetection", "class");
		prop.setProperty("hibernate.enable_lazy_load_no_trans", "true");

		// "update" caso queira para atualizar as tabelas
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		return prop;
	}

	@Bean
	public AnnotationMBeanExporter annotationMBeanExporter() {
		AnnotationMBeanExporter annotationMBeanExporter = new AnnotationMBeanExporter();
		annotationMBeanExporter.addExcludedBean(DesafioJavaConstants.DESAFIO_JAVA_DATA_SOURCE_BEAN);
		annotationMBeanExporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
		return annotationMBeanExporter;
	}

	@Bean(name = DesafioJavaConstants.DESAFIO_JAVA_DATA_SOURCE_BEAN)
	public DataSource dataSource() throws NamingException {
		return (DataSource) new JndiTemplate().lookup("java:comp/env/" + jndiName);
	}
}
