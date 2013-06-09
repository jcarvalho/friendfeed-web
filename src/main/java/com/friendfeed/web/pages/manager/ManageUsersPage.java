package com.friendfeed.web.pages.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.form.StatelessForm;

import com.friendfeed.core.domain.FriendFeed;
import com.friendfeed.core.domain.User;
import com.friendfeed.core.security.Authorizations.LoggedIn;
import com.friendfeed.web.pages.FriendFeedPage;
import com.friendfeed.web.pages.MountPage;
import com.friendfeed.web.security.RequiresAuthorization;

@MountPage(path = "/users/manage")
@RequiresAuthorization(authorization = LoggedIn.class)
public class ManageUsersPage extends FriendFeedPage {

    private static final long serialVersionUID = -8650667233030919842L;

    public ManageUsersPage() {
        StatelessForm<Void> form = new StatelessForm<Void>("searchUsersForm");
        add(form);

        final AutoCompleteTextField<User> userField = new AutoCompleteTextField<User>("usernameSearch", User.class) {

            private static final long serialVersionUID = -7214620480644087011L;

            @Override
            protected Iterator<User> getChoices(String currentValue) {
                List<User> users = new ArrayList<User>(10);
                for (User user : FriendFeed.getInstance().getUserSet()) {
                    //if (user.getName().startsWith("currentValue")) {
                    users.add(user);
                    //}
                    if (users.size() == 10) {
                        break;
                    }
                }
                return users.iterator();
            }
        };

        form.add(userField);

        userField.add(new AjaxFormSubmitBehavior(form, "onchange") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                System.out.println("User is now: " + target);
            }

            @Override
            protected void onError(AjaxRequestTarget target) {
            }
        });
    }
}
