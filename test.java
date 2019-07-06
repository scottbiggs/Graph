
import java.util.List;

/** Just a tester for the Graph class */
class test {

	//---------------------------------
	// data
	//---------------------------------

	private static Graph<MyNode> mGraph;

	//---------------------------------
	//	methods
	//---------------------------------

	public static void main(String args[]) {

		mGraph = new Graph<>(true);
		fillGraph();

		System.out.println(mGraph.toString());


		System.out.println("connected? " + mGraph.isConnected());
	}

	/** just fills the graph with a bunch of nodes and edges */
	private static void fillGraph() {
		mGraph.addNode(0, new test().new MyNode(50));
		mGraph.addNode(1, new test().new MyNode(100));
		mGraph.addNode(2, new test().new MyNode(80));
		mGraph.addNode(3, new test().new MyNode(200));
		mGraph.addNode(4, new test().new MyNode(75));
		mGraph.addNode(5, new test().new MyNode(50));
		mGraph.addNode(6, new test().new MyNode(20));
		mGraph.addNode(7, new test().new MyNode(5));

		mGraph.addEdge(1, 2);
		mGraph.addEdge(1, 3);
		mGraph.addEdge(2, 3);
		mGraph.addEdge(3, 4);
		mGraph.addEdge(4, 5);
		mGraph.addEdge(5, 6);
//		mGraph.addEdge(5, 7);	// commenting this out makes it unconnected
		mGraph.addEdge(7, 0);
	}

	private static void printAllAdjacencies() {
		List<Integer> nodeIdList = mGraph.getAllNodeIds();
		for (int id : nodeIdList) {
			printAdjacent(id);
		}
	}

	private static void printAdjacent(int nodeId) {
		System.out.print("Adjacent to " + nodeId + " is:");
		List<Integer> adjList = mGraph.getAllAdjacentTo(nodeId);
		for (int i = 0; i < adjList.size(); i++) {
			System.out.print(" " + adjList.get(i));
		}
		System.out.println();

	}


	class MyNode {
		public int dollars;

		MyNode(int _dollars) {
			dollars = _dollars;
		}

		@Override
		public String toString() {
			return "$" + dollars;
		}
	}

}
