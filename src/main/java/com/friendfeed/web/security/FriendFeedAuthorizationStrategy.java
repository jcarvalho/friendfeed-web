package com.friendfeed.web.security;

import org.apache.wicket.Component;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.component.IRequestableComponent;

import com.friendfeed.core.security.Authorization;
import com.friendfeed.web.FriendFeedSession;
import com.friendfeed.web.pages.MountPage;

public class FriendFeedAuthorizationStrategy implements IAuthorizationStrategy {

    @Override
    public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {
        MountPage mount = componentClass.getAnnotation(MountPage.class);
        if (mount != null) {
            Authorization authorization = AuthorizationSupport.getAuthorizationForClass(mount.authorization());
            return authorization.isUserAuthorized(FriendFeedSession.get().getUser());
        }
        return true;
    }

    @Override
    public boolean isActionAuthorized(Component component, Action action) {
        if (action.equals(Component.RENDER)) {
            if (component instanceof BookmarkablePageLink<?>) {
                BookmarkablePageLink<?> link = (BookmarkablePageLink<?>) component;
                return isInstantiationAuthorized(link.getPageClass());
            }
        }
        return true;
    }
}
