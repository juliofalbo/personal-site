package juliosilveiradev.site.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{

		//Controle de acesso por ROLE
		//    	.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")

		http.authorizeRequests().antMatchers("/admin/**").authenticated().and().formLogin()
				.defaultSuccessUrl("/admin", true).loginPage("/login").permitAll().and().logout()
				.permitAll();

		http.authorizeRequests().antMatchers("/resources/static/**").permitAll().anyRequest()
				.permitAll();
		
		http.authorizeRequests().antMatchers("/blog/**").permitAll().anyRequest()
		.permitAll();

		http.authorizeRequests().antMatchers("/home").permitAll().anyRequest().permitAll();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select username, password, enabled from Usuario where username = ?")
				.authoritiesByUsernameQuery(
						"select ur.id_user, ur.role from UsuarioRole ur inner join Usuario u on u.id = ur.id_user where u.username = ?");
	}
}