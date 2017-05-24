package hospital.impl;

import hospital.dao.DiagnosisDao;
import hospital.types.Departure;
import hospital.types.Ward;
import lombok.AllArgsConstructor;
import hospital.dao.DepartureDao;
import hospital.dao.WardDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */

@AllArgsConstructor
public class DepartureDaoImpl implements DepartureDao {
    final private SessionFactory sessionFactory;
    final private WardDao wardDao;
    final private DiagnosisDao diagnosisDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartureDaoImpl.class);

    @Override
    public List<Departure> findAll() {
        try(Session session = sessionFactory.openSession()) {

            return session.createQuery("from Departure").list();
        }
    }

    @Override
    public List<Departure> findAllWithWards() {
        final List<Departure> departureWithOutWards = findAll();
        final List<Ward> wards = wardDao.findAllWithPeople();

        final HashMap<Long,Departure> departureHashMap = new HashMap<>();

        departureWithOutWards.forEach(departure ->
                departureHashMap.put(departure.getDepartureId(),departure));

        wards.forEach(ward -> departureHashMap.get(ward.getDepartureId()).addWard(ward));

        return departureWithOutWards;
    }

    @Override
    public void deleteDeparture(final long id) {
        try (Session session = sessionFactory.openSession()) {
            final Object persistentInstance = session.load(Departure.class, id);
            if (persistentInstance != null) {
                session.beginTransaction();
                wardDao.deleteByDepartureId(id);
                diagnosisDao.deleteByDepartureId(id);
                session.delete(persistentInstance);
                session.getTransaction().commit();
                LOGGER.info("Ward with id {} was deleted with all people", id);
            } else {
                LOGGER.error("No Ward with id {}", id);
            }
        }
    }

    @Override
    public Long findByName(final String name) {
        try (Session session = sessionFactory.openSession()) {
            final Query query = session.createQuery("from Departure where departureName = :name");
            query.setParameter("name",name);

            final List list = query.list();

            if (list.size() == 0) {
                return -1L;
            } else {
                return ((Departure)list.get(0)).getDepartureId();
            }

        }
    }

    @Override
    public Departure insert(final Departure departure) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(departure);
            session.getTransaction().commit();
        }

        return departure;
    }
}
