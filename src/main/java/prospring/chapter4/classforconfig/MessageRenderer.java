package prospring.chapter4.classforconfig;

/**
 * Polytech
 * Created by igor on 17.03.17.
 */
public interface MessageRenderer {
    void render();
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}
