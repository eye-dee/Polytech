package hospital.impl;

import hospital.dao.DiagnosisDao;
import hospital.types.Diagnosis;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
