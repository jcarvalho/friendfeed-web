package com.friendfeed.web.pages;

import com.friendfeed.core.domain.User;
import com.friendfeed.web.FriendFeedSession;
import com.friendfeed.web.panel.FeedsPanel;

public class FriendFeedHome extends FriendFeedPage {

    private static final long serialVersionUID = 5966860435038911759L;

    public FriendFeedHome() {
        User user = FriendFeedSession.get().getUser();

        if (user != null) {
            add(new FeedsPanel("feeds", user));
        }
    }

}
