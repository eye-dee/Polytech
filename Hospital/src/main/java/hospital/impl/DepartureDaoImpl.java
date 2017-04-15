package hospital.impl;

import hospital.types.Departure;
import hospital.types.Ward;
import lombok.AllArgsConstructor;
import hospital.dao.DepartureDao;
import hospital.dao.WardDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
