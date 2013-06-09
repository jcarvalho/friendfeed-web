package com.friendfeed.web.pages;

import org.apache.wicket.Session;

import com.friendfeed.core.security.Authorizations.LoggedIn;
import com.friendfeed.web.security.RequiresAuthorization;

@MountPage(path = "logout")
@RequiresAuthorization(authorization = LoggedIn.class)
public class LogoutPage extends FriendFeedPage {

    private static final long serialVersionUID = -3678525949530337500L;

    public LogoutPage() {
        Session.get().invalidate();
        setResponsePage(FriendFeedHome.class);
    }

}
