package com.friendfeed.web.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.Model;

import com.friendfeed.core.util.ConfigurationManager;

public class StaticImage extends WebComponent {

    private static final String staticResourceBase = ConfigurationManager.getProperty("staticResources");

    private static final long serialVersionUID = 7582977918678779927L;

    public StaticImage(String id, String url) {
        super(id, new Model<String>(staticResourceBase + url));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        checkComponentTag(tag, "img");
        tag.put("src", getDefaultModelObjectAsString());
    }

}
