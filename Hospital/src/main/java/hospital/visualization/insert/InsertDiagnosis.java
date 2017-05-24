package hospital.visualization.insert;

import hospital.dao.DepartureDao;
import hospital.dao.DiagnosisDao;
import hospital.types.Diagnosis;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Polytech
 * Created by igor on 11.05.17.
 */
public class InsertDiagnosis extends JPanel {
    private final DepartureDao departureDao;
    private final DiagnosisDao diagnosisDao;

    private final JFormattedTextField departure;
    private final JFormattedTextField name;
    private final JButton insert;

    public InsertDiagnosis(
            final DepartureDao departureDao,
            final DiagnosisDao diagnosisDao) {
        super(new GridLayout(3, 0));
        this.diagnosisDao = diagnosisDao;
        this.departureDao = departureDao;

        departure = new JFormattedTextField();
        departure.setBorder(new TitledBorder("Отделение"));
        name = new JFormattedTextField();
        name.setBorder(new TitledBorder("Диагноз"));

        insert = new JButton("Добавить");

        insert.addActionListener(
                e -> {
                    try {
                        this.diagnosisDao.insert(
                                Diagnosis.builder()
                                        .departureId(this.departureDao.findByName(departure.getText()))
                                        .diagnosisName(name.getText())
                                        .build()
                        );
                    } catch (final Exception exception) {
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

        add(departure);
        add(name);
        add(insert);
    }
}
