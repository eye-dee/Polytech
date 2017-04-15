package hospital.impl;

import hospital.dao.PeopleDao;
import hospital.types.People;
import hospital.types.Ward;
import lombok.AllArgsConstructor;
import hospital.dao.WardDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        /*try (Session session = sessionFactory.openSession()){
            final List list = session.createQuery("from Ward ward " +
                    "join People people on people.wardId = ward.wardId").list();

            return getWardsFromQuery(list);
        }*/
        try (Session session = sessionFactory.openSession()) {
            final List<Ward> wards = session.createQuery("from Ward").list();
            final List<People> peoples = peopleDao.findAll();

            final Map<Long, Ward> wardMap = new HashMap<>();
            wards.forEach(ward -> wardMap.put(ward.getWardId(),ward));

            peoples.forEach(people -> wardMap.get(people.getWardId()).addPeople(people));

            return wards;
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
}
