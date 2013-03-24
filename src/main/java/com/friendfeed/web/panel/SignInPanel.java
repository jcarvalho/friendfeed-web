package com.friendfeed.web.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

import com.friendfeed.core.domain.User;
import com.friendfeed.web.FriendFeedApplication;
import com.friendfeed.web.pages.FriendFeedHome;

public class SignInPanel extends Panel {

    private static final long serialVersionUID = -3480067697256907945L;

    public SignInPanel(String id) {
        super(id);

        add(new SignInForm("signInForm"));
    }

    public final class SignInForm extends Form<Void> {

        private static final long serialVersionUID = 7634349439380603856L;

        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";

        private final ValueMap properties = new ValueMap();

        public SignInForm(final String id) {
            super(id);

            add(new TextField<String>(USERNAME, new PropertyModel<String>(properties, USERNAME)));
            add(new PasswordTextField(PASSWORD, new PropertyModel<String>(properties, PASSWORD)));
        }

        @Override
        public final void onSubmit() {
            User user = new User();
            user.setUsername(getUsername());
            FriendFeedApplication.setCurrentUser(user);
            setResponsePage(FriendFeedHome.class);
        }

        /**
         * @return
         */
        private String getUsername() {
            return properties.getString(USERNAME);
        }

    }

}
