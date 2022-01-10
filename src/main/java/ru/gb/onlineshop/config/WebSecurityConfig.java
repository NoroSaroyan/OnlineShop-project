package ru.gb.onlineshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("myUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/home", "/index", "/about", "/help", "/register").permitAll()
                .antMatchers("/user/**").hasAuthority("ROLE_USER")
                .antMatchers("/admin/**", "/product/new").hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN")
                .antMatchers("/product/delete/*").hasAuthority("ROLE_SUPER_ADMIN")
                .antMatchers("/product/new","/product/edit/*").hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN")
                .antMatchers("/cart/add/*").hasAnyAuthority("ROLE_USER","ROLE_ADMIN","ROLE_SUPER_ADMIN")
                .antMatchers("/cart/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN","ROLE_SUPER_ADMIN")
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
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
