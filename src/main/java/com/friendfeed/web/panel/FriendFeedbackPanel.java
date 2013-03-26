package com.friendfeed.web.panel;

import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class FriendFeedbackPanel extends FeedbackPanel {

    private static final long serialVersionUID = 6753873042256084703L;

    public FriendFeedbackPanel(String id, IFeedbackMessageFilter filter) {
        super(id, filter);
    }

}
