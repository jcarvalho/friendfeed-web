package com.friendfeed.web.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.friendfeed.core.security.Authorization;
import com.friendfeed.core.security.Authorizations.Public;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RequiresAuthorization {

    Class<? extends Authorization> authorization() default Public.class;

}
