package hospital.visualization.list;

import hospital.dao.PeopleDao;
import hospital.types.People;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created by igor on 11.05.17.
 */
public class WriteOutPeople extends JPanel
        implements ListSelectionListener {
    private final JList<String> list;
    private final DefaultListModel<String> listModel;
    private final List<People> peopleList = new ArrayList<>();

    private static final String fireString = "Выписать";
    private final JButton fireButton;

    private final PeopleDao peopleDao;

    public WriteOutPeople(final PeopleDao peopleDao) {
        super(new BorderLayout());
        this.peopleDao = peopleDao;

        listModel = new DefaultListModel<>();

        updateListModel();

        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        final JScrollPane listScrollPane = new JScrollPane(list);

        fireButton = new JButton(fireString);
        fireButton.setActionCommand(fireString);
        fireButton.addActionListener(new FireListener());

        final JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(fireButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    public void setjTabbedPane(final JTabbedPane jTabbedPane) {
        jTabbedPane.addChangeListener(
                e -> {
                    final int index = jTabbedPane.getSelectedIndex();
                    if (index == 6) {
                        updateListModel();
                    }
                }
        );
    }

    private void updateListModel() {
        listModel.clear();
        peopleList.clear();

        peopleDao.findAll().forEach(people -> {
            peopleList.add(people);
            listModel.addElement(people.toString());
        });
    }

    class FireListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            int index = list.getSelectedIndex();

            peopleDao.deleteById(peopleList.get(index).getPeopleId());

            listModel.remove(index);
            peopleList.remove(index);

            if (listModel.isEmpty()) {
                fireButton.setEnabled(false);

            } else {
                if (index == listModel.getSize()) {
                    --index;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    public void valueChanged(final ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (list.getSelectedIndex() == -1) {
                fireButton.setEnabled(false);

            } else {
                fireButton.setEnabled(true);
            }
        }
    }
}
