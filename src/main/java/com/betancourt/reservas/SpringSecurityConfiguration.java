package com.betancourt.reservas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.betancourt.reservas.security.LoginSuccessHandler;
import com.betancourt.reservas.services.UsuarioService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private LoginSuccessHandler handler;
		
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{	
		build.userDetailsService(service).passwordEncoder(encoder());		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
        .antMatchers("/","/css/**","/fonts/**","/photos/**","/js/**","/scss/**","/vendor/**").permitAll()
		.antMatchers("/create").anonymous()
		.antMatchers("/cliente/save").permitAll()
		.antMatchers("/usuario/save").permitAll()
		.antMatchers("/gerente/save").permitAll()
		.antMatchers("/gerente/servicios/**").hasRole("USER")
		.antMatchers("/cliente/list", "/admin/**").hasRole("ADMIN")
		.antMatchers("/gerente/**").hasAnyRole("ADMIN", "GERENTE")
		.antMatchers("/servicio/**", "/reservacion/list", "/reservacion/retrieve/**").hasAnyRole("ADMIN", "GERENTE")
		.antMatchers("/reservacion/save", "cliente/retrieve/**").hasAnyRole("USER", "ADMIN", "GERENTE")
		.and()
        .formLogin()
            .loginPage("/login")
            .permitAll();
	}
}
