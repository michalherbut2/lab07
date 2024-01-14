package pl.mherbut.jp.lab07;

import javax.swing.*;
import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException {
        WorldGUI gui = new WorldGUI();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new WorldGUI();
//            }
//        });
    }
}
