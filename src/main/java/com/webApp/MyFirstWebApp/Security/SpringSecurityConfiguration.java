package com.webApp.MyFirstWebApp.Security;

import java.util.function.Function;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		UserDetails userDetails1 = extracted("Pranshu", "pranshu123");
		UserDetails userDetails2 = extracted("Khushi", "khushi123");
							
		return new InMemoryUserDetailsManager(userDetails1,userDetails2);
	}

	private UserDetails extracted(String userName, String password) {
		Function<String, String> passwordEncoder 
						= input -> passwordEncoder().encode(input);
		
		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder)
									.username(userName)
									.password(password)
									.roles("USER","ADMIN")
									.build();
		return userDetails;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		 return new BCryptPasswordEncoder();
	}
	

//for using H2 with spring security disable CSRF & configure add FRAMES as security console blocks FRAMES
	
	//all unauthorized urls will be 1st checked with SecurityFilterChain
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
		http.formLogin(withDefaults());
		
		//Had to define above 2 steps as we are overriding default filter Chain
		
		http.headers().frameOptions().disable();
		http.csrf().disable();
		
		return http.build();
	}
	
}
