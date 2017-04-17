package hospital.impl;

import hospital.dao.PeopleDao;
import hospital.types.People;
import hospital.types.Ward;
import lombok.AllArgsConstructor;
import hospital.dao.WardDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
@AllArgsConstructor
public class WardDaoImpl implements WardDao {
    final private SessionFactory sessionFactory;
    final private PeopleDao peopleDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartureDaoImpl.class);

    @Override
    public List<Ward> findAll() {
        try(Session session = sessionFactory.openSession()) {

            return session.createQuery("from Ward").list();
        }
    }

    @Override
    public List<Ward> findAllWithPeople() {
        try (Session session = sessionFactory.openSession()) {
            final List<Ward> wards = session.createQuery("from Ward").list();
            final List<People> peoples = peopleDao.findAll();

            final Map<Long, Ward> wardMap = new HashMap<>();
            wards.forEach(ward -> wardMap.put(ward.getWardId(),ward));

            peoples.forEach(people -> wardMap.get(people.getWardId()).addPeople(people));

            return wards;
        }
    }

    @Override
    public Ward findById(final long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ward.class,id);
        }
    }

    private List<Ward> getWardsFromQuery(final List list) {
        final Map<Long,Ward> wardMap = new HashMap<>();
        for (final Object object : list) {
            final Ward ward = (Ward)(((Object[])object)[0]);
            final People people = (People) (((Object[])object)[1]);

            wardMap.computeIfAbsent(
                    people.getWardId(),
                    k -> wardMap.put(ward.getWardId(),ward));

            ward.addPeople(people);
        }

        return wardMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(final long id) {
        try (Session session = sessionFactory.openSession()) {
            final Object persistentInstance = session.load(Ward.class, id);
            if (persistentInstance != null) {
                session.beginTransaction();
                peopleDao.deleteByWardId(id);
                session.delete(persistentInstance);
                session.getTransaction().commit();
                LOGGER.info("Ward with id {} was deleted with all people", id);
            } else {
                LOGGER.error("No Ward with id {}", id);
            }
        }
    }

    @Override
    public void deleteByDepartureId(final long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            findAllWithPeople().stream()
                    .filter(ward -> ward.getDepartureId() == id)
                    .forEach(ward -> peopleDao.deleteByWardId(ward.getWardId()));

            final Query query = session.createQuery("delete Ward where departureId = :id");
            query.setParameter("id",id);

            final int result = query.executeUpdate();

            if (result > 0) {
                LOGGER.info("From Ward table was deleted {} rows", result);
            } else {
                LOGGER.info("No Ward in departure {} or some error",id);
            }

            session.getTransaction().commit();
        }
    }
}
