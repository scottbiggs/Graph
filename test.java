
import java.util.List;

/** Just a tester for the Graph class */
class test {

	public static void main(String args[]) {

		Graph test = new Graph(true);
		fillGraph(test);

		System.out.println(test.toString());

		// printAdjacent(test, 2);
		printAllAdjacencies(test);

	}

	/** just fills the graph with a bunch of nodes and edges */
	private static void fillGraph(Graph graph) {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addNode(6);
		graph.addNode(7);

		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(5, 6);
		graph.addEdge(5, 7);
	}

	private static void printAllAdjacencies(Graph graph) {
		List<Integer> nodeList = graph.getAllNodes();
		for (int id : nodeList) {
			printAdjacent(graph, id);
		}
	}

	private static void printAdjacent(Graph graph, int nodeId) {
		System.out.print("Adjacent to " + nodeId + " is:");
		List<Integer> adjList = graph.getAllAdjacentTo(nodeId);
		for (int i = 0; i < adjList.size(); i++) {
			System.out.print(" " + adjList.get(i));
		}
		System.out.println();

	}

}
