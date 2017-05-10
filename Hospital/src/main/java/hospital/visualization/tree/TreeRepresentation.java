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
    private static final int HEIGHT = 600;

    private final DepartureDao departureDao;
    private final SessionFactory sessionFactory;
    private HospitalTree tree;
    private JScrollPane jScrollPane;

    public void setjTabbedPane(final JTabbedPane jTabbedPane) {
        jTabbedPane.addChangeListener(
                e -> {
                    final int index = jTabbedPane.getSelectedIndex();
                    if (index == 1) {
                        update();
                    }
                }
        );
    }

    public TreeRepresentation(
            final DepartureDao departureDao,
            final SessionFactory sessionFactory) {
        super(new BorderLayout());
        this.departureDao = departureDao;
        this.sessionFactory = sessionFactory;

        final JFrame frame = new JFrame("Hospital");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final List<Departure> departures = departureDao.findAllWithWards();

        tree = new HospitalTree(createTreeFromDepartures(departures));
        jScrollPane = new JScrollPane(tree);
        jScrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        add(jScrollPane, BorderLayout.CENTER);
    }

    public void update() {
        remove(jScrollPane);
        final List<Departure> departures = departureDao.findAllWithWards();
        tree = new HospitalTree(createTreeFromDepartures(departures));
        jScrollPane = new JScrollPane(tree);

        add(jScrollPane, BorderLayout.CENTER);

        updateUI();
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
}
