package com.friendfeed.web.pages.manager;

import com.friendfeed.core.security.Authorizations.LoggedIn;
import com.friendfeed.web.pages.FriendFeedPage;
import com.friendfeed.web.pages.MountPage;
import com.friendfeed.web.security.RequiresAuthorization;

@MountPage(path = "/users/manage")
@RequiresAuthorization(authorization = LoggedIn.class)
public class ManageUsersPage extends FriendFeedPage {

    private static final long serialVersionUID = -8650667233030919842L;

}
