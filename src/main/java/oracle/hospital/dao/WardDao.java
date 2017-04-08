package oracle.hospital.dao;

import oracle.hospital.types.Ward;

import java.util.List;

/**
 * Polytech
 * Created by igor on 07.04.17.
 */
public interface WardDao {
    List<Ward> findAll();

    List<Ward> findAllWithPeople();
}
