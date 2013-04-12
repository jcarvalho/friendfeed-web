package com.friendfeed.web.pages;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;

import com.friendfeed.web.panel.FriendFeedbackPanel;
import com.friendfeed.web.panel.TopPanel;

public abstract class FriendFeedPage extends BasePage {

    private static final long serialVersionUID = -2894308070463725312L;

    public FriendFeedPage() {
        add(new TopPanel("user"));
        add(new FriendFeedbackPanel("feedbackError", new ErrorLevelFeedbackMessageFilter(FeedbackMessage.WARNING)));
    }

    protected void notFound() {
        throw new RestartResponseAtInterceptPageException(NotFoundPage.class);
    }

}
