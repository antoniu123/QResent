package com.example.qresent.security;

import com.example.qresent.model.ApplicationUser;
import com.example.qresent.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

@Configuration
public class WebSecurityConfigurator extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	ApplicationUserService usersService;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				ApplicationUser user = usersService.findByName(username);
				if (Objects.nonNull(user)) {
					switch (user.getTip_user().getName()) {
						case "ADMINISTRATOR":
							return new User(user.getUsername(), user.getPassword(), true, true, true,
									true, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
						case "TEACHER":
							return new User(user.getUsername(), user.getPassword(), true, true, true,
									true, AuthorityUtils.createAuthorityList("ROLE_TEACHER"));
						case "STUDENT":
							return new User(user.getUsername(), user.getPassword(), true, true, true,
									true, AuthorityUtils.createAuthorityList("ROLE_STUDENT"));
						default:
							throw new UsernameNotFoundException("user '" + username + "' is locked");
					}
				}
				else {
					throw new UsernameNotFoundException("could not find the user '" + username + "'");
				}
			}

		};
	}
}
