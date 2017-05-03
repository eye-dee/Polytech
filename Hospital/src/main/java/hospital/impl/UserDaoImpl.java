package hospital.impl;

import hospital.dao.UserDao;
import hospital.types.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Polytech
 * Created by igor on 03.05.17.
 */

@AllArgsConstructor
public class UserDaoImpl implements UserDao {
    final private SessionFactory sessionFactory;

    @Override
    public User insert(final User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public User findByLogin(final String login) {
        try (Session session = sessionFactory.openSession()) {

            final Query query = session.createQuery("from UsersTable where login = :login");
            query.setParameter("login",login);

            final List list = query.list();

            if (list.isEmpty()) {
                return User.builder().build();
            } else {
                return (User) list.get(0);
            }
        }
    }
}
