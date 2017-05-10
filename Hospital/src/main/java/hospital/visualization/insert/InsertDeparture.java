package hospital.visualization.insert;

import hospital.dao.DepartureDao;
import hospital.types.Departure;

import javax.swing.*;
import java.awt.*;

/**
 * Polytech
 * Created by igor on 11.05.17.
 */
public class InsertDeparture extends JPanel {
    private final DepartureDao departureDao;

    private final JFormattedTextField name;
    private final JButton insert;

    public InsertDeparture(
            final DepartureDao departureDao) {
        super(new GridLayout(2, 0));

        this.departureDao = departureDao;

        name = new JFormattedTextField();

        insert = new JButton("Добавить");

        insert.addActionListener(
                e ->
                        departureDao.insert(
                                Departure.builder()
                                        .departureName(name.getText())
                                        .build()
                        )
        );

        add(name);
        add(insert);
    }
}
