package prospring.chapter4.classforconfig;

/**
 * Polytech
 * Created by igor on 17.03.17.
 */
public class ConfigurableMessageProvider implements MessageProvider {
    private String message;

    public ConfigurableMessageProvider() {

    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public ConfigurableMessageProvider(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
