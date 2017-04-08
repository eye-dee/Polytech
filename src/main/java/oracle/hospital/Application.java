package oracle.hospital;

import oracle.hospital.dao.DepartureDao;
import oracle.hospital.dao.DiagnosisDao;
import oracle.hospital.dao.PeopleDao;
import oracle.hospital.dao.WardDao;
import oracle.hospital.types.Departure;
import oracle.hospital.types.Diagnosis;
import oracle.hospital.types.People;
import oracle.hospital.types.Ward;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */

public class Application {
    public static void main(final String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        final PeopleDao peopleDao = applicationContext.getBean("peopleDao", PeopleDao.class);
        final WardDao wardDao = applicationContext.getBean("wardDao", WardDao.class);
        final DiagnosisDao diagnosisDao = applicationContext.getBean("diagnosisDao", DiagnosisDao.class);
        final DepartureDao departureDao = applicationContext.getBean("departureDao", DepartureDao.class);

        final List<People> peoples = peopleDao.findAll();
        System.out.println("People List:");
        peoples.forEach(System.out::println);
        System.out.println("--------------------------");

        final List<Ward> wards = wardDao.findAllWithPeople();
        System.out.println("Ward List:");
        wards.forEach(System.out::println);
        System.out.println("--------------------------");

        final List<Diagnosis> diagnoses = diagnosisDao.findAll();
        System.out.println("Diagnosis List:");
        diagnoses.forEach(System.out::println);
        System.out.println("--------------------------");

        final List<Departure> departures = departureDao.findAllWithWards();
        System.out.println("Departure List:");
        departures.forEach(System.out::println);
        System.out.println("--------------------------");


        /*peopleDao.insert(new People(0,"firtName","lastName","fatherName",1,1));
        System.out.println("inserted");*/

        final SessionFactory sessionFactory = (SessionFactory)applicationContext.getBean("sessionFactory");
        StandardServiceRegistryBuilder.destroy(sessionFactory.getSessionFactoryOptions().getServiceRegistry());
    }
}
