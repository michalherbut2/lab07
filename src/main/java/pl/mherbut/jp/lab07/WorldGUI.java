//////package pl.mherbut.jp.lab06;
//////import javax.swing.*;
//////import java.awt.*;
//////import java.util.Objects;
//////
//////public class WorldGUI extends JFrame {
//////    private World world;
//////
//////    public WorldGUI(World world) {
//////        super("World Map");
//////        this.world = world;
//////        this.world.gui=this;
//////        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//////        setSize(400, 400);
//////        setLayout(new GridLayout(5, 5));
//////
//////        initMap();
//////        updateMap();
//////
//////        setVisible(true);
//////    }
//////
//////    private void initMap() {
//////        for (int i = 0; i < world.map.length; i++) {
//////            for (int j = 0; j < world.map[i].length; j++) {
//////                JPanel panel = new JPanel();
//////                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//////                add(panel);
//////            }
//////        }
//////    }
//////
//////    private void updateMap() {
//////        for (int i = 0; i < world.map.length; i++) {
//////            for (int j = 0; j < world.map[i].length; j++) {
//////                JPanel panel = (JPanel) getContentPane().getComponent(i * 5 + j);
//////                if (world.map[i][j].hasMachine()){
//////                    if (Objects.equals(world.map[i][j].getMachine().getRole(), "Seeder")) {
//////                        panel.setBackground(Color.GREEN); // You can customize the color
//////                    }else {
//////                        panel.setBackground(Color.YELLOW); // You can customize the color
//////                    }
//////                } else {
//////                    panel.setBackground(Color.WHITE);
//////                }
//////            }
//////        }
//////    }
//////
//////    public void refreshMap() {
//////        SwingUtilities.invokeLater(new Runnable() {
//////            @Override
//////            public void run() {
//////                updateMap();
//////            }
//////        });
//////    }
//////
//////    public static void main(String[] args) {
//////        World world = new World();
//////        WorldGUI worldGUI = new WorldGUI(world);
//////
//////        // Do something in your application to update the world and call refreshMap() accordingly
//////        // For example: world.register("localhost", 12345, "Seeder");
//////        // worldGUI.refreshMap();
//////    }
//////}
////package pl.mherbut.jp.lab06;
////
////import javax.swing.*;
////import java.awt.*;
////import java.util.Objects;
////
////public class WorldGUI extends JFrame {
////    private World world;
////
////    private Icon seederIcon;
////    private Icon harvesterIcon;
////    private Icon plantIcon;
////    private JLabel[][] machineLabels;
////    private JLabel[][] plantLabels;
////    public WorldGUI(World world) {
////        super("World Map");
////        this.world = world;
////        this.world.gui = this;
////        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        setSize(400, 400);
////        setLayout(new GridLayout(5, 5));
////
////        seederIcon = new ImageIcon(getClass().getResource("seeder.png"));
////        harvesterIcon = new ImageIcon(getClass().getResource("harvester.png"));
////        plantIcon = new ImageIcon(getClass().getResource("plant.png")); // Add an icon for plants
////
////        initMap();
////        updateMap();
////
////        setVisible(true);
////    }
////
//////    private void initMap() {
//////        for (int i = 0; i < world.map.length; i++) {
//////            for (int j = 0; j < world.map[i].length; j++) {
//////                JLabel label = new JLabel();
//////                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//////                add(label);
//////            }
//////        }
//////    }
////private void initMap() {
////    for (int i = 0; i < world.map.length; i++) {
////        for (int j = 0; j < world.map[i].length; j++) {
////            JPanel panel = new JPanel(new BorderLayout());
////            JLabel machineLabel = new JLabel();
////            JLabel plantLabel = new JLabel();
////
////            panel.add(machineLabel, BorderLayout.NORTH);
////            panel.add(plantLabel, BorderLayout.SOUTH);
////
////            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
////            add(panel);
//////
//////            machineLabels[i][j] = machineLabel;
//////            plantLabels[i][j] = plantLabel;
////        }
////    }
////}
//////    private void updateMap() {
//////        for (int i = 0; i < world.map.length; i++) {
//////            for (int j = 0; j < world.map[i].length; j++) {
//////                JLabel label = (JLabel) getContentPane().getComponent(i * 5 + j);
//////
//////                if (world.map[i][j].hasMachine()) {
//////                    if (Objects.equals(world.map[i][j].getMachine().getRole(), "Seeder")) {
//////                        label.setIcon(seederIcon);
//////                    } else {
//////                        label.setIcon(harvesterIcon);
//////                    }
//////                } else {
//////                    label.setIcon(null);
//////                }
//////            }
//////        }
//////    }
//////private void updateMap() {
//////    for (int i = 0; i < world.map.length; i++) {
//////        for (int j = 0; j < world.map[i].length; j++) {
//////            JPanel panel = (JPanel) getContentPane().getComponent(i * 5 + j);
//////            JLabel label = new JLabel();
//////            JLabel plantLabel = new JLabel();
//////            label.setIcon(plantIcon);
//////            if (world.map[i][j].hasMachine()) {
//////                if (Objects.equals(world.map[i][j].getMachine().getRole(), "Seeder")) {
//////                    label.setIcon(seederIcon);
//////                } else {
//////                    label.setIcon(harvesterIcon);
//////                }
//////            } else if (!world.map[i][j].getSeeds().isEmpty()) {
//////                label.setIcon(plantIcon);
//////            } else {
//////                label.setIcon(null);
//////            }
//////            panel.add(label,BorderLayout.NORTH);
//////            panel.add(plantLabel,BorderLayout.SOUTH);
//////        }
//////    }
//////}
////
////    private void updateMap() {
////        for (int i = 0; i < world.map.length; i++) {
////            for (int j = 0; j < world.map[i].length; j++) {
////                machineLabels[i][j].setIcon(null);
////                plantLabels[i][j].setIcon(null);
////                plantLabels[i][j].setText(null);
////
////                if (world.map[i][j].hasMachine()) {
////                    if (Objects.equals(world.map[i][j].getMachine().getRole(), "Seeder")) {
////                        machineLabels[i][j].setIcon(seederIcon);
////                    } else {
////                        machineLabels[i][j].setIcon(harvesterIcon);
////                    }
////                }
////
////                if (!world.map[i][j].getSeeds().isEmpty()) {
////                    // Display the number of plants using the appropriate image
////                    int numPlants = world.map[i][j].getSeeds().size();
////                    String plantImageFileName = "plant_" + numPlants + ".png";
////                    Icon plantIcon = new ImageIcon(getClass().getResource(plantImageFileName));
////                    plantLabels[i][j].setIcon(plantIcon);
////                    plantLabels[i][j].setText(null);
////                }
////            }
////        }
////    }
////
////
////
////    //    private void updateMap() {
//////        for (int i = 0; i < world.map.length; i++) {
//////            for (int j = 0; j < world.map[i].length; j++) {
//////                JPanel panel = (JPanel) getContentPane().getComponent(i * 5 + j);
//////                if (world.map[i][j].hasMachine()){
//////                    if (Objects.equals(world.map[i][j].getMachine().getRole(), "Seeder")) {
//////                        panel.setBackground(Color.GREEN); // You can customize the color
//////                    }else {
//////                        panel.setBackground(Color.YELLOW); // You can customize the color
//////                    }
//////
//////                }
//////                else {
//////                    panel.setBackground(Color.WHITE);
//////                }
//////            }
//////        }
//////    }
////    public void refreshMap() {
////        SwingUtilities.invokeLater(this::updateMap);
////    }
////
////    public static void main(String[] args) {
////        World world = new World();
////        WorldGUI worldGUI = new WorldGUI(world);
////
////        // Do something in your application to update the world and call refreshMap() accordingly
////        // For example: world.register("localhost", 12345, "Seeder");
////        // worldGUI.refreshMap();
////    }
////}
//package pl.mherbut.jp.lab06;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.Objects;
//
//public class WorldGUI extends JFrame {
//    private World world;
//
//    private Icon seederIcon;
//    private Icon harvesterIcon;
//
//    private JLabel[][] machineLabels;
//    private JLabel[][] plantLabels;
//
//    public WorldGUI(World world) {
//        super("World Map");
//        this.world = world;
//        this.world.gui = this;
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(400, 400);
//        setLayout(new GridLayout(5, 5));
//
////        seederIcon = new ImageIcon(getClass().getResource("seeder.png"));
////        harvesterIcon = new ImageIcon(getClass().getResource("harvester.png"));
//
//        seederIcon = resizeImageIcon(new ImageIcon(getClass().getResource("seeder.png")), 50, 50);
//        harvesterIcon = resizeImageIcon(new ImageIcon(getClass().getResource("harvester.png")), 50, 50);
//
//
//        machineLabels = new JLabel[world.map.length][world.map[0].length];
//        plantLabels = new JLabel[world.map.length][world.map[0].length];
//
//        initMap();
//        updateMap();
//
//        setVisible(true);
//    }
//
//    private void initMap() {
//        for (int i = 0; i < world.map.length; i++) {
//            for (int j = 0; j < world.map[i].length; j++) {
//                JPanel panel = new JPanel(new BorderLayout());
//                JLabel machineLabel = new JLabel();
//                JLabel plantLabel = new JLabel();
//
//                panel.add(machineLabel, BorderLayout.NORTH);
//                panel.add(plantLabel, BorderLayout.SOUTH);
//
//                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//                add(panel);
//
//                machineLabels[i][j] = machineLabel;
//                plantLabels[i][j] = plantLabel;
//            }
//        }
//    }
//
//    private void updateMap() {
//        for (int i = 0; i < world.map.length; i++) {
//            for (int j = 0; j < world.map[i].length; j++) {
//                machineLabels[i][j].setIcon(null);
//                plantLabels[i][j].setIcon(null);
//                plantLabels[i][j].setText(null);
//
//                if (world.map[i][j].hasMachine()) {
//                    if (Objects.equals(world.map[i][j].getMachine().getRole(), "Seeder")) {
//                        machineLabels[i][j].setIcon(seederIcon);
//                    } else {
//                        machineLabels[i][j].setIcon(harvesterIcon);
//                    }
//                }
//
//                if (!world.map[i][j].getSeeds().isEmpty()) {
//                    // Display the number of plants using the appropriate image
//                    int numPlants = world.map[i][j].getSeeds().size();
////                    String plantImageFileName = "plant_" + numPlants + ".png";
//                    String plantImageFileName;
//                    if  (numPlants==4)
//                        plantImageFileName = "wheat.png";
//                    else
//                        plantImageFileName = "plant.png";
//
//                    Icon plantIcon = resizeImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(plantImageFileName))),40,40);
//                    plantLabels[i][j].setIcon(plantIcon);
//                    plantLabels[i][j].setText(null);
//                }
//            }
//        }
//    }
//    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
//        Image img = icon.getImage();
//        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//        return new ImageIcon(resizedImg);
//    }
//    public void refreshMap() {
//        SwingUtilities.invokeLater(this::updateMap);
//    }
//
//    public static void main(String[] args) {
//        World world = new World();
//        WorldGUI worldGUI = new WorldGUI(world);
//
//        // Do something in your application to update the world and call refreshMap() accordingly
//        // For example: world.register("localhost", 12345, "Seeder");
//        // worldGUI.refreshMap();
//    }
//}
package pl.mherbut.jp.lab07;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.Objects;

public class WorldGUI extends JFrame {
    private final World world;
    private final ImageIcon seederIcon;
    private final ImageIcon harvesterIcon;

    private final JLabel[][] machineLabels;
    private final JPanel[][] plantPanels;

    public WorldGUI() throws RemoteException {
        super("World Map");
        world = new World();
        world.gui = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(5, 5));

        seederIcon = resizeImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("seeder.png"))), 50, 50);
        harvesterIcon = resizeImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("harvester.png"))), 50, 50);

        machineLabels = new JLabel[world.map.length][world.map[0].length];
        plantPanels = new JPanel[world.map.length][world.map[0].length];

        initMap();
        updateMap();

        setVisible(true);
    }

    public static void main(String[] args) throws RemoteException {
        new WorldGUI();
    }

    private void initMap() {
        for (int i = 0; i < world.map.length; i++) {
            for (int j = 0; j < world.map[i].length; j++) {
                JPanel panel = new JPanel(new BorderLayout());
                JLabel machineLabel = new JLabel();
                JPanel plantPanel = new JPanel();

                panel.add(machineLabel, BorderLayout.NORTH);
                panel.add(plantPanel, BorderLayout.SOUTH);

                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(panel);

                machineLabels[i][j] = machineLabel;
                plantPanels[i][j] = plantPanel;
            }
        }
    }

    private void updateMap() {
        for (int i = 0; i < world.map.length; i++) {
            for (int j = 0; j < world.map[i].length; j++) {
                machineLabels[i][j].setIcon(null);

                // Clear existing plant panels
                plantPanels[i][j].removeAll();

                if (world.map[i][j].hasMachine()) {
                    if (Objects.equals(world.map[i][j].getMachine().getRole(), "Seeder"))
                        machineLabels[i][j].setIcon(seederIcon);
                    else
                        machineLabels[i][j].setIcon(harvesterIcon);
                }

                if (!world.map[i][j].getSeeds().isEmpty()) {
                    for (int k = 0; k < world.map[i][j].getSeeds().size(); k++) {
                        // Display a plant label for each plant
                        String plantImage = "plant.png";
                        if (world.map[i][j].getSeeds().get(k) == 4)
                            plantImage = "wheat.png";
                        JLabel plantLabel = new JLabel(resizeImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(plantImage))), 20, 20));
                        plantPanels[i][j].add(plantLabel);
                    }
                }
            }
        }
    }

    public void refreshMap() {
        SwingUtilities.invokeLater(this::updateMap);
    }

    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
