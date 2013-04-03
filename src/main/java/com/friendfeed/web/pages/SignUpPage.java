package com.friendfeed.web.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.friendfeed.core.application.Authenticate;

@MountPage("signup")
public class SignUpPage extends FriendFeedPage {

    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!_-]).{6,20})";

    private static final long serialVersionUID = -6440993053327515166L;

    public SignUpPage() {
        add(new SignUpForm("signUpForm", new CompoundPropertyModel<SignUpBean>(new SignUpBean())));
    }

    private static class SignUpForm extends StatelessForm<SignUpBean> {

        private static final long serialVersionUID = -814100904777031887L;

        public SignUpForm(String id, IModel<SignUpBean> model) {
            super(id, model);
            setOutputMarkupId(true);

            add(new RequiredTextField<String>("name").add(StringValidator.minimumLength(4)));

            final FormComponent<String> screenNameComponent = new RequiredTextField<String>("screenName");
            add(screenNameComponent);

            OnChangeAjaxBehavior screenNameChange = new OnChangeAjaxBehavior() {

                private static final long serialVersionUID = -8467996172937558711L;

                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                    System.out.println("Target is now: " + screenNameComponent.getDefaultModelObjectAsString());
                }
            };
            screenNameComponent.add(screenNameChange);

            add(new EmailTextField("email").setRequired(true));

            PasswordTextField passwordField = new PasswordTextField("password");
            passwordField.add(new PatternValidator(PASSWORD_PATTERN));

            PasswordTextField passwordConfirmationField = new PasswordTextField("passwordConfirm");

            add(passwordField);
            add(passwordConfirmationField);

            add(new EqualPasswordInputValidator(passwordField, passwordConfirmationField));
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();
            SignUpBean bean = (SignUpBean) getDefaultModelObject();
            boolean success = Authenticate.createUser(bean.getName(), bean.getScreenName(), bean.getEmail(), bean.getPassword());
            if (success) {
                success("User created successfully. Check your email to activate your account.");
                setResponsePage(FriendFeedHome.class);
            } else {
                error("An user with that name already exists!");
            }
        }
    }

    static class SignUpBean implements IClusterable {
        private static final long serialVersionUID = 7190286912086616503L;

        String name, screenName, email, password, passwordConfirm;

        public String getName() {
            return name;
        }

        public String getScreenName() {
            return screenName;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

}
