package com.example.qresent.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/").access("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
				.antMatchers("/student").access("hasRole('ADMIN')")
				.antMatchers("/student/add").access("hasRole('ADMIN')")
				.antMatchers("/student/{id}").access("hasRole('ADMIN')")
				.antMatchers("/student/delete/{id}").access("hasRole('ADMIN')")
				.antMatchers("/students").access("hasRole('ADMIN') or hasRole('TEACHER')")
				.antMatchers("/students/{id}").access("hasRole('STUDENT')")

				.antMatchers("/grade").access("hasRole('TEACHER')")
				.antMatchers("grade/delete/{gradeId}").access("hasRole('TEACHER')")
				.antMatchers("grades/add").access("hasRole('TEACHER')")
				.antMatchers("grade/{gradeId}").access("hasRole('TEACHER')")
				.antMatchers("grades/all").access("hasRole('TEACHER')")
				.antMatchers("grades/{gradeId}").access("hasRole('STUDENT')")

				.antMatchers("/averages").access("hasRole('TEACHER')")
				.antMatchers("averages/action}").access("hasRole('TEACHER')")
				.antMatchers("averages/save").access("hasRole('TEACHER')")
				.antMatchers("student/{studentId}/average").access("hasRole('STUDENT')")

				.antMatchers("/css/evidenta.css").permitAll()
				.antMatchers("/img/index.jpg").permitAll()


				.anyRequest().authenticated()
				.and()
				.formLogin()  //login configuration
				.loginPage("/login")
				.loginProcessingUrl("/app-login")
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/")
				.and()
				.logout()    //logout configuration
				.logoutUrl("/app-logout")
				.logoutSuccessUrl("/login")
				.and()
				.exceptionHandling() //exception handling configuration
				.accessDeniedPage("/error");


		// http.csrf().disable();
	}
}
