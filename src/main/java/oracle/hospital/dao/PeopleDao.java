package oracle.hospital.dao;

import oracle.hospital.types.People;

import java.util.List;

/**
 * Polytech
 * Created by igor on 07.04.17.
 */
public interface PeopleDao {
    List<People> findAll();

    People insert(People people);

    void deleteById(long id);
}
