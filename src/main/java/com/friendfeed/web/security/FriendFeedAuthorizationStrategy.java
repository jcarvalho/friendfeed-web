package com.friendfeed.web.security;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.apache.wicket.request.component.IRequestableComponent;

import com.friendfeed.core.security.Authorization;
import com.friendfeed.web.FriendFeedApplication;
import com.friendfeed.web.pages.MountPage;

public class FriendFeedAuthorizationStrategy implements IAuthorizationStrategy {

    @Override
    public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {
        MountPage mount = componentClass.getAnnotation(MountPage.class);
        if (mount != null) {
            Authorization authorization = AuthorizationSupport.getAuthorizationForClass(mount.authorization());
            if (!authorization.isUserAuthorized(FriendFeedApplication.getCurrentUser())) {
                unauthorized(componentClass);
            }
        }
        return true;
    }

    private void unauthorized(Class<? extends IRequestableComponent> clazz) {
        Session.get().invalidate();
        throw new UnauthorizedInstantiationException(clazz);
    }

    @Override
    public boolean isActionAuthorized(Component component, Action action) {
        return true;
    }

}
