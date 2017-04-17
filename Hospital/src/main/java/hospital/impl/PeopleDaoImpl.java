package hospital.impl;

import hospital.dao.DiagnosisDao;
import hospital.dao.PeopleDao;
import hospital.types.Diagnosis;
import hospital.types.People;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
            final List<People> peoples = session.createQuery("FROM People").list();

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
            final Object persistentInstance = session.load(People.class, id);
            if (persistentInstance != null) {
                session.beginTransaction();
                session.delete(persistentInstance);
                session.getTransaction().commit();
                LOGGER.info("People with id {} was deleted", id);
            } else {
                LOGGER.error("No People with id {}", id);
            }
        }
    }

    @Override
    public void deleteByWardId(final long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            final Query query = session.createQuery("delete People where wardId = :id");
            query.setParameter("id",id);

            final int result = query.executeUpdate();

            if (result > 0) {
                LOGGER.info("From People table was deleted {} rows", result);
            } else {
                LOGGER.info("No people in ward {} or some error",id);
            }

            session.getTransaction().commit();
        }
    }
}
