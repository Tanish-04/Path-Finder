
public enum Square {
    EMPTY('.'),
    ROCK('0'),
    START('S'),
    FINISH('F');
    private final char symbol;
    Square(char symbol) {
        this.symbol = symbol;
    }
    public char getSymbol() {
        return symbol;
    }
}