package hospital;

import hospital.dao.DepartureDao;
import hospital.dao.DiagnosisDao;
import hospital.impl.DepartureDaoImpl;
import hospital.impl.DiagnosisDaoImpl;
import hospital.impl.PeopleDaoImpl;
import hospital.types.Departure;
import hospital.types.Diagnosis;
import hospital.types.People;
import hospital.types.Ward;
import hospital.dao.PeopleDao;
import hospital.dao.WardDao;
import hospital.impl.WardDaoImpl;
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
        return new DepartureDaoImpl(sessionFactory(),wardDao(),diagnosisDao());
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
