package hospital.impl;

import hospital.dao.DiagnosisDao;
import hospital.types.Diagnosis;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public class DiagnosisDaoImpl implements DiagnosisDao {
    final private SessionFactory sessionFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartureDaoImpl.class);

    public DiagnosisDaoImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Diagnosis> findAll() {
        try(Session session = sessionFactory.openSession()) {

            return session.createQuery("from Diagnosis").list();
        }
    }

    @Override
    public Long findByName(final String name) {
        try(Session session = sessionFactory.openSession()) {
            final Query query = session.createQuery("from Diagnosis where diagnosisName = :name");
            query.setParameter("name", name);
            return ((Diagnosis) query.list().get(0)).getDiagnosisId();
        }
    }

    @Override
    public Diagnosis insert(final Diagnosis diagnosis) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(diagnosis);
            session.getTransaction().commit();
        }
        return diagnosis;
    }

    @Override
    public void deleteByDepartureId(final long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            final Query query = session.createQuery("delete Diagnosis where departureId = :id");
            query.setParameter("id",id);

            final int result = query.executeUpdate();

            if (result > 0) {
                LOGGER.info("From Diagnosis table was deleted {} rows", result);
            } else {
                LOGGER.info("No diagnosis in ward {} or some error",id);
            }

            session.getTransaction().commit();
        }
    }
}
