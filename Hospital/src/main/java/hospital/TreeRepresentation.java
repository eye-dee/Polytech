package hospital;

import hospital.dao.DepartureDao;
import hospital.types.Departure;
import hospital.types.People;
import hospital.types.Ward;
import hospital.visualization.HospitalTree;
import hospital.visualization.TreeNode;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public class TreeRepresentation extends JPanel {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 800;

    public static void main() {
        javax.swing.SwingUtilities.invokeLater(TreeRepresentation::createAndShowGUI);
    }

    private static TreeNode createTreeFromDepartures(final List<Departure> departures) {
        final TreeNode root = new TreeNode("Hospital");

        for (final Departure departure : departures) {
            final TreeNode curDepartureNode = new TreeNode(departure.getDepartureName());
            root.add(curDepartureNode);

            final List<Ward> wards = departure.getWards();
            for (final Ward ward : wards) {
                final TreeNode currentWardNode = new TreeNode(ward.getWardName());
                curDepartureNode.add(currentWardNode);

                final List<People> peoples = ward.getPeoples();
                for (final People people : peoples) {
                    final TreeNode currentPeopleNode = new TreeNode(people.toString());
                    currentWardNode.add(currentPeopleNode);
                }
            }
        }

        return root;
    }

    private final HospitalTree tree;

    private TreeRepresentation() {
        super(new BorderLayout());

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        final DepartureDao departureDao = applicationContext.getBean("departureDao", DepartureDao.class);

        final List<Departure> departures = departureDao.findAllWithWards();

        final SessionFactory sessionFactory = (SessionFactory)applicationContext.getBean("sessionFactory");
        StandardServiceRegistryBuilder.destroy(sessionFactory.getSessionFactoryOptions().getServiceRegistry());

        tree = new HospitalTree(createTreeFromDepartures(departures));
        final JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        add(scrollPane, BorderLayout.CENTER);
    }

    private static void createAndShowGUI() {
        final JFrame frame = new JFrame("Hospital");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final TreeRepresentation newContentPane = new TreeRepresentation();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }
}
