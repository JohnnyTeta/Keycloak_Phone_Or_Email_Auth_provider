package app.grife.keycloak.authenticator;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

import java.util.List;

public class PhoneOrEmailAuthenticator implements Authenticator {

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        String usernameOrPhone = context.getHttpRequest().getDecodedFormParameters().getFirst("username");

        UserModel user = null;
        if (usernameOrPhone.contains("@")) {
            // Assume email
            user = context.getSession().users().getUserByEmail(context.getRealm(), usernameOrPhone);
        } else {
            // Assume phone number
            List<UserModel> users = context.getSession().users().searchForUserByUserAttributeStream(context.getRealm(), "phoneNumber", usernameOrPhone)
                    .toList();
            if (!users.isEmpty()) {
                user = users.getFirst();
            }
        }

        if (user != null) {
            context.setUser(user);
            context.success();
        } else {
            context.failure(AuthenticationFlowError.INVALID_USER);
        }
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        // Implement action if needed
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        // TODO
    }

    @Override
    public void close() {
        // Clean up resources if needed
    }
}
