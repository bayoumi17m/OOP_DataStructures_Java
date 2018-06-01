package student;

import controllers.Spaceship;
import models.Edge;
import models.Node;
import models.NodeStatus;
import student.Paths;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
//import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import controllers.SearchPhase;

import controllers.RescuePhase;

/** An instance implements the methods needed to complete the mission. */
public class MySpaceship implements Spaceship {
	

	/** The spaceship is on the location given by parameter state.
	 * Move the spaceship to Planet X and then return (with the spaceship is on
	 * Planet X). This completes the first phase of the mission.
	 * 
	 * If the spaceship continues to move after reaching Planet X, rather than
	 * returning, it will not count. If you return from this procedure while
	 * not on Planet X, it will count as a failure.
	 *
	 * There is no limit to how many steps you can take, but your score is
	 * directly related to how long it takes you to find Planet X.
	 *
	 * At every step, you know only the current planet's ID, the IDs of
	 * neighboring planets, and the strength of the signal from Planet X at
	 * each planet.
	 *
	 * In this rescuePhase,
	 * (1) In order to get information about the current state, use functions
	 * currentID(), neighbors(), and signal().
	 *
	 * (2) Use method onPlanetX() to know if you are on Planet X.
	 *
	 * (3) Use method moveTo(int id) to move to a neighboring planet with the
	 * given ID. Doing this will change state to reflect your new position.
	 */
	@Override
	public void search(SearchPhase state) {
		// TODO: Find the missing spaceship
		HashSet<Integer> map = new HashSet<Integer>();
		dfsWalk(state,map);
		return;
	}
	
	// -1912715221887551404
	
	/** The walker is standing on a Node u (say) given by State s.
	Visit every node reachable along paths of unvisited nodes from node u. 
	End with walker standing on Node u.
	Precondition: u is unvisited. */
	public static void dfsWalk(SearchPhase state,HashSet<Integer> map) {
		if (state.onPlanetX()) {
			return;
		}
		int n = state.currentID();
		map.add(n);
		Random rand = new Random();
		float epsilon = rand.nextFloat();
		if (epsilon > 0.9) {
			RandomizeArray(state.neighbors());
		} else {
			QS(state.neighbors(),0,state.neighbors().length - 1);
		}
		for (NodeStatus m: state.neighbors()) {
			if (!map.contains(m.id())) {
				state.moveTo(m.id());
				map.add(m.id());
				dfsWalk(state,map);
				if (state.onPlanetX()) {
					return;
				}
				state.moveTo(n);
			}
		}
		return;
	}
	
	// -6518032787690943609
	

	/** The spaceship is on the location given by state. Get back to Earth
	 * without running out of fuel and return while on Earth. Your ship can
	 * determine how much fuel it has left via method fuelRemaining().
	 * 
	 * In addition, each Planet has some gems. Passing over a Planet will
	 * automatically collect any gems it carries, which will increase your
	 * score; your objective is to return to earth successfully with as many
	 * gems as possible.
	 * 
	 * You now have access to the entire underlying graph, which can be accessed
	 * through parameter state. Functions currentNode() and earth() return Node
	 * objects of interest, and nodes() returns a collection of all nodes on the
	 * graph.
	 *
	 * Note: Use moveTo() to move to a destination node adjacent to your current
	 * node. */
	@Override
	public void rescue(RescuePhase state) {
		// TODO: Complete the rescue mission and collect gems
		
		//Description of Algorithm to get the highest score..
		// Initially, we will start with Djikstra's algorithm..? to get a working solution
		
		minRescue(state);
		//gStar(state);
		//hStar(state);
//		if (state.currentNode() != state.earth()) {
//			minRescue(state);
//		}
		//heapGem(state);
	}
	

	/** This function takes last element as pivot,
    places the pivot element at its correct
    position in sorted array, and places all
    smaller (smaller than pivot) to left of
    pivot and all greater elements to right
    of pivot */
	public static int partition(NodeStatus arr[], int low, int high) {
		NodeStatus pivot = arr[high]; 
		int i = (low-1); // index of smaller element
		for (int j=low; j<high; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (arr[j].signal() <= pivot.signal()) {
				i++;

				// swap arr[i] and arr[j]
				NodeStatus temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				}
			}

		// swap arr[i+1] and arr[high] (or pivot)
		NodeStatus temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;

		return i+1;
		}


	/** The main function that implements QuickSort()
	 * arr[] --> Array to be sorted,
	 * low  --> Starting index,
	 * high  --> Ending index */
	public static void QS(NodeStatus arr[], int low, int high) {
		if (low < high) {
			/* pi is partitioning index, arr[pi] is 
			 * now at right place */
			int pi = partition(arr, low, high);

			// Recursively sort elements before
			// partition and after partition
			QS(arr, low, pi-1);
			QS(arr, pi+1, high);
			}
		}
	
	
	/**
	 * This function ....
	 * @return
	 * @param state
	 */
	public static void minRescue(RescuePhase state) {
		List<Node> path = Paths.minPath(state.currentNode(),state.earth()); //(LinkedList<Node>) Paths.minPath(state.currentNode(),state.earth());
		//System.out.println(path);
		path = path.subList(1, path.size());
		for (Node n: path) {
			//System.out.println(n);
			state.moveTo(n);
		}
		return;
	}
	
	
	/**
	 * 
	 * @param state
	 */
	public static void gStar(RescuePhase state) {
		List<Node> pathGem = Paths.maxGem(state.currentNode(), state.earth());
		Node n= pathGem.get(1);
		while (Paths.minDist(n, state.earth()) < state.fuelRemaining()-100) {
			pathGem = Paths.maxGem(state.currentNode(), state.earth());
			n= pathGem.get(1);
			while (n == state.earth()) {
				Random rand = new Random();
				Edge[] edges = state.currentNode().exits().toArray(
						new Edge[state.currentNode().exits().size()]);
				n = edges[rand.nextInt(edges.length)].getOther(state.currentNode());
			}
			state.moveTo(n);
		}
		minRescue(state);
	}
	
	
	
	/**
	 * 
	 */
	public static void heapGem(RescuePhase state) {
		HashSet<Node> deadStates = new HashSet<Node>();
		while (state.currentNode() != state.earth() && state.fuelRemaining() > 100) {
			Heap<Node> h = new Heap<Node>(false);
			Set<Edge> exit = state.currentNode().exits();
			int totGem = 0;
			for (Edge edge: exit) {
				Node n = edge.getOther(state.currentNode());
				int gems = n.gems();
				totGem = totGem + gems;
				int dist = Paths.minDist(n, state.earth()) + edge.length;
				if (dist < state.fuelRemaining() && gems != 0) {
					h.add(n, gems/dist);
				}
				
			}
			if (totGem == 0) {
				deadStates.add(state.currentNode());
			}
			if (h.size() > 0) {
				Node n = h.poll();
				while (deadStates.contains(n) && h.size > 0) {
					n = h.poll();
				}
				state.moveTo(n);
			} else {
				minRescue(state);
			}
		}
	}
	
	
	
	
	
	
	/**
	 * 
	 * @param state
	 */
	public static void hStar(RescuePhase state) {
		while (state.currentNode() != state.earth()) {
			//
			Heap<Node> h = getHeap(state);
			Node n =h.poll();
			while (state.currentNode().getEdge(n).length + Paths.minDist(n, state.earth()) >= state.fuelRemaining()) {
				n = h.poll();
			}
			if (n != null) state.moveTo(n);
			else minRescue(state);
		}
	}
	
	
	/**
	 * 
	 * @param state
	 */
	public static Heap<Node> getHeap(RescuePhase state) {
		Heap<Node> h = new Heap<Node>(false);
		for (Edge e: state.currentNode().exits()) {
			Node n = e.getOther(state.currentNode());
			h.add(n, n.gems() / e.fuelNeeded());
		}
		return h;
	}
	
	
	
	public static HashSet<Node> deadStates(RescuePhase state, HashSet<Node> deadNodes){
		return null;
	}

	
	/**
	 * Input: an int array
	 * Output: shuffled array(in an randomized order)
	 * @param array
	 * @return
	 */
	public static NodeStatus[] RandomizeArray(NodeStatus[] array){
		Random rgen = new Random();  // Random number generator			
 
		for (int i=0; i<array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    NodeStatus temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
 
		return array;
	}
	
	
	
	/**
	 * 
	 * @param state
	 */
	public static void vStar(RescuePhase state) {
		//  Value Function V: S x A --> R
		//  V(s_{t}, a_{t}) := (1 - alpha)*r(s_{t},a_{t}) + alpha*(max_{a}(V(s_{t-1}, a_{t-1})))
		//
		//  Recursively Determine Backwards the Optimal Path
		//
		//  This might be too computationally expensive, but try building it before building an estimation
		//  method for the max_{a}(V(s_{t-1}, a_{t-1})) portion of the equation potentially.
		minRescue(state);
	}
	
	

}
