package com.friendfeed.web.panel;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import com.friendfeed.web.FriendFeedSession;
import com.friendfeed.web.pages.LogoutPage;
import com.friendfeed.web.pages.manager.ManageUsersPage;

public class TopPanel extends Panel {

    private static final long serialVersionUID = -8279097248984315833L;

    public TopPanel(String id) {
        super(id);
        add(new Label("username", new PropertyModel<>(this, "session.user.screenName")));

        add(new WebMarkupContainer("loginLink") {
            private static final long serialVersionUID = -1058430501543689942L;

            @Override
            public boolean isVisible() {
                return FriendFeedSession.get().getUser() == null;
            }
        });

        add(new BookmarkablePageLink<ManageUsersPage>("manageUsersLink", ManageUsersPage.class));
        add(new BookmarkablePageLink<LogoutPage>("logoutLink", LogoutPage.class));

        add(new SignInPanel("signInPanel"));
    }
}
