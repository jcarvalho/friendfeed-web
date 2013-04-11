package com.friendfeed.web.servlet;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import org.apache.wicket.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.friendfeed.web.FriendFeedApplication;
import com.friendfeed.web.pages.MountPage;
import com.friendfeed.web.security.AuthorizationSupport;
import com.friendfeed.web.security.RequiresAuthorization;

@HandlesTypes({ MountPage.class, RequiresAuthorization.class })
public class AnnotationInitializer implements ServletContainerInitializer {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationInitializer.class);

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        if (c == null) {
            return;
        }

        for (Class<?> clazz : c) {
            if (clazz.isAnnotationPresent(MountPage.class)) {
                if (!Page.class.isAssignableFrom(clazz)) {
                    throw new Error("Only Pages can be annotated with MountPage");
                }
                MountPage mount = clazz.getAnnotation(MountPage.class);
                RequiresAuthorization auth = clazz.getAnnotation(RequiresAuthorization.class);

                logger.debug("Adding page {} with path '{}'", clazz.getName(), mount.path());

                if (auth != null) {
                    AuthorizationSupport.registerNewAuthorizationClass(auth.authorization());
                }

                @SuppressWarnings("unchecked")
                Class<? extends Page> page = (Class<? extends Page>) clazz;
                FriendFeedApplication.addPage(page, mount);
            }
        }
    }
}
