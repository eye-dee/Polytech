package hospital.visualization.login;

import hospital.dao.UserDao;
import hospital.types.User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;

/**
 * Polytech
 * Created by igor on 03.05.17.
 */
public class LoginWindow extends JPanel {
    private final JFormattedTextField login;
    private final JPasswordField password;

    private final JButton confirm;

    private final UserDao userDao;
    private final User currentUser;

    private JTabbedPane jTabbedPane;

    public LoginWindow(final UserDao userDao, final User currentUser) {
        super(new GridLayout(3, 0));

        this.userDao = userDao;
        this.currentUser = currentUser;

        login = new JFormattedTextField();
        login.setBorder(new TitledBorder("Логин"));
        password = new JPasswordField();
        password.setBorder(new TitledBorder("Пароль"));
        confirm = new JButton("Войти");

        add(login);
        add(password);
        add(confirm);

        confirm.addActionListener(e -> {
            final User user = this.userDao.findByLogin(login.getText());

            if (user.getLogin() == null) {
                JOptionPane.showMessageDialog(this,
                        "Incorrect User",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
                setEnabled(false);
            } else {
                if (Arrays.equals(
                        password.getPassword(),
                        user.getPassword().toCharArray())) {
                    currentUser.setLogin(user.getLogin());
                    currentUser.setPassword(user.getPassword());
                    JOptionPane.showMessageDialog(this,
                            "Success",
                            "Inane error",
                            JOptionPane.INFORMATION_MESSAGE);

                    setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Incorrect Password",
                            "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                    setEnabled(false);
                }
            }
        });
    }

    public void setjTabbedPane(final JTabbedPane jTabbedPane) {
        this.jTabbedPane = jTabbedPane;

        jTabbedPane.addChangeListener(
                e -> {
                    final int index = jTabbedPane.getSelectedIndex();
                    if (index == 0) {
                        setEnabled(false);
                    }
                }
        );
    }

    public void setEnabled(final boolean flag) {
        final int size = jTabbedPane.getComponents().length;
        for (int i = 1; i < size; ++i) {
            jTabbedPane.setEnabledAt(i, flag);
        }
    }
}
