package hospital.visualization.insert;

import hospital.dao.DepartureDao;
import hospital.dao.DiagnosisDao;
import hospital.types.Diagnosis;

import javax.swing.*;
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
        name = new JFormattedTextField();

        insert = new JButton("Добавить");

        insert.addActionListener(
                e ->
                        this.diagnosisDao.insert(
                                Diagnosis.builder()
                                        .departureId(this.departureDao.findByName(departure.getText()))
                                        .diagnosisName(name.getText())
                                        .build()
                        )
        );

        add(departure);
        add(name);
        add(insert);
    }
}
