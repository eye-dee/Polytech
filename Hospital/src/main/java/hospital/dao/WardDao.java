package hospital.dao;

import hospital.types.Ward;

import java.util.List;

/**
 * Polytech
 * Created by igor on 07.04.17.
 */
public interface WardDao {
    List<Ward> findAll();

    List<Ward> findAllWithPeople();
    Ward findById(long id);

    void deleteById(long id);

    void deleteByDepartureId(long id);
}
