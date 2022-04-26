

package com.reservoir.datareservoir.core.security;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import com.reservoir.datareservoir.client.config.properties.SecurityProperties;
import com.reservoir.datareservoir.core.security.authorizationserver.properties.ClientProperties;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final ClientProperties clientProperties;
	private final SecurityProperties securityProperties;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
	                .antMatchers(HttpMethod.POST, "/v1/**").hasAuthority("SCOPE_WRITE")
	                .antMatchers(HttpMethod.GET, "/v1/**").hasAuthority("SCOPE_READ")
	                .antMatchers(HttpMethod.DELETE, "/v1/**").hasAuthority("ADMIN")
                    .antMatchers("/resources/**", "/webjars/**").permitAll()
                    .anyRequest().authenticated()
                .and()
            		.cors()
                .and()
	                .formLogin().permitAll()
	            .and()
		            .oauth2ResourceServer()
	                .jwt()
	                .jwtAuthenticationConverter(jwtAuthenticationConverter());
    }
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                    .withUser(clientProperties.getWebServicePassword())
                    .password(passwordEncoder().encode(clientProperties.getWebServicePassword()))
                    .authorities("base-station")
                .and()
                    .withUser(clientProperties.getAdminUsername())
                    .password(passwordEncoder().encode(clientProperties.getAdminPassword()))
                    .authorities("ADMIN")
                .and()
	                .withUser(securityProperties.getUser())
	                .password(securityProperties.getPassword())
	                .roles("USER");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
    
    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var authorities = jwt.getClaimAsStringList("authorities");

            if(authorities == null) {
                authorities = Collections.emptyList();
            }

            var scopesAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
            Collection<GrantedAuthority> grantedAuthorities = scopesAuthoritiesConverter.convert(jwt);

            grantedAuthorities.addAll(authorities.stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList()));

            return grantedAuthorities;
        });

        return jwtAuthenticationConverter;
    }
}
