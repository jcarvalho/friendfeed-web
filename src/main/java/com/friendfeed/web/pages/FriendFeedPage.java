package com.friendfeed.web.pages;

import java.nio.charset.Charset;

import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.head.CssUrlReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptUrlReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;

import com.friendfeed.core.util.ConfigurationManager;
import com.friendfeed.web.FriendFeedApplication;
import com.friendfeed.web.panel.FriendFeedbackPanel;
import com.friendfeed.web.panel.TopPanel;

public abstract class FriendFeedPage extends WebPage {

    private static final long serialVersionUID = -2894308070463725312L;

    private static final String staticResourceBase = ConfigurationManager.getProperty("staticResources");

    public FriendFeedPage() {
        add(new TopPanel("user", FriendFeedApplication.getCurrentUser()));
        add(new FriendFeedbackPanel("feedbackError", new ErrorLevelFeedbackMessageFilter(FeedbackMessage.WARNING)));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(new CssUrlReferenceHeaderItem(staticResourceBase + "/css/bootstrap.min.css", "screen", ""));
        response.render(new CssUrlReferenceHeaderItem(staticResourceBase + "/css/styles.less.css", "screen", ""));

        response.render(new JavaScriptUrlReferenceHeaderItem(staticResourceBase + "/js/jquery.min.js", "", false, Charset
                .defaultCharset().toString(), ""));
        response.render(new JavaScriptUrlReferenceHeaderItem(staticResourceBase + "/js/bootstrap.min.js", "", false, Charset
                .defaultCharset().toString(), ""));
    }

}
