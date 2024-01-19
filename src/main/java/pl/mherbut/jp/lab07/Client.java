package pl.mherbut.jp.lab07;

import pl.edu.pwr.tkubik.jp.farm.api.Action;
import pl.edu.pwr.tkubik.jp.farm.api.ICallback;
import pl.edu.pwr.tkubik.jp.farm.api.IWorld;
import pl.edu.pwr.tkubik.jp.farm.api.Role;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Client extends UnicastRemoteObject implements ICallback, Serializable {

    private final IWorld iw;
    private final Role role;
    private final ClientGUI gui;
    private int id;

    public Client(ClientGUI gui, Role role, String serverHost, String serverPort) throws NotBoundException, RemoteException {
//    public Client(ClientGUI gui, String role, String severHost, String serverPort) throws NotBoundException, RemoteException {
        Registry r2 = LocateRegistry.getRegistry(serverHost, Integer.parseInt(serverPort));
        iw = (IWorld) r2.lookup("World");
        this.role = role;
        this.gui = gui;
    }


    public void register() throws IOException {
        id = iw.register(this, role);
        if (id != 0) {
            gui.setButtonsEnabled(true);
            gui.showResponse("Dostełem id:" + id);
        } else
            gui.showResponse("Nie udało sięzarejestrować!");
    }

    public void unregister() throws IOException {
        boolean isUnregistered = iw.unregister(id);
        if (isUnregistered) {
            gui.showResponse("Poprawnie się wyrejestrowałem id:" + id);
            gui.setButtonsEnabled(false);
        } else
            gui.showResponse("Nie udało się Poprawnie wyrejestrować id:" + id);
    }

    public void move() throws IOException {
        iw.move(id);
    }

    public void seed() throws IOException {
        iw.seed(id);
    }

    public void harvest(List<Integer> data) throws IOException {
        iw.harvest(id, data);
    }

    @Override
    public void response(Action action, List<Integer> data) throws RemoteException {
        String message = "nic";
        switch (action) {
            case MOVE:
                if (data.isEmpty())
                    message = "Wjechałeś na puste pole: ";
                else
                    message = "Rośliny o wieku: " + data;
                break;

            case SEED:
                if (data.get(0) > 0)
                    message = "Udało się zasiać";
                else
                    message = "Nie udało się zasiać";
                break;

            case HARVEST:
                if (data.get(0) > 0)
                    message = "Udało się skosić";
                else
                    message = "Nie udało się skosić";
                break;

            default:
                System.out.println("dostałem nieznane polecenie: " + action);
                break;
        }
        System.out.println(message);
        gui.showResponse(message);
    }
}
