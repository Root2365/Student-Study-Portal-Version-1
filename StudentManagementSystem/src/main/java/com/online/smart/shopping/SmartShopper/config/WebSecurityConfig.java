package com.online.smart.shopping.SmartShopper.config;

import com.online.smart.shopping.SmartShopper.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   UserDetailsServiceImpl userDetailsService;

   @Bean
   public static BCryptPasswordEncoder passwordEncoder() {
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

      http.authorizeRequests()
              .antMatchers("/", "/home", "/about","/register","/registerDone/**").permitAll();
//      // Requires login with role ROLE_EMPLOYEE or ROLE_MANAGER.
      // If not, it will redirect to /admin/login.
      http.authorizeRequests().antMatchers("/admin/orderList", "/admin/order", "/admin/accountInfo")//
            .access("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER','ROLE_USER')");

      // Pages only for MANAGER
      http.authorizeRequests().antMatchers("/admin/product").access("hasAnyRole('ROLE_MANAGER','ROLE_USER')");

      // When user login, role XX.
      // But access to the page requires the YY role,
      // An AccessDeniedException will be thrown.
      http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

      // Configuration for Login Form.
      http.authorizeRequests().and().formLogin()//

            //
            .loginProcessingUrl("/j_spring_security_check") // Submit URL
            .loginPage("/admin/login")//
            .defaultSuccessUrl("/admin/accountInfo")//
            .failureUrl("/admin/login?error=true")//
            .usernameParameter("userName")//
            .passwordParameter("password")

            // Configuration for the Logout page.
            // (After logout, go to home page)
            .and().logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/admin/login");

   }
}
