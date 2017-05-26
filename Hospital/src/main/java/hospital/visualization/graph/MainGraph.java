package hospital.visualization.graph;

import hospital.dao.DepartureDao;
import hospital.dao.WardDao;
import hospital.types.Departure;
import hospital.types.Ward;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Polytech
 * Created on 27.05.17.
 */
public class MainGraph extends JPanel {
    private final JButton histogram;
    private final JButton people;
    private final WardDao wardDao;
    private final DepartureDao departureDao;

    public MainGraph(final WardDao wardDao, DepartureDao departureDao) {
        super(new GridLayout(1, 0));

        this.wardDao = wardDao;
        this.departureDao = departureDao;
        histogram = new JButton("Занятость палат");
        people = new JButton("Люди в отделениях");

        histogram.addActionListener(
                e -> update()
        );

        people.addActionListener(
                e ->  new PeopleGraph(
                        departureDao.findAllWithWards()
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        Departure::getDepartureId,
                                        Function.identity()
                                )
                        )
                )
        );

        add(histogram);
        add(people);
    }

    public void update() {
        final Map<Long, Ward> wardMap = wardDao.findAllWithPeople()
                .stream()
                .collect(
                        Collectors.toMap(
                                Ward::getWardId,
                                Function.identity()
                        )
                );

        EventQueue.invokeLater(() -> new Histogram(wardMap));
    }
}
