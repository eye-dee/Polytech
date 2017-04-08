package oracle.hospital.impl;

import oracle.hospital.dao.DiagnosisDao;
import oracle.hospital.types.Diagnosis;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public class DiagnosisDaoImpl implements DiagnosisDao{
    final private SessionFactory sessionFactory;

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
