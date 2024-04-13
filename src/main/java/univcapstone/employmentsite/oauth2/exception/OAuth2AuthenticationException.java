package univcapstone.employmentsite.oauth2.exception;

import org.springframework.security.core.AuthenticationException;

public class OAuth2AuthenticationException extends AuthenticationException {
    public OAuth2AuthenticationException(String msg) {
        super(msg);
    }
}
