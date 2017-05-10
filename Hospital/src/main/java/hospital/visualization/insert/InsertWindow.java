package hospital.visualization.insert;

import hospital.dao.DiagnosisDao;
import hospital.dao.PeopleDao;
import hospital.dao.WardDao;
import hospital.types.People;

import javax.swing.*;
import java.awt.*;

/**
 * Polytech
 * Created by igor on 06.05.17.
 */
public class InsertWindow extends JPanel {
    private final PeopleDao peopleDao;
    private final WardDao wardDao;
    private final DiagnosisDao diagnosisDao;

    private final JFormattedTextField firstName;
    private final JFormattedTextField lastName;
    private final JFormattedTextField fatherName;
    private final JFormattedTextField diagnosis;
    private final JFormattedTextField ward;
    private final JButton insert;

    public InsertWindow(
            final PeopleDao peopleDao,
            final WardDao wardDao,
            final DiagnosisDao diagnosisDao
    ) {
        super(new GridLayout(6, 0));

        this.peopleDao = peopleDao;
        this.wardDao = wardDao;
        this.diagnosisDao = diagnosisDao;

        firstName = new JFormattedTextField();
        lastName = new JFormattedTextField();
        fatherName = new JFormattedTextField();
        diagnosis = new JFormattedTextField();
        ward = new JFormattedTextField();

        insert = new JButton("Добавить");

        insert.addActionListener(
                e ->
                        peopleDao.insert(
                                People.builder()
                                        .firstName(firstName.getText())
                                        .lastName(lastName.getText())
                                        .fatherName(fatherName.getText())
                                        .diagnosisId(diagnosisDao.findByName(diagnosis.getText()))
                                        .wardId(wardDao.findByName(ward.getText()))
                                        .build()
                        )
        );

        add(firstName);
        add(lastName);
        add(fatherName);
        add(diagnosis);
        add(ward);
        add(insert);
    }
}
