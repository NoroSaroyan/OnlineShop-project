package ru.gb.onlineshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("myUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    //    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .antMatchers("/*").permitAll() // This will be your home screen URL
//                    .antMatchers("/login").permitAll()
//                    .antMatchers("/home/","/home/*").permitAll()
//                    .antMatchers("/registration","/registration/*").permitAll()
//                    .antMatchers("/css/**").permitAll()
//                    .antMatchers("/js/**").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                    .defaultSuccessUrl("/home")
//                    .loginPage("/login")
//                    .permitAll()
//                .and()
//                    .logout()
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .logoutSuccessUrl("/login").permitAll();
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/home", "/index", "/about", "/help", "/register", "/cart/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**", "/product/new").hasRole("ADMIN")
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .and().headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/webjars/**", "/js/**", "/error/**"
                , "/css/**", "/fonts/**", "/libs/**", "/img/**", "/h2-console/**");
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
