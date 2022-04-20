package com.reservoir.datareservoir.core.security.authorizationserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.reservoir.datareservoir.core.security.authorizationserver.properties.JwtKeyStoreProperties;
import com.reservoir.datareservoir.core.security.authorizationserver.properties.ResourceProperties;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtKeyStoreProperties jwtKeyStoreProperties;
    private final ResourceProperties resourceProperties;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                    .withClient(resourceProperties.getBaseUsername())
                    .secret(passwordEncoder.encode(resourceProperties.getBasePassword()))
                    .authorizedGrantTypes("client_credentials")
                    .scopes("WRITE")
                .and()
                    .withClient(resourceProperties.getClientUsername())
                    .secret(passwordEncoder.encode(resourceProperties.getClientPassword()))
                    .authorizedGrantTypes("password")
                    .scopes("READ")
                .and()
                    .withClient(resourceProperties.getAdminUsername())
                    .secret(passwordEncoder.encode(resourceProperties.getAdminPassword()))
                    .authorizedGrantTypes("password")
                    .scopes("READ", "WRITE");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

        var keyStorePass = jwtKeyStoreProperties.getPassword();
        var keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();

        var keyStoreKeyFactory = new KeyStoreKeyFactory(jwtKeyStoreProperties.getJksLocation(), keyStorePass.toCharArray());
        var keyPair = keyStoreKeyFactory.getKeyPair(keyPairAlias);

        jwtAccessTokenConverter.setKeyPair(keyPair);

        return jwtAccessTokenConverter;
    }
}
