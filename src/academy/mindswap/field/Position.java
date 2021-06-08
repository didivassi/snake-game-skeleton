package academy.mindswap.field;

public class Position {

    private int col;
    private int row;

    public Position(int col, int row) {
        this.col=col;
        this.row=row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object obj) {
       Position position=(Position)obj;
        return col == position.getCol() && row == position.getRow();
    }

    @Override
    public String toString() {
        return "Position{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
