package com.friendfeed.web.panel;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.friendfeed.core.domain.User;
import com.friendfeed.web.FriendFeedApplication;

public class TopPanel extends Panel {

    private static final long serialVersionUID = -8279097248984315833L;

    public TopPanel(String id, User user) {
        super(id);
        add(new Label("username", user == null ? "" : "Welcome " + user.getUsername()));

        add(new WebMarkupContainer("loginLink") {
            private static final long serialVersionUID = -1058430501543689942L;

            @Override
            public boolean isVisible() {
                return FriendFeedApplication.getCurrentUser() == null;
            }
        });

        add(new Link<Void>("logoutLink") {

            private static final long serialVersionUID = 1929981792291846295L;

            @Override
            public void onClick() {
                Session.get().invalidateNow();
            }

            @Override
            public boolean isVisible() {
                return FriendFeedApplication.getCurrentUser() != null;
            }
        });

        add(new SignInPanel("signInPanel"));
    }

}
