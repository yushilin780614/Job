package com.show.job;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// 為了測試，只做最簡單的設定，並且用Basic Auth替代JWT，以方便測試
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 任意的三個使用者，以便能夠測試
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails u1=User.withUsername("user1").password("111").build();
        UserDetails u2=User.withUsername("user2").password("222").build();
        UserDetails u3=User.withUsername("user3").password("333").build();
        return new InMemoryUserDetailsManager(List.of(u1, u2, u3));
    }

    // 為了方便測試，所以不對密碼進行加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // 為了測試RESTful API和一般顯示網頁，所以直接採用最簡單的"HTTP Basic 認證"和"Spring預設的登入畫面"
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf-> csrf.disable())
         // 直接讓所以HTTP Method為POST，都要求用"HTTP Basic 認證"
         .authorizeHttpRequests(auth->auth.requestMatchers(method->method.getMethod()=="POST").authenticated())
         .httpBasic(Customizer.withDefaults())
         // 所以不是HTTP Method為POST，都要求用"Spring預設的登入畫面"
         .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
         .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
