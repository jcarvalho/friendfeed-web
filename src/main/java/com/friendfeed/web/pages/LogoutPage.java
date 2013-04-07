package com.friendfeed.web.pages;

import org.apache.wicket.Session;

import com.friendfeed.core.security.Authorizations.LoggedIn;

@MountPage(value = "logout", authorization = LoggedIn.class)
public class LogoutPage extends FriendFeedPage {

    private static final long serialVersionUID = -3678525949530337500L;

    public LogoutPage() {
        Session.get().invalidateNow();
        setResponsePage(FriendFeedHome.class);
    }

}
