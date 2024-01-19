package pl.mherbut.jp.lab07;

import javax.swing.*;
import java.awt.*;

public class PReceiver extends JPanel {

    private final JTextField messageField;

    /**
     * Create the panel.
     */
    public PReceiver() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Using FlowLayout with some spacing

        JLabel lblMessage = new JLabel("Odpowedź świata:");

        messageField = new JTextField(18);
        messageField.setEditable(false);
        messageField.setBackground(Color.WHITE);

        JPanel hostPanel = new JPanel(new GridLayout(3, 2, 5, 5)); // Using GridLayout for labels and text fields

        hostPanel.add(lblMessage);
        hostPanel.add(messageField);
        add(hostPanel);
    }


    public void messageReceived(String theLine) {
        messageField.setText(theLine);
    }

}
