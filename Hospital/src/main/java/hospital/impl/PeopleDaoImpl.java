package hospital.impl;

import hospital.dao.DiagnosisDao;
import hospital.types.Diagnosis;
import hospital.types.People;
import lombok.AllArgsConstructor;
import hospital.dao.PeopleDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Polytech
 * Created by igor on 07.04.17.
 */

@Repository
@AllArgsConstructor
public class PeopleDaoImpl implements PeopleDao {
    final private SessionFactory sessionFactory;
    final private DiagnosisDao diagnosisDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartureDaoImpl.class);


    @Override
    public List<People> findAll() {
        try(Session session = sessionFactory.openSession()) {
            final List<People> peoples = session.createQuery("from People").list();

            final List<Diagnosis> diagnoses = diagnosisDao.findAll();

            final Map<Long,Diagnosis> diagnosisMap = new HashMap<>();
            diagnoses.forEach(diagnosis ->
                    diagnosisMap.put(diagnosis.getDiagnosisId(),diagnosis));

            peoples.forEach(people ->
                    people.setDiagnosis(diagnosisMap.get(people.getDiagnosisId())));

            return peoples;
        }
    }

    @Override
    public People insert(final People people) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(people);
            session.getTransaction().commit();
        }

        return people;
    }

    @Override
    public void deleteById(final long id) {
        try (Session session = sessionFactory.openSession()) {
            session.delete(People.builder().peopleId(id).build());
            LOGGER.info("People with id {} was deleted",id);
        }
    }
}
