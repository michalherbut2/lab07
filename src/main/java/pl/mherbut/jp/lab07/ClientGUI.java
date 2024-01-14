package pl.mherbut.jp.lab07;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClientGUI extends JFrame {
    public String serverPort = "2000";
    public String serverHost = "localhost";
    private Client client;
    private final JButton registerButton;
    private final JButton unregisterButton;
    private final JButton moveButton;
    private final JButton seedButton;
    private final JButton harvestButton;
    private final JComboBox<String> roleComboBox;
    private final PReceiver receiver;
    private final JTextField serverHostField;
    private final JTextField serverPortField;

    public ClientGUI() {
        super("Symulator Farmy 2024");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(508, 521);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        serverHostField = new JTextField(serverHost);
        serverPortField = new JTextField(serverPort);

        String[] roles = {"Seeder", "Harvester"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setSelectedItem("Seeder");

        registerButton = new JButton("Zarejestruj się");
        unregisterButton = new JButton("Wyrejestruj się");
        moveButton = new JButton("Rusz się");
        seedButton = new JButton("Zasiej");
        harvestButton = new JButton("Skoś");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));
        buttonPanel.add(registerButton);
        buttonPanel.add(unregisterButton);
        buttonPanel.add(moveButton);
        buttonPanel.add(seedButton);
        buttonPanel.add(harvestButton);

        buttonPanel.setBounds(35, 275, 423, 106);
        contentPane.add(buttonPanel);

        registerButton.addActionListener(createButtonListener("register"));
        unregisterButton.addActionListener(createButtonListener("unregister"));
        moveButton.addActionListener(createButtonListener("move"));
        seedButton.addActionListener(createButtonListener("seed"));
        harvestButton.addActionListener(createButtonListener("harvest"));

        receiver = new PReceiver();
        receiver.setBorder(new LineBorder(new Color(0, 0, 0)));
        receiver.setBounds(35, 13, 423, 106);
        contentPane.add(receiver);

        JPanel secondPanel = new JPanel((new FlowLayout(FlowLayout.LEFT, 10, 10))); // Using FlowLayout with some spacing
        JPanel box = new JPanel(new GridLayout(3, 2, 10, 10));
        secondPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        secondPanel.setBounds(35, 132, 423, 129);

        box.add(new JLabel("Host serwera:"));
        box.add(serverHostField);
        box.add(new JLabel("Port serwera:"));
        box.add(serverPortField);
        box.add(new JLabel("Rola:"));
        box.add(roleComboBox);

        secondPanel.add(box);

        contentPane.add(secondPanel);

        setButtonsEnabled(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientGUI::new);
    }

    private ActionListener createButtonListener(String action) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    handleButtonAction(action);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    private void handleButtonAction(String action) throws IOException {
        switch (action) {
            case "register":
                try {
                    serverHost = serverHostField.getText();
                    serverPort = serverPortField.getText();
                    String role = (String) roleComboBox.getSelectedItem();

//                    myHost = receiver.getHost();
//                    myPort = receiver.getPort();

                    client = new Client(this, role, serverHost, serverPort);
                    client.register();
//                    client.register(myHost, myPort, role);



                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Błąd połączenia. Spróbuj zamienić PORT");
                } catch (NotBoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "unregister":
                client.unregister();
                break;
            case "move":
                client.move();
                break;
            case "seed":
                client.seed();
                break;
            case "harvest":
                String inputData = JOptionPane.showInputDialog("Enter data (comma-separated indices):");
                List<Integer> data = Arrays.stream(inputData.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                client.harvest(data);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(null, "Error harvesting.");
//                }
                break;
        }
    }

    void setButtonsEnabled(boolean enabled) {
        boolean isSeeder = Objects.equals(roleComboBox.getSelectedItem(), "Seeder");
        registerButton.setVisible(!enabled);
        unregisterButton.setVisible(enabled);
        moveButton.setVisible(enabled);
        seedButton.setVisible(enabled && isSeeder);
        harvestButton.setVisible(enabled && !isSeeder);

        roleComboBox.setEnabled(!enabled);
    }

    public void showResponse(String message) {
        receiver.messageReceived(message);
    }
}

