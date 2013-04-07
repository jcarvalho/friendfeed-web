package com.friendfeed.web.pages;

import java.nio.charset.Charset;

import org.apache.wicket.markup.head.CssUrlReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptUrlReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;

import com.friendfeed.core.util.ConfigurationManager;

public class BasePage extends WebPage {

    private static final long serialVersionUID = -317493788078378898L;

    private static final String staticResourceBase = ConfigurationManager.getProperty("staticResources");

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
