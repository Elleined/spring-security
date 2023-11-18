package com.elleined.springsecurity.config;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import static com.elleined.springsecurity.model.Credential.Role.*;

public class CustomHttpRequestAuthorizer implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> {
    @Override
    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        // Inside this you can declare all your api-endpoint security based on what you want.
        auth.requestMatchers("/secured/users/**").hasAnyAuthority(USER.name(), ADMIN.name());

        auth.requestMatchers("/admins/**").hasAnyAuthority(DEVELOPER.name(), ADMIN.name());

        auth.requestMatchers("/developers/**").hasAuthority(DEVELOPER.name());

        auth.requestMatchers("/users").permitAll();
    }
}
