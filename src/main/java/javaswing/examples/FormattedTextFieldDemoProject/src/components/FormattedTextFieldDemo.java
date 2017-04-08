/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package javaswing.examples.FormattedTextFieldDemoProject.src.components;

import java.awt.*;

import javax.swing.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.text.*;

/**
 * FormattedTextFieldDemo.java requires no other files.
 *
 * It implements a mortgage calculator that uses four
 * JFormattedTextFields.
 */
public class FormattedTextFieldDemo extends JPanel
                                    implements PropertyChangeListener {
    //Values for the fields
    private double amount = 100000;
    private double rate = 7.5;  //7.5%
    private int numPeriods = 30;

    //Labels to identify the fields
    private final JLabel amountLabel;
    private final JLabel rateLabel;
    private final JLabel numPeriodsLabel;
    private final JLabel paymentLabel;

    //Strings for the labels
    private static final String amountString = "Loan Amount: ";
    private static final String rateString = "APR (%): ";
    private static final String numPeriodsString = "Years: ";
    private static final String paymentString = "Monthly Payment: ";

    //Fields for data entry
    private final JFormattedTextField amountField;
    private final JFormattedTextField rateField;
    private final JFormattedTextField numPeriodsField;
    private final JFormattedTextField paymentField;

    //Formats to format and parse numbers
    private NumberFormat amountFormat;
    private NumberFormat percentFormat;
    private NumberFormat paymentFormat;

    public FormattedTextFieldDemo() {
        super(new BorderLayout());
        setUpFormats();
        final double payment = computePayment(amount,
                                        rate,
                                        numPeriods);

        //Create the labels.
        amountLabel = new JLabel(amountString);
        rateLabel = new JLabel(rateString);
        numPeriodsLabel = new JLabel(numPeriodsString);
        paymentLabel = new JLabel(paymentString);

        //Create the text fields and set them up.
        amountField = new JFormattedTextField(amountFormat);
        amountField.setValue(new Double(amount));
        amountField.setColumns(10);
        amountField.addPropertyChangeListener("value", this);

        rateField = new JFormattedTextField(percentFormat);
        rateField.setValue(new Double(rate));
        rateField.setColumns(10);
        rateField.addPropertyChangeListener("value", this);

        numPeriodsField = new JFormattedTextField();
        numPeriodsField.setValue(new Integer(numPeriods));
        numPeriodsField.setColumns(10);
        numPeriodsField.addPropertyChangeListener("value", this);

        paymentField = new JFormattedTextField(paymentFormat);
        paymentField.setValue(new Double(payment));
        paymentField.setColumns(10);
        paymentField.setEditable(false);
        paymentField.setForeground(Color.red);

        //Tell accessibility tools about label/textfield pairs.
        amountLabel.setLabelFor(amountField);
        rateLabel.setLabelFor(rateField);
        numPeriodsLabel.setLabelFor(numPeriodsField);
        paymentLabel.setLabelFor(paymentField);

        //Lay out the labels in a panel.
        final JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(amountLabel);
        labelPane.add(rateLabel);
        labelPane.add(numPeriodsLabel);
        labelPane.add(paymentLabel);

        //Layout the text fields in a panel.
        final JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(amountField);
        fieldPane.add(rateField);
        fieldPane.add(numPeriodsField);
        fieldPane.add(paymentField);

        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
    }

    /** Called when a field's "value" property changes. */
    public void propertyChange(final PropertyChangeEvent e) {
        final Object source = e.getSource();
        if (source == amountField) {
            amount = ((Number)amountField.getValue()).doubleValue();
        } else if (source == rateField) {
            rate = ((Number)rateField.getValue()).doubleValue();
        } else if (source == numPeriodsField) {
            numPeriods = ((Number)numPeriodsField.getValue()).intValue();
        }

        final double payment = computePayment(amount, rate, numPeriods);
        paymentField.setValue(new Double(payment));
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        final JFrame frame = new JFrame("FormattedTextFieldDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new FormattedTextFieldDemo());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            //Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE);
            createAndShowGUI();
        });
    }

    //Compute the monthly payment based on the loan amount,
    //APR, and length of loan.
    double computePayment(final double loanAmt, final double rate, int numPeriods) {
        final double I;
        final double partial1;
        final double denominator;
        final double answer;

        numPeriods *= 12;        //get number of months
        if (rate > 0.01) {
            I = rate / 100.0 / 12.0;         //get monthly rate from annual
            partial1 = Math.pow((1 + I), (0.0 - numPeriods));
            denominator = (1 - partial1) / I;
        } else { //rate ~= 0
            denominator = numPeriods;
        }

        answer = (-1 * loanAmt) / denominator;
        return answer;
    }

    //Create and set up number formats. These objects also
    //parse numbers input by user.
    private void setUpFormats() {
        amountFormat = NumberFormat.getNumberInstance();

        percentFormat = NumberFormat.getNumberInstance();
        percentFormat.setMinimumFractionDigits(3);

        paymentFormat = NumberFormat.getCurrencyInstance();
    }
}
