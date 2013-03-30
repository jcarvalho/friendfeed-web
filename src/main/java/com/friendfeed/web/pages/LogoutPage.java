package com.friendfeed.web.pages;

import org.apache.wicket.Session;

@MountPage("logout")
public class LogoutPage extends FriendFeedPage {

    private static final long serialVersionUID = -3678525949530337500L;

    public LogoutPage() {
        Session.get().invalidateNow();
        setResponsePage(FriendFeedHome.class);
    }

}
