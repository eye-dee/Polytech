package hospital.dao;

import hospital.types.Departure;

import java.util.List;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public interface DepartureDao {
    List<Departure> findAll();

    List<Departure> findAllWithWards();
}
