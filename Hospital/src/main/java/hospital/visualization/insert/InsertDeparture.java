package hospital.visualization.insert;

import hospital.dao.DepartureDao;
import hospital.types.Departure;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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
        name.setBorder(new TitledBorder("Название"));

        insert = new JButton("Добавить");

        insert.addActionListener(
                e -> {
                    try {
                        departureDao.insert(
                                Departure.builder()
                                        .departureName(name.getText())
                                        .build()
                        );
                    }  catch (final Exception exception) {
                        JOptionPane.showMessageDialog(this,
                                "Данные введены с ошибкой",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);

                        return;
                    }

                    JOptionPane.showMessageDialog(this,
                            "Успешно добавлено",
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                }
        );

        add(name);
        add(insert);
    }
}
