// Main.java
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {

            List<MapSquare> map = MapParser.parseMap("./src/input.txt");

            Graph graph = Graph.createGraph(map);
            //Graph.readGraph(graph);// helper method
            GraphNode start = findStartNode(graph);
            GraphNode finish = findFinishNode(graph);
            assert start != null;
            assert finish != null;
            System.out.println(start.getRow()+" "+start.getCol() +" "+finish.getRow()+" "+ finish.getRow());
            ShortestPathFinder pathFinder = new ShortestPathFinder();
            List<GraphNode> shortestPath = pathFinder.findShortestPath(graph, start, finish);
            if (!shortestPath.isEmpty()) {
                System.out.println("Starting from (" + start.getRow() + ", " + start.getCol() + ")");
                for (int i = 0; i < shortestPath.size() - 1; i++) {
                    GraphNode current = shortestPath.get(i);
                    GraphNode next = shortestPath.get(i + 1);
                    int rowDiff = next.getRow() - current.getRow();
                    int colDiff = next.getCol() - current.getCol();
                    String direction;
                    if (rowDiff == 1) {
                        direction = "Down";
                    } else if (rowDiff == -1) {
                        direction = "Up";
                    } else if (colDiff == 1) {
                        direction = "Right";
                    } else {
                        direction = "Left";
                    }
                    System.out.println((i + 1) + ". Move " + direction + " to (" + next.getRow() + ", " + next.getCol() + ")");
                }
                System.out.println(shortestPath.size()+". Done");
            } else {
                System.out.println("No path found.");
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }
    static GraphNode findStartNode(Graph graph) {
        for (GraphNode node : graph.nodes) {
            if (node.getSquare() == Square.START) {
                return node;
            }
        }
        return null;
    }
    static GraphNode findFinishNode(Graph graph) {
        for (GraphNode node : graph.nodes) {
            if (node.getSquare() == Square.FINISH) {
                return node;
            }
        }
        return null;
    }
}
