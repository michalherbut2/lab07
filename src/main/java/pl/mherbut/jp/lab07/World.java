package pl.mherbut.jp.lab07;

import pl.edu.pwr.tkubik.jp.farm.api.*;
import pl.edu.pwr.tkubik.jp.farm.api.Action;
import pl.mherbut.jp.lab07.models.Field;
import pl.mherbut.jp.lab07.models.Machine;

import javax.swing.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

class World extends UnicastRemoteObject implements IWorld, Serializable {

    private final int worldSize = 5;
    private final Machine[] machines = new Machine[worldSize * 2];
    public WorldGUI gui;
    Field[][] map = new Field[worldSize][worldSize];
    List<Integer> errorData = new ArrayList<>(List.of(0));
    List<Integer> successData = new ArrayList<>(List.of(1));
    private int seederCounter = 0;
    private int harvesterCounter = 0;

    public World() throws RemoteException {
        for (int i = 0; i < worldSize; i++)
            for (int j = 0; j < worldSize; j++)
                map[i][j] = new Field();
        new Timer(3000, e -> {
            increasePlantsAge();
            gui.refreshMap();
        }).start();
        Registry r = LocateRegistry.createRegistry(2000);
        r.rebind("World", this);
    }

    @Override
    public synchronized int register(ICallback ic, Role role) {
        int id;
        try {
            if (role == Role.SEEDER)
                if (seederCounter + 1 > worldSize)
                    throw new Exception("Brak miejsca");
                else
                    seederCounter++;
            else if (harvesterCounter + 1 > worldSize)
                throw new Exception("Brak miejsca");
            else
                harvesterCounter++;
            id = generateId();
            int index = id - 1;
            int col = index;
            int row = index;
            Machine machine = new Machine(id, row, col, ic, role);
            machines[index] = machine;
            map[row][col].setMachine(machine);
            List<Integer> data = new ArrayList<>();
            data.add(id);
            gui.refreshMap();
            return id;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public synchronized boolean unregister(int id) {
        int index = id - 1;
        Role role = machines[index].getRole();
        if (role == Role.SEEDER)
            seederCounter--;
        else
            harvesterCounter--;
        int row = machines[index].getRow();
        int col = machines[index].getCol();
        machines[index] = null;
        map[row][col].removeMachine();
        gui.refreshMap();
        return true;
    }

    @Override
    public synchronized void move(int id) throws RemoteException {
        // Implement movement logic
        int index = id - 1;
        Machine machine = machines[index];
        ICallback ic = machine.getIc();
        int row = machine.getRow();
        int col = machine.getCol();
        int newRow = row;
        int newCol = col;
        int direction = machine.getDirection();
        Role role = machine.getRole();

        if (role == Role.SEEDER) {
            if (col + direction > 4 || col + direction < 0) {
                machine.changeDirection();
                direction = machine.getDirection();
            }
            newCol += direction;
        } else {
            if (row + direction > 4 || row + direction < 0) {
                machine.changeDirection();
                direction = machine.getDirection();
            }
            newRow += direction;
        }
        if (!map[newRow][newCol].hasMachine()) {
            machine.setRow(newRow);
            machine.setCol(newCol);
            try {
                map[newRow][newCol].setMachine(machine);
                map[row][col].removeMachine();

                gui.refreshMap();
                List<Integer> data = map[newRow][newCol].getSeeds();
                ic.response(Action.MOVE, data);
            } catch (Exception e) {
                ic.response(Action.MOVE, errorData);
            }
        } else {
            ic.response(Action.MOVE, errorData);
        }
    }

    @Override
    public synchronized void seed(int id) throws RemoteException {
        // Implement seeding logic
        int index = id - 1;
        Machine machine = machines[index];
        ICallback ic = machine.getIc();
        int row = machine.getRow();
        int col = machine.getCol();
        try {
            map[row][col].plantSeed();
            gui.refreshMap();
            ic.response(Action.SEED, successData);
        } catch (Exception e) {
            ic.response(Action.SEED, errorData);
        }
    }

    @Override
    public synchronized void harvest(int id, List<Integer> data) throws RemoteException {
        // Implement harvesting logic
        int index = id - 1;
        Machine machine = machines[index];
        ICallback ic = machine.getIc();
        int row = machine.getRow();
        int col = machine.getCol();
        List<Integer> seeds = map[row][col].getSeeds();
        try {
            // Check if data contains only valid indices
            int maxIndex = seeds.size() - 1;
            for (int seedIndex : data)
                if (seedIndex < 0 || seedIndex > maxIndex || seeds.get(seedIndex) != 4)
                    throw new IllegalArgumentException("Invalid seed index in data: " + seedIndex);

            // Check for duplicates in data
            if (hasDuplicates(data))
                throw new IllegalArgumentException("Duplicate indices in data: " + data);
            map[row][col].harvest(data);
            gui.refreshMap();
            ic.response(Action.HARVEST, successData);
        } catch (Exception e) {
            ic.response(Action.HARVEST, errorData);
        }
    }

    private boolean hasDuplicates(List<Integer> list) {
        HashSet<Integer> set = new HashSet<>();
        for (int element : list)
            if (!set.add(element))
                return true; // Duplicate found
        return false; // No duplicate
    }

    private int generateId() throws Exception {
        for (int i = 1; i <= worldSize; i++)
            if (machines[i - 1] == null)
                return i;
        throw new Exception("No space");
    }

    private void increasePlantsAge() {
        for (int i = 0; i < worldSize; i++)
            for (int j = 0; j < worldSize; j++)
                map[i][j].increasePlantAge();
    }
}
