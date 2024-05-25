import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class MapParser {
    public static List<MapSquare> parseMap(String filePath) throws IOException {
        List<MapSquare> map = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    char symbol = line.charAt(col);
                    if (symbol == '.') {
                        map.add(new MapSquare(row, col, Square.EMPTY));
                    } else if (symbol == '0'){

                            map.add(new MapSquare(row, col, Square.ROCK));
                    }
                    else if (symbol == 'F'){

                        map.add(new MapSquare(row, col, Square.FINISH));
                    }
                    else if (symbol == 'S'){

                        map.add(new MapSquare(row, col, Square.START));
                    }
                }
                row++;
            }

        }
        return map;
    }
}
