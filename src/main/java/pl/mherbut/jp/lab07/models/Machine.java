package pl.mherbut.jp.lab07.models;

import pl.edu.pwr.tkubik.jp.farm.api.ICallback;
import pl.edu.pwr.tkubik.jp.farm.api.Role;

public class Machine {
    private final int id;
    private final ICallback ic;
    private final Role role;
    private int row;
    private int col;
    private int direction = 1;

    public Machine(int id, int row, int col, ICallback ic, Role role) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.ic = ic;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Role getRole() {
        return role;
    }

    public int getDirection() {
        return direction;
    }

    public void changeDirection() {
        this.direction = -direction;
    }

    public ICallback getIc() {
        return ic;
    }
}
