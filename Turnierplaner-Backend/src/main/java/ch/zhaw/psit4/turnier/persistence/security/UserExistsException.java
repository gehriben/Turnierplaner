package ch.zhaw.psit4.turnier.persistence.security;

public class UserExistsException extends Throwable {

    public UserExistsException(final String message) {
        super(message);
    }

}