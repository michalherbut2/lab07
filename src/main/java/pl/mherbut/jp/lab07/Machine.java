package pl.mherbut.jp.lab07;

public class Machine {
    private final int id;
    private int row;
    private int col;
    private ICallback ic;
    private final String role;
    private int direction=1;

    public Machine(int id, int row, int col, ICallback ic, String role) {
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

    public String getRole() {
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
