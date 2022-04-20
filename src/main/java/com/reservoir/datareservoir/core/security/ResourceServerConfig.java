package com.reservoir.datareservoir.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@Order(99)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	System.out.println(http.authorizeRequests().antMatchers(HttpMethod.GET, "/v1/**").hasAuthority("SCOPE_READ"));
        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/v1/**").hasAuthority("SCOPE_WRITE")
                    .antMatchers(HttpMethod.GET, "/v1/**").hasAuthority("SCOPE_READ")
                    .antMatchers(HttpMethod.DELETE, "/v1/**").hasAuthority("ADMIN")
                .and()
                    .cors()
                .and()
                    .oauth2ResourceServer()
                    .jwt()
                    .jwtAuthenticationConverter(jwtAuthenticationConverter());
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
