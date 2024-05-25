
public class MapSquare {
    private int row;
    private int col;
    private Square square;
    public MapSquare(int row, int col, Square square) {
        this.row = row;
        this.col = col;
        this.square = square;
    }
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Square getSquare() {
        return square;
    }
}
