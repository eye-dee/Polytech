package hospital;

import hospital.dao.*;
import hospital.impl.*;
import hospital.init.Import;
import hospital.types.*;
import hospital.visualization.insert.InsertDeparture;
import hospital.visualization.insert.InsertDiagnosis;
import hospital.visualization.insert.InsertWard;
import hospital.visualization.insert.InsertWindow;
import hospital.visualization.list.WriteOutPeople;
import hospital.visualization.login.LoginWindow;
import hospital.visualization.tabs.MainWindow;
import hospital.visualization.tree.TreeRepresentation;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import java.util.Locale;

/**
 * Polytech
 * Created by igor on 07.04.17.
 */

@Configuration
@ComponentScan(basePackages = "hospital.*")
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
        configuration.addAnnotatedClass(User.class);

        return configuration.buildSessionFactory();
    }

    /*@Bean
    @Scope("prototype")
    Session session() {
        return sessionFactory().openSession();
    }*/

    @Bean
    PeopleDao peopleDao() {
        return new PeopleDaoImpl(sessionFactory(), diagnosisDao());
    }

    @Bean
    DepartureDao departureDao() {
        return new DepartureDaoImpl(sessionFactory(), wardDao(), diagnosisDao());
    }

    @Bean
    DiagnosisDao diagnosisDao() {
        return new DiagnosisDaoImpl(sessionFactory());
    }

    @Bean
    WardDao wardDao() {
        return new WardDaoImpl(sessionFactory(), peopleDao());
    }

    @Bean
    UserDao userDao() {
        return new UserDaoImpl(sessionFactory());
    }

    @Bean
    TreeRepresentation treeRepresentation() {
        return new TreeRepresentation(departureDao(), sessionFactory());
    }

    @Bean
    MainWindow mainWindow() {
        return new MainWindow(
                treeRepresentation(),
                loginWindow(),
                tabbedPane(),
                writeOutPeople());
    }

    @Bean
    LoginWindow loginWindow() {
        return new LoginWindow(userDao(), currentUser());
    }

    @Bean
    User currentUser() {
        return User.builder().build();
    }

    @Bean
    InsertWindow insertWindow() {
        return new InsertWindow(peopleDao(),wardDao(),diagnosisDao());
    }

    @Bean
    InsertDeparture insertDeparture() {
        return new InsertDeparture(departureDao());
    }

    @Bean
    InsertDiagnosis insertDiagnosis() {
        return new InsertDiagnosis(departureDao(),diagnosisDao());
    }

    @Bean
    InsertWard insertWard() {
        return new InsertWard(wardDao(),departureDao());
    }

    @Bean
    WriteOutPeople writeOutPeople() {
        return new WriteOutPeople(peopleDao(), departureDao(), wardDao());
    }

    @Bean
    JTabbedPane tabbedPane() {
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Логин", null, loginWindow(), null);
        tabbedPane.addTab("Дерево Больницы", null, treeRepresentation(), null);
        tabbedPane.addTab("Добавить больного", null, insertWindow(), null);
        tabbedPane.addTab("Добавить отделение", null, insertDeparture(), null);
        tabbedPane.addTab("Добавить палату",null, insertWard(), null);
        tabbedPane.addTab("Добавить диагноз", null, insertDiagnosis(),null);
        tabbedPane.addTab("Выписывание пациентов",null,writeOutPeople(),null);

        tabbedPane.setSelectedIndex(0);
        final String toolTip = "<html>Blue Wavy Line border art crew:<br>&nbsp;&nbsp;&nbsp;Bill Pauley<br>&nbsp;&nbsp;&nbsp;Cris St. Aubyn<br>&nbsp;&nbsp;&nbsp;Ben Wronsky<br>&nbsp;&nbsp;&nbsp;Nathan Walrath<br>&nbsp;&nbsp;&nbsp;Tommy Adams, special consultant</html>";
        tabbedPane.setToolTipTextAt(1, toolTip);

        final int size = tabbedPane.getComponents().length;
        for (int i = 1; i < size; ++i) {
            tabbedPane.setEnabledAt(i, false);
        }

        return tabbedPane;
    }

    @Bean
    Import anImport() {
        return new Import(peopleDao(),diagnosisDao(),departureDao(),wardDao());
    }
}
