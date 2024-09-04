package app.grife.keycloak.authenticator;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.Collections;
import java.util.List;

public class PhoneOrEmailAuthenticatorFactory implements AuthenticatorFactory {

    public static final String PROVIDER_ID = "phone-or-email-authenticator";

    @Override
    public Authenticator create(KeycloakSession session) {
        return new PhoneOrEmailAuthenticator();
    }

    @Override
    public void init(Config.Scope scope) {
        // Initialization code if needed
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
        // Post initialization code if needed
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "Phone or Email Authenticator";
    }

    @Override
    public String getReferenceCategory() {
        return "authenticator";
    }

    @Override
    public boolean isConfigurable() {
        return false; // Change to true if your authenticator has configurable properties
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return new AuthenticationExecutionModel.Requirement[] {
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.ALTERNATIVE
        };
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false; // Set to true if the authenticator requires user setup
    }

    @Override
    public String getHelpText() {
        return "Allows login with either phone number or email.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        // Return a list of configuration properties if your authenticator is configurable
        return Collections.emptyList();
    }

    @Override
    public void close() {
        // Cleanup resources if needed
    }
}
