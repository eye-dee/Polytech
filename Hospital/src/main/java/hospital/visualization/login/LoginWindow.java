package hospital.visualization.login;

import hospital.dao.UserDao;
import hospital.types.User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

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

    public LoginWindow(final UserDao userDao, final User currentUser) {
        super(new GridLayout(3,0));

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
            final User user = userDao.findByLogin(login.getText());

            if (user.getLogin() == null) {
                System.out.println("No user");
            } else {
                currentUser.setLogin(user.getLogin());
                currentUser.setPassword(user.getPassword());
                System.out.println("Loggined");
            }
        });
    }
}
