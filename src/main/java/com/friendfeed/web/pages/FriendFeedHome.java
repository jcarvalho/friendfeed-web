package com.friendfeed.web.pages;

import org.apache.wicket.markup.html.basic.Label;

import pt.ist.fenixframework.FenixFramework;

public class FriendFeedHome extends FriendFeedPage {

    private static final long serialVersionUID = 5966860435038911759L;

    public FriendFeedHome() {
        add(new Label("message", FenixFramework.getDomainRoot().toString()));
    }
}
