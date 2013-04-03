package com.friendfeed.web.panel;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

import com.friendfeed.core.application.Authenticate;
import com.friendfeed.core.domain.User;
import com.friendfeed.web.FriendFeedApplication;
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

        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";

        private final ValueMap properties = new ValueMap();

        public SignInForm(final String id) {
            super(id);

            add(new TextField<String>(USERNAME, new PropertyModel<String>(properties, USERNAME)).setType(String.class)
                    .setRequired(false));
            add(new PasswordTextField(PASSWORD, new PropertyModel<String>(properties, PASSWORD)).setRequired(false));
        }

        @Override
        public final void onSubmit() {
            if (getUsername() == null) {
                error("Username cannot be null");
                return;
            }
            User user = Authenticate.login(getUsername(), getPassword());
            if (user == null) {
                error("Incorrect login data");
                return;
            }

            FriendFeedApplication.setCurrentUser(user);
            setResponsePage(FriendFeedHome.class);
        }

        /**
         * @return
         */
        private String getUsername() {
            return properties.getString(USERNAME);
        }

        private String getPassword() {
            return properties.getString(PASSWORD);
        }

    }

}
