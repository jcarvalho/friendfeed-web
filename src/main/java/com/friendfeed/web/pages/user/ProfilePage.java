package com.friendfeed.web.pages.user;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.friendfeed.core.domain.User;
import com.friendfeed.web.pages.FriendFeedPage;
import com.friendfeed.web.pages.MountPage;

@MountPage(path = "/user/${screenName}")
public class ProfilePage extends FriendFeedPage {

    private static final long serialVersionUID = 7132218768732497863L;

    private final User targetUser;

    public ProfilePage(PageParameters parameters) {
        targetUser = User.findByScreenName(parameters.get("screenName").toString());
        if (targetUser == null) {
            notFound();
        }
        add(new Label("pageTitle", targetUser.getScreenName() + " (" + targetUser.getName() + ")"));
        add(new Label("screenName", targetUser.getScreenName()));
    }

}
