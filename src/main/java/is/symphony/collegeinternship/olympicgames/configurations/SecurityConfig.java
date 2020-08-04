package is.symphony.collegeinternship.olympicgames.configurations;

import is.symphony.collegeinternship.olympicgames.security.jwt.JwtAuthenticationEntryPoint;
import is.symphony.collegeinternship.olympicgames.security.jwt.JwtAuthenticationFilter;
import is.symphony.collegeinternship.olympicgames.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl detailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(detailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/volunteers/**").hasAnyAuthority("ADMIN","VOLUNTEER")
                .antMatchers("/api/sports/**").hasAnyAuthority("ADMIN","VOLUNTEER")
                .antMatchers("/api/athletes/**").hasAnyAuthority("ATHLETE","ADMIN")
                .antMatchers("/api/competitions/all/**", "/api/competitions/**").hasAnyAuthority("ATHLETE","ADMIN","VOLUNTEER")
                .antMatchers("/resources/**","/h2-console/**","/api/upload","/login","/signup","/api/countries").permitAll()
                .anyRequest().authenticated()
                .and()
                .authorizeRequests()
                .and()
                .headers().frameOptions().sameOrigin();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


