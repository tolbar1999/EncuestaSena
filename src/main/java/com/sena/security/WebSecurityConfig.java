package com.sena.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		String sqlUser = "select u.usuario, u.clave, true from usuario as u where u.usuario = ?";
								

		String sqlRole = "select u.usuario , 'admin' from usuario as u where u.usuario = ?";
				

		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(sqlUser).authoritiesByUsernameQuery(sqlRole).passwordEncoder(passwordEncoder());
	}
 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
        http.csrf().disable()
        .authorizeRequests().antMatchers("/administrador/**").hasAuthority("admin")
			.and()
        .formLogin()
			.loginPage("/administrador")
			.permitAll()
			.usernameParameter("usuario") // Por defecto el name se le puede poner username
			.passwordParameter("clave")	// Por defecto el name se le puede poner password
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/administrador")
			.and()// Por defecto es /login
        .logout()
			.permitAll();
          
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/static/**","/css/**","/fonts/**","/img/**","/js/**");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
