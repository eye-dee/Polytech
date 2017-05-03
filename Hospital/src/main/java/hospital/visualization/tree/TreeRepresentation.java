package hospital.visualization.tree;

import hospital.dao.DepartureDao;
import hospital.types.Departure;
import hospital.types.People;
import hospital.types.Ward;
import org.hibernate.SessionFactory;

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

    private final DepartureDao departureDao;
    private final SessionFactory sessionFactory;
    private final HospitalTree tree;

    public TreeRepresentation(
            final DepartureDao departureDao,
            final SessionFactory sessionFactory) {
        super(new BorderLayout());
        this.departureDao = departureDao;
        this.sessionFactory = sessionFactory;

        final JFrame frame = new JFrame("Hospital");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final List<Departure> departures = departureDao.findAllWithWards();
        //StandardServiceRegistryBuilder.destroy(sessionFactory.getSessionFactoryOptions().getServiceRegistry());

        tree = new HospitalTree(createTreeFromDepartures(departures));
        final JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        add(scrollPane, BorderLayout.CENTER);

        /*this.setOpaque(true);
        frame.setContentPane(this);

        frame.pack();
        frame.setVisible(true);*/
    }

    /*public static void main() {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI);
    }*/

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
}
