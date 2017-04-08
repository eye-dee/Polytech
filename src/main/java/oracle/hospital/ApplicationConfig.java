package oracle.hospital;

import oracle.hospital.dao.DepartureDao;
import oracle.hospital.dao.DiagnosisDao;
import oracle.hospital.dao.PeopleDao;
import oracle.hospital.dao.WardDao;
import oracle.hospital.impl.DepartureDaoImpl;
import oracle.hospital.impl.DiagnosisDaoImpl;
import oracle.hospital.impl.PeopleDaoImpl;
import oracle.hospital.impl.WardDaoImpl;
import oracle.hospital.types.Departure;
import oracle.hospital.types.Diagnosis;
import oracle.hospital.types.People;
import oracle.hospital.types.Ward;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

/**
 * Polytech
 * Created by igor on 07.04.17.
 */

@Configuration
@ComponentScan(basePackages = "oracle.hospital.*")
public class ApplicationConfig {
    @Bean
    public org.hibernate.cfg.Configuration configuration() {
        Locale.setDefault(Locale.ENGLISH);
        return new org.hibernate.cfg.Configuration();
    }

    @Bean(destroyMethod = "close")
    public SessionFactory sessionFactory() {
        final org.hibernate.cfg.Configuration configuration = configuration().configure("/oracle.hospital/hibernate.cfg.xml");

        configuration.addAnnotatedClass(People.class);
        configuration.addAnnotatedClass(Diagnosis.class);
        configuration.addAnnotatedClass(Ward.class);
        configuration.addAnnotatedClass(Departure.class);

        return configuration.buildSessionFactory();
    }

    /*@Bean
    @Scope("prototype")
    Session session() {
        return sessionFactory().openSession();
    }*/

    @Bean
    PeopleDao peopleDao() {
        return new PeopleDaoImpl(sessionFactory(),diagnosisDao());
    }

    @Bean
    DepartureDao departureDao() {
        return new DepartureDaoImpl(sessionFactory(),wardDao());
    }

    @Bean
    DiagnosisDao diagnosisDao() {
        return new DiagnosisDaoImpl(sessionFactory());
    }

    @Bean
    WardDao wardDao() {
        return new WardDaoImpl(sessionFactory(),peopleDao());
    }
}
