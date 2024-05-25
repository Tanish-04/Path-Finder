import java.util.*;
public class ShortestPathFinder {
    public List<GraphNode> findShortestPath(Graph graph, GraphNode start, GraphNode finish) {
        Map<GraphNode, Integer> distances = new HashMap<>();
        Map<GraphNode, GraphNode> previousNodes = new HashMap<>();
        Queue<GraphNode> queue = new LinkedList<>();
        distances.put(start, 0);
        queue.add(start);
        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();
            if (current.equals(finish)) {
                return reconstructPath(previousNodes, finish);
            }
            for (GraphNode neighbor : graph.getNeighbors(current)) {
                int newDistance = distances.get(current) + 1;
                if (!distances.containsKey(neighbor) || newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previousNodes.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        return new ArrayList<>(); // No path found
    }
    private List<GraphNode> reconstructPath(Map<GraphNode, GraphNode> previousNodes, GraphNode finish) {
        List<GraphNode> path = new ArrayList<>();
        GraphNode current = finish;
        while (previousNodes.containsKey(current)) {
            path.add(current);
            current = previousNodes.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}
