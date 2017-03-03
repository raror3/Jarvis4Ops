package org.jarvis4ops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


//@EnableWebMvc
//@ComponentScan
@SpringBootApplication
@EnableScheduling
public class SpringAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAppApplication.class, args);
    }
    
/*    @Configuration
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
    }*/

}
