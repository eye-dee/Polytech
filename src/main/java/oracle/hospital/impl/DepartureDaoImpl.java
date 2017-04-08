package oracle.hospital.impl;

import lombok.AllArgsConstructor;
import oracle.hospital.dao.DepartureDao;
import oracle.hospital.dao.WardDao;
import oracle.hospital.types.Departure;
import oracle.hospital.types.Ward;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
