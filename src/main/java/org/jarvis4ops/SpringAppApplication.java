package org.jarvis4ops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//@EnableWebMvc
//@ComponentScan
//@ComponentScan(basePackages = {"org.jarvis4ops, org.jarvis4ops.controller, org.jarvis4ops.configurations, org.jarvis4ops.bean"})
@SpringBootApplication
@EnableScheduling
public class SpringAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAppApplication.class, args);
    }
    
    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
      @Override
      protected void configure(HttpSecurity http) throws Exception {
        http
          .httpBasic()
        .and()
          .authorizeRequests()
            .antMatchers("/index", "/index.html", "/home.html", "/login.html", "/").permitAll()
            .anyRequest().authenticated();
      }
    }

}
