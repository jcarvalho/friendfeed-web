package com.friendfeed.web.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import pt.ist.fenixframework.FenixFramework;

public class FriendFeedHome extends WebPage {

    private static final long serialVersionUID = 5966860435038911759L;

    public FriendFeedHome() {
        add(new Label("message", FenixFramework.getDomainRoot().toString()));
    }

}
