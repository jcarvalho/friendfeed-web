package com.friendfeed.web;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.friendfeed.core.domain.User;

public class FriendFeedSession extends WebSession {

    private static final long serialVersionUID = -8529250746151185083L;

    private User user;

    public FriendFeedSession(Request request) {
        super(request);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static FriendFeedSession get() {
        return (FriendFeedSession) Session.get();
    }

}
