package oracle.hospital.dao;

import oracle.hospital.types.Diagnosis;

import java.util.List;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public interface DiagnosisDao {
    List<Diagnosis> findAll();
}
