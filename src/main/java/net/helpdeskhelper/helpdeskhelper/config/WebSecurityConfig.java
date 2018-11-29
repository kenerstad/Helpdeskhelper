package net.helpdeskhelper.helpdeskhelper.config;

import javax.sql.DataSource;

import net.helpdeskhelper.helpdeskhelper.service.UserDetailsService_Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
@EnableWebSecurity //Must be enabled if disabled default config
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	   @Autowired
	    private DataSource dataSource;
	   
	    @Autowired
	    private UserDetailsService_Impl userDetailsService;
	    
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
	    }
	   
	   @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	 
	        // Setting Service to find User in the database.
	        // And Setting PassswordEncoder
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	 
	    }

	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	 
	        http.csrf().disable();
	         
	        http.authorizeRequests()
	        	.antMatchers("/", "/index", "/login", "/logout")
	        	.permitAll();
	 	       
	        http.authorizeRequests()
	        	.antMatchers("/userInfo")
	        	.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
	 
	        http.authorizeRequests()
	        	.antMatchers("/admin")
	        	.access("hasRole('ROLE_ADMIN')");
	 
	        http.authorizeRequests()
	        	.and()
	        	.exceptionHandling()
	        	.accessDeniedPage("/403");
	 
	        http.authorizeRequests()
	        	.and()
	        	.formLogin()
                .loginProcessingUrl("/security_check") 
                .loginPage("/login")//
                .defaultSuccessUrl("/account")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")              
                .and()
                .logout()
                .logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
 
	        /*
	        // Config Remember Me.
	        http.authorizeRequests().and() //
	                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
	                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
	 */
	    }
	 
	 /*
	 @Bean
	 public PersistentTokenRepository persistentTokenRepository() {
	     JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
	     db.setDataSource(this.dataSource);
	     return db;
	 }
	*/
	
}
