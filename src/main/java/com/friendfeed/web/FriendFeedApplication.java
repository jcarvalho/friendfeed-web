package com.friendfeed.web;

import org.apache.wicket.Page;
import org.apache.wicket.core.util.file.WebApplicationPath;
import org.apache.wicket.protocol.http.WebApplication;

import com.friendfeed.web.pages.FriendFeedHome;
import com.friendfeed.web.security.FriendFeedAuthorizationStrategy;
import com.friendfeed.web.security.FriendFeedComponentInstantiationListener;

public class FriendFeedApplication extends WebApplication {

    @Override
    protected void init() {
        super.init();

        getResourceSettings().getResourceFinders().clear();
        getResourceSettings().getResourceFinders().add(new WebApplicationPath(getServletContext(), "/"));

        getSecuritySettings().setAuthorizationStrategy(new FriendFeedAuthorizationStrategy());
        getSecuritySettings().setUnauthorizedComponentInstantiationListener(new FriendFeedComponentInstantiationListener());
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return FriendFeedHome.class;
    }

}
