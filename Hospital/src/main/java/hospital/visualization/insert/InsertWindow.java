package hospital.visualization.insert;

import hospital.dao.DiagnosisDao;
import hospital.dao.PeopleDao;
import hospital.dao.WardDao;
import hospital.types.People;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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
        firstName.setBorder(new TitledBorder("Имя"));
        lastName = new JFormattedTextField();
        lastName.setBorder(new TitledBorder("Фамилия"));
        fatherName = new JFormattedTextField();
        fatherName.setBorder(new TitledBorder("Отчество"));
        diagnosis = new JFormattedTextField();
        diagnosis.setBorder(new TitledBorder("Диагноз"));
        ward = new JFormattedTextField();
        ward.setBorder(new TitledBorder("Палата"));

        insert = new JButton("Добавить");

        insert.addActionListener(
                e -> {
                    try {
                        peopleDao.insert(
                                People.builder()
                                        .firstName(firstName.getText())
                                        .lastName(lastName.getText())
                                        .fatherName(fatherName.getText())
                                        .diagnosisId(diagnosisDao.findByName(diagnosis.getText()))
                                        .wardId(wardDao.findByName(ward.getText()))
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

        add(firstName);
        add(lastName);
        add(fatherName);
        add(diagnosis);
        add(ward);
        add(insert);
    }
}
