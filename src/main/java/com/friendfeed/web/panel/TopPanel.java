package com.friendfeed.web.panel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.friendfeed.core.domain.User;

public class TopPanel extends Panel {

    private static final long serialVersionUID = -8279097248984315833L;

    public TopPanel(String id, User user) {
        super(id);
        add(new Label("username", user == null ? "Anonymous" : user.getUsername()));
    }

}
