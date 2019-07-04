
import java.util.List;
import java.util.ArrayList;

/**
 * todo: use Templates to make the data more generic
 *
 * Library for directed and undirected graphs.
 * Note that this uses lists for its data, so it may
 * not be as fast as it could be.
 *
 *	USAGE:
 *		- When instantiating, define whether it's directed.
 *
 *		- Add the nodes, supplying a unique id for that node.
 *
 *		- Add edges.  Use a weight if desired.
 *
 *		- Use the graph as you like.
 */
class Graph {

	//-----------------------
	//	constants
	//-----------------------

	private static final String TAG = "Graph";

	//-----------------------
	//	data
	//-----------------------

	/** holds all the nodes */
	private List<Integer> mNodes = new ArrayList<>();

	/**
	 * Holds all the edges.
	 *
	 * Note that for a undirected graph, there will be just one
	 * edge--startNode and endNode are the same things for them.
	 */
	private List<Edge> mEdges = new ArrayList<>();

	/** Tells whether this is a directed graph or undirected (default) */
	private boolean mDirected = false;

	//-----------------------
	//	constructors
	//-----------------------

	/**
	 * Basic constructor. Graph will be undirected (default)!
	 */
	public Graph() {
	}

	public Graph(boolean directed) {
		mDirected = directed;
	}


	//-----------------------
	//	methods
	//-----------------------

	/**
	 * Add a new node to this graph.
	 *
	 *	@param	id	A unique id for this node.  Yes, the Graph class will
	 *				break if the id isn't unique!
	 *
	 *	@return	The current number of nodes AFTER this one has been added.
	 */
	public int addNode(int id) {
		mNodes.add(id);
		return mNodes.size();
	}

	/**
	 * Adds an edge to this class.  Doesn't check for redundancies,
	 * so be careful!
	 *
	 *	@param	startNode	The first the starting node (not relevant
	 *						for non-directed graphs).
	 *
	 *	@param	endNode		The ending edge.
	 *
	 *	@param	weight	The weight of this edge.
	 *
	 *	@returns	The total number of edges AFTER this has been added.
	 */
	public int addEdge(int startNode, int endNode, int weight) {
		Edge edge = new Edge();
		edge.startNode = startNode;
		edge.endNode = endNode;
		edge.weight = 0;

		mEdges.add(edge);
		return mEdges.size();
	}

	/**
	 *	Just like AddEdge, but without any weight.
	 */
	 public int addEdge(int startNode, int endNode) {
		 return addEdge(startNode, endNode, 0);
 	}

	/**
	 * private util method to simplify a few things
	 */
	 private int addEdge(Edge edge) {
		 mEdges.add(edge);
		 return mEdges.size();
	 }


	 /**
 	 * Returns a list of all the nodes adjacent to the given node.
 	 * If none, this returns an empty list.
	 *
	 *	O(n)
 	 *
 	 * @param	nodeId		The ID of the node in question.
 	 */
 	public List<Integer> getAllAdjacentTo(int nodeId) {

 		List<Integer> adjacentList = new ArrayList<>();
		List<Edge> edgeList = getEdges(nodeId);
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			if (mDirected) {
				if (edge.startNode == nodeId) {
					// This is an edge that stars with our node.
					// Add the end node to our list.
					adjacentList.add(edge.endNode);
				}
			}
			else {
				// undirected--just include the other node.
				if (edge.startNode == nodeId) {
					adjacentList.add(edge.endNode);
				}
				else {
					adjacentList.add(edge.startNode);
				}
			}
		}

		return adjacentList;
 	}


	/**
	 * Returns a list of all the node ids for this graph.
	 */
	public List<Integer> getAllNodes() {
		return mNodes;
	}


	/**
	 * Curious if two nodes are adjacent?  Use this to find out!
	 * For undirected graphs, the order doesn't matter.
	 */
	public boolean isAdjacent(int startNode, int endNode) {
		boolean found = false;

		// simply go through our adjacency list and see if there's a match.
		for (Edge edge : mEdges) {
			if ((edge.startNode == startNode) && (edge.endNode == endNode)) {
				found = true;
				break;
			}
			else if (mDirected == false) {
				// special case for undirected graphs
				if ((edge.startNode == endNode) && (edge.endNode == startNode)) {
					found = true;
					break;
				}
			}

		}

		return found;
	}

	/**
	 * Find all the edges that use the given node.
	 * If none are found, the returned list will be empty.
	 *
	 *	O(n)
	 *
	 * @param	nodeId		The ID of the node in question.
	 */
	protected List<Edge> getEdges(int nodeId) {

		List<Edge> edgeList = new ArrayList<>();

		for (int i = 0; i < mEdges.size(); i++) {
			Edge edge = mEdges.get(i);
			if ((edge.startNode == nodeId) || (edge.endNode == nodeId)) {
				edgeList.add(edge);
			}
		}

		return edgeList;
	}

 	/**
 	 * Creates an exact duplicate of this graph.
 	 */
 	public Graph clone() {
 		Graph newGraph = new Graph(mDirected);

 		for (int i = 0; i < mNodes.size(); i++) {
 			newGraph.addNode(mNodes.get(i));
 		}

 		for (int i =0; i < mEdges.size(); i++) {
 			Edge edge = mEdges.get(i);
 			newGraph.addEdge(edge);
 		}

 		return newGraph;
 	}



	/**
	 * Returns the number of nodes in this graph. Hope you didn't make
	 * any duplicates!
	 */
	public int numNodes() {
		return mNodes.size();
	}

	/**
	 * Returns the number of edges in this graph.  For undirected graphs,
	 * this may return the count for both A->B and B-> IF YOU WERE DUMB
	 * ENOUGH TO ENTER THOSE EDGES!
	 */
	 public int numEdges() {
		 return mEdges.size();
	 }

	/**
	 * Removes the given node.  This assumes that any edges associated
	 * with this node have been PREVIOUSLY removed!  This will cause
	 * a total cluster fuck if you don't do this before-hand! You've
	 * been warned!
	 *
	 * But wait, there's more! If there are MORE THAN ONE node with
	 * the same id (and there shouldn't!), this will remove only the
	 * first that was found.  Really--you should be more careful with
	 * your graphs!
	 *
	 *	@param	id	The id of the node to be removed.
	 *
	 *	@returns	TRUE if the node was removed.
	 *				FALSE if the node can't be found.
	 */
	 public boolean removeNode(int id) {
		 for (int i = 0; i < mNodes.size(); i++) {
			 if (mNodes.get(i) == id) {
				 mNodes.remove(i);
				 return true;
			 }
		 }
		 return false;	// couldn't be found
	 }

	 /**
	  *	Removes the specified edge.  For undirected graphs, will
	  * try both directions, possibly removing both.
	  */
	 public boolean removeEdge(int startNode, int endNode) {
		 boolean removed = false;

		 for (int i = 0; i < mEdges.size(); i++) {
			 Edge edge = mEdges.get(i);
			 if ((edge.startNode == startNode) && (edge.endNode == endNode)) {
				 mEdges.remove(i);
				 removed = true;
				 break;
			 }
		 }

		 // TODO: this shouldn't be necessary!
		 if (mDirected) {
			 return removed;
		 }

		 // Undirected, check for other direction
		 for (int i = 0; i < mEdges.size(); i++) {
			 Edge edge = mEdges.get(i);
			 if ((edge.startNode == endNode) && (edge.endNode == startNode)) {
				 mEdges.remove(i);
				 removed = true;
				 break;
			 }
		 }

		 return removed;
	 }

	 /**
 	 *	Prints the contents of this graph to a string.
 	 */
 	public String toString() {
 		String nodestr = " Nodes[" + mNodes.size() + "]:";
 		for (int i = 0; i < mNodes.size(); i++) {
 			nodestr = nodestr + " " + mNodes.get(i);
 		}

 		String edgestr = " Edges[" + mEdges.size() + "]:";
 		for (int i = 0; i < mEdges.size(); i++) {
 			Edge edge = mEdges.get(i);
 			edgestr = edgestr + " (" + edge.startNode + ", " + edge.endNode
 				+ ": " + edge.weight + ")";
 		}

 		return nodestr + "\n" + edgestr;
 	}


	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//	classes
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Defines an edge of the graph. Very simple class meant to
	 * only be used within the Graph class.
	 */
	private class Edge {
		public int startNode = -1;
		public int endNode = -1;
		public int weight = 0;
	}

}
