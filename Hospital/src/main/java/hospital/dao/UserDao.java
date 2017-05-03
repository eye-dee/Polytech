package hospital.dao;

import hospital.types.User;

/**
 * Polytech
 * Created by igor on 03.05.17.
 */
public interface UserDao {
    User insert(User user);
    User findByLogin(String login);
}
