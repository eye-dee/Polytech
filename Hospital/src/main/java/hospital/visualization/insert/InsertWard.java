package hospital.visualization.insert;

import hospital.dao.DepartureDao;
import hospital.dao.WardDao;
import hospital.types.Ward;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Polytech
 * Created by igor on 11.05.17.
 */
public class InsertWard extends JPanel {
    private final WardDao wardDao;
    private final DepartureDao departureDao;

    private final JFormattedTextField maxCount;
    private final JFormattedTextField departure;
    private final JFormattedTextField wardName;
    private final JButton insert;

    public InsertWard(
            final WardDao wardDao,
            final DepartureDao departureDao) {
        super(new GridLayout(4, 0));

        this.wardDao = wardDao;
        this.departureDao = departureDao;

        maxCount = new JFormattedTextField();
        maxCount.setBorder(new TitledBorder("максимальное количество"));
        departure = new JFormattedTextField();
        departure.setBorder(new TitledBorder("отделение"));
        wardName = new JFormattedTextField();
        wardName.setBorder(new TitledBorder("номер палаты"));

        insert = new JButton("Добавить");

        insert.addActionListener(
                e -> {
                    try {
                        this.wardDao.insert(
                                Ward.builder()
                                        .maxCount(Long.valueOf(maxCount.getText()))
                                        .departureId(this.departureDao.findByName(departure.getText()))
                                        .wardName(wardName.getText())
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

        add(maxCount);
        add(departure);
        add(wardName);
        add(insert);
    }
}
