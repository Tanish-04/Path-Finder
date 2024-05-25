public class GraphNode {
    private int row;
    private int col;
    private Square square;

    public GraphNode(int row, int col, Square square) {
        this.row = row;
        this.col = col;
        this.square = square;
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

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }
// Getters and setters for row, col, and square
}