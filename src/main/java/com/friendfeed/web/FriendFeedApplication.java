package com.friendfeed.web;

import org.apache.wicket.Page;
import org.apache.wicket.core.util.file.WebApplicationPath;
import org.apache.wicket.protocol.http.WebApplication;

import com.friendfeed.web.pages.FriendFeedHome;

public class FriendFeedApplication extends WebApplication {

    @Override
    protected void init() {
        super.init();

        getResourceSettings().getResourceFinders().add(new WebApplicationPath(getServletContext(), "/"));
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return FriendFeedHome.class;
    }

}
