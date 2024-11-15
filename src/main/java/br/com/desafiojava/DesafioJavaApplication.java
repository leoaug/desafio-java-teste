package br.com.desafiojava;

import javax.sql.DataSource;

import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({ "br.com.desafiojava.*" })
@PropertySource("classpath:application.yml")
public class DesafioJavaApplication {

	@Value("${jndi-name}")
	private String jndiName;

	@Value("${url}")
	private String url;

	@Value("${driver-class-name}")
	private String driverClassName;

	@Value("${usuario}")
	private String username;

	@Value("${password}")
	private String password;

	@Bean
	public TomcatServletWebServerFactory tomcatFactory() {
		return new TomcatServletWebServerFactory() {
			@Override
			protected TomcatWebServer getTomcatWebServer(org.apache.catalina.startup.Tomcat tomcat) {
				tomcat.enableNaming();
				return super.getTomcatWebServer(tomcat);
			}

			@Override
			protected void postProcessContext(org.apache.catalina.Context context) {

				// context
				ContextResource resource = new ContextResource();
				resource.setName(jndiName);
				resource.setType(DataSource.class.getName());
				resource.setProperty("driverClassName", driverClassName);

				resource.setProperty("url", url);
				resource.setProperty("username", username);
				resource.setProperty("password", password);
				context.getNamingResources().addResource(resource);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DesafioJavaApplication.class, args);
	}

}
