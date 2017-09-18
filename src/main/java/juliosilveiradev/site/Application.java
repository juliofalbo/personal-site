package juliosilveiradev.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class Application extends SpringBootServletInitializer
{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(Application.class);
	}

	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public DriverManagerDataSource dataSource()
	{
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/juliosilveira");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");
		return driverManagerDataSource;
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer()
	{
		return new EmbeddedServletContainerCustomizer()
		{
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container)
			{
				container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error"));
			}
		};
	}

}