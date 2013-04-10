package com.friendfeed.web.panel;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import com.friendfeed.core.application.Authenticate;
import com.friendfeed.core.domain.User;
import com.friendfeed.web.FriendFeedSession;
import com.friendfeed.web.component.StaticImage;
import com.friendfeed.web.pages.FriendFeedHome;

public class SignInPanel extends Panel {

    private static final long serialVersionUID = -3480067697256907945L;

    public SignInPanel(String id) {
        super(id);

        add(new StaticImage("twitterLogo", "/img/twitter-bird-white-on-blue.png"));
        add(new StaticImage("facebookLogo", "/img/f_logo.png"));
        add(new StaticImage("googleLogo", "/img/google_search.png"));
        add(new StaticImage("twitterSignIn", "/img/sign-in-with-twitter-gray.png"));
        add(new SignInForm("signInForm"));
    }

    public final class SignInForm extends StatelessForm<Void> {

        private static final long serialVersionUID = 7634349439380603856L;

        private String username;
        private String password;

        public SignInForm(final String id) {
            super(id);

            add(new TextField<String>("username", new PropertyModel<String>(this, "username"), String.class).setRequired(false));
            add(new PasswordTextField("password", new PropertyModel<String>(this, "password")).setRequired(false));
        }

        @Override
        public final void onSubmit() {
            if (username == null) {
                error("Username cannot be null");
                return;
            }
            User user = Authenticate.login(username, password);
            if (user == null) {
                error("Incorrect login data");
                return;
            }

            FriendFeedSession.get().setUser(user);
            getSession().bind();
            error("User set: " + user);
            setResponsePage(FriendFeedHome.class);
        }

    }

}
