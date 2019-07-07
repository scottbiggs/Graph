
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

		// Testing Minimal Graphs.
		Graph<MyNode> directed = new Graph<>(true);
		Graph<MyNode> undirected = new Graph<>(false);

		System.out.println("empty directed graph connected? " + directed.isConnected());
		System.out.println("empty undirected graph connected? " + undirected.isConnected());

		try {
			directed.addNode(0, new test().new MyNode(50));
			undirected.addNode(0, new test().new MyNode(500));
			directed.addNode(2, new test().new MyNode(70));
			undirected.addNode(2, new test().new MyNode(700));
			directed.addNode(4, new test().new MyNode(90));
			undirected.addNode(4, new test().new MyNode(900));
			undirected.addNode(4, new test().new MyNode(900));

		} catch (GraphNodeDuplicateIdException e) {
			e.printStackTrace();
		}

		System.out.println("directed graph (1 unconnected node) connected? " + directed.isConnected());
		System.out.println("undirected graph (1 unconnected node) connected? " + undirected.isConnected());

		directed.addEdge(0, 0);
		undirected.addEdge(0, 0);

		System.out.println("directed graph (node pointing to itself) connected? " + directed.isConnected());
		System.out.println("directed graph (node pointing to itself) connected? " + undirected.isConnected());

		directed.addEdge(2, 4);
		undirected.addEdge(2, 4);

		System.out.println("directed graph (should be no) connected? " + directed.isConnected());
		System.out.println("directed graph (should be no) connected? " + undirected.isConnected());


		// mGraph = new Graph<>(true);
		// fillGraph();
		// System.out.println(mGraph.toString());
		// System.out.println("connected? " + mGraph.isConnected());
	}

	/** just fills the graph with a bunch of nodes and edges */
	private static void fillGraph() {
		try {
			mGraph.addNode(0, new test().new MyNode(50));
			mGraph.addNode(1, new test().new MyNode(100));
			mGraph.addNode(2, new test().new MyNode(80));
			mGraph.addNode(3, new test().new MyNode(200));
			mGraph.addNode(4, new test().new MyNode(75));
			mGraph.addNode(5, new test().new MyNode(50));
			mGraph.addNode(6, new test().new MyNode(20));
			mGraph.addNode(7, new test().new MyNode(5));
		} catch (GraphNodeDuplicateIdException e) {
			e.printStackTrace();
		}

		mGraph.addEdge(1, 2);
		mGraph.addEdge(1, 3);
		mGraph.addEdge(2, 3);
		mGraph.addEdge(3, 4);
		mGraph.addEdge(4, 5);
		mGraph.addEdge(5, 6);
		mGraph.addEdge(5, 7);	// commenting this out makes it unconnected
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
