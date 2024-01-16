package org.example.ssiach21ex4.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.example.ssiach21ex4.config.CustomSecurityContextFactory;
import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = CustomSecurityContextFactory.class)
public @interface WithCustomUser {

    String username();

}
