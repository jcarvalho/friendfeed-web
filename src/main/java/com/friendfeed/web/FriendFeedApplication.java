package com.friendfeed.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.core.util.file.WebApplicationPath;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.friendfeed.web.pages.FriendFeedHome;
import com.friendfeed.web.pages.MountPage;
import com.friendfeed.web.pages.UnauthorizedPage;
import com.friendfeed.web.security.FriendFeedAuthorizationStrategy;

public class FriendFeedApplication extends WebApplication {

    @Override
    protected void init() {
        super.init();

        getResourceSettings().getResourceFinders().add(new WebApplicationPath(getServletContext(), "/"));
        getApplicationSettings().setAccessDeniedPage(UnauthorizedPage.class);

        getSecuritySettings().setAuthorizationStrategy(new FriendFeedAuthorizationStrategy());
        getSecuritySettings().setEnforceMounts(true);

        for (Entry<Class<? extends Page>, String> page : pages.entrySet()) {
            mountPage(page.getValue(), page.getKey());
        }
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return FriendFeedHome.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new FriendFeedSession(request);
    }

    /*
     * Static stuff
     */

    private static final Map<Class<? extends Page>, String> pages = new HashMap<>();

    public static void addPage(Class<? extends Page> page, MountPage mount) {
        pages.put(page, mount.path());
    }

}
