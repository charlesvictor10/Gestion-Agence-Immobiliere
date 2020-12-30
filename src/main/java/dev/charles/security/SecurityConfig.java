package dev.charles.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username as principal, password as credentials, active from utilisateur where username=?")
		.authoritiesByUsernameQuery("select utilisateur_username as principal, roles_role as role from utilisateur_role where utilisateur_username=?")
		.rolePrefix("ROLE_")
		.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/css/**","/js/**","/images/**","/fonts/**").permitAll()
		.antMatchers("/inscription","/saveUtilisateur","/reinitialisation").permitAll()
		.antMatchers("/getLogo","/accueil","/about","/getImage","/getPhoto","/immo","/detail","/getLogos","/agence","/bail","/contact","/traitement","/getPaiement","/getPhotoUtilisateur").permitAll()
		.antMatchers("/operation", "/payement","/saveOperation","/confirmation").hasRole("USER")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated().and()
		.formLogin().loginPage("/connexion").permitAll().and()
		.logout().invalidateHttpSession(true).logoutUrl("/deconnexion").logoutSuccessUrl("/connexion").permitAll().and()
		.exceptionHandling().accessDeniedPage("/403").and()
		.sessionManagement().maximumSessions(1).expiredUrl("/connexion");
	}
}
