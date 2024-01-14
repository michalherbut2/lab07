package pl.mherbut.jp.lab07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field {
    private final int maxSeeds = 4;
    private final List<Integer> seeds = new ArrayList<>();
    private Machine machine = null;

    public int getMaxSeeds() {
        return maxSeeds;
    }

    public List<Integer> getSeeds() {
        return seeds;
    }

    public void plantSeed() throws Exception {
        if (seeds.size() < maxSeeds)
            seeds.add(1);
        else
            throw new Exception("Full");
    }

    public void harvest(List<Integer> data) throws Exception {
        System.out.println("HARVESTING" + data + seeds);
        // Sort indices in reverse order to avoid index shifting issues
        data.sort(Collections.reverseOrder());

        for (int index : data)
            if (index >= 0 && index < seeds.size())
                seeds.remove(index);
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) throws Exception {
        if (this.machine == null)
            this.machine = machine;
        else
            throw new Exception("Zajęte");
    }

    public boolean hasMachine() {
        return machine != null;
    }

    public void removeMachine() {
        this.machine = null;
    }

    public void increasePlantAge() {
        for (int i = 0; i < seeds.size(); i++) {
            int age = seeds.get(i);
            int maxAge = 4;
            if (age < maxAge) // maxAge to maksymalny wiek rośliny, po przekroczeniu którego roślina zostanie usunięta
                seeds.set(i, age + 1);
        }
    }

}
