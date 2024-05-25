import java.util.*;
public class Graph {
    private Map<GraphNode, List<GraphNode>> adjacencyList;
    List<GraphNode> nodes;
    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.nodes = new ArrayList<>();
    }
    public void addEdge(GraphNode source, GraphNode destination) {
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
    }
    public List<GraphNode> getNeighbors(GraphNode node) {
        return adjacencyList.getOrDefault(node, new ArrayList<>());
    }
    public static void readGraph(Graph graph) {
        // Iterate over each node in the graph
        for (GraphNode node : graph.nodes) {
            int row = node.getRow();
            int col = node.getCol();
            Square square = node.getSquare();

            // Process the node as needed
            System.out.println("( " + row + ", " + col + ")" + square.getSymbol());
        }
    }
    public static Graph createGraph(List<MapSquare> map) {
        int numRows = 0;
        int numCols = 0;

        for (MapSquare square : map) {
            numRows = Math.max(numRows, square.getRow() + 1);
            numCols = Math.max(numCols, square.getCol() + 1);
        }

        Graph graph = new Graph();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                MapSquare currentSquare = map.get(i * numCols + j);
                GraphNode currentNode = new GraphNode(currentSquare.getRow(), currentSquare.getCol(), currentSquare.getSquare());
                graph.nodes.add(currentNode);
            }
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                MapSquare currentSquare = map.get(i * numCols + j);
                GraphNode currentNode = graph.nodes.get(i * numCols + j);
                if (currentSquare.getSquare() == Square.EMPTY || currentSquare.getSquare() == Square.START || currentSquare.getSquare() == Square.FINISH) {
                    for (int[] dir : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
                        int newRow = i + dir[0];
                        int newCol = j + dir[1];
                        if (isValidSquare(newRow, newCol, numRows, numCols)) {
                            GraphNode neighborNode = graph.nodes.get(newRow * numCols + newCol);
                            graph.addEdge(currentNode, neighborNode);
                        }
                    }
                }
            }
        }
        GraphNode startNode = findStartNode(map);
        GraphNode finishNode = findFinishNode(map);
        if (startNode != null && finishNode != null) {
            graph.addEdge(startNode, finishNode);
        }
        return graph;
    }
    private static boolean isValidSquare(int row, int col, int numRows, int numCols) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }
    static GraphNode findStartNode(List<MapSquare> map) {
        for (MapSquare square : map) {
            if (square.getSquare() == Square.START) {
                return new GraphNode(square.getRow(), square.getCol(),square.getSquare());
            }
        }
        return null;
    }
    static GraphNode findFinishNode(List<MapSquare> map) {
        for (MapSquare square : map) {
            if (square.getSquare() == Square.FINISH) {
                return new GraphNode(square.getRow(), square.getCol(),square.getSquare());
            }
        }
        return null;
    }
    public static void printNeighbors(Graph graph) {
        for (GraphNode node : graph.nodes) {
            int row = node.getRow();
            int col = node.getCol();
            Square square = node.getSquare();
            List<GraphNode> neighbors = graph.getNeighbors(node);
            System.out.println("Neighbors of Node at row " + row + ", column " + col + " with square type " + square + ":");
            for (GraphNode neighbor : neighbors) {
                int neighborRow = neighbor.getRow();
                int neighborCol = neighbor.getCol();
                Square neighborSquare = neighbor.getSquare();
                System.out.println("  Neighbor at row " + neighborRow + ", column " + neighborCol + " with square type " + neighborSquare);
            }
        }
    }

}
