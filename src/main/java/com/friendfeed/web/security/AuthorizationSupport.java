package com.friendfeed.web.security;

import java.util.HashMap;
import java.util.Map;

import com.friendfeed.core.security.Authorization;

public class AuthorizationSupport {

    private static final Map<Class<? extends Authorization>, Authorization> authorizations = new HashMap<>();

    public static Authorization getAuthorizationForClass(Class<? extends Authorization> authorizationClass) {
        return authorizations.get(authorizationClass);
    }

    public static void registerNewAuthorizationClass(Class<? extends Authorization> authorizationClass) {
        if (authorizations.containsKey(authorizationClass)) {
            return;
        }
        try {
            Authorization authorization = authorizationClass.newInstance();
            authorizations.put(authorizationClass, authorization);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new Error("Cannot instantiate authorization " + authorizationClass.getName());
        }
    }

}
