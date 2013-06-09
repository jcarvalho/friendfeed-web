package com.friendfeed.web.panel;

import org.apache.wicket.markup.html.panel.Panel;

import com.friendfeed.core.domain.User;

public class FeedsPanel extends Panel {

    private static final long serialVersionUID = 8756320711725465413L;

    public FeedsPanel(String id, User user) {
        super(id);
    }

}
