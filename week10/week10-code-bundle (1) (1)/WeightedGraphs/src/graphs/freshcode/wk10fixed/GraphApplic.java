package graphs.freshcode.wk10fixed;

import java.util.*;

public class GraphApplic extends Graph {

	private static String DEPTH_FIRST_TRAVERSAL_PATH="";
	private String MAXIMAL_DEPTH_FIRST_TRAVERSAL_PATH;



	public Integer numVerticesGtrDegree(Integer v, Integer k){
		int count =0;


		Stack<Integer> s = new Stack<Integer>();

		s.push(v);
		getVertex(v).setMarked();

		while (!s.isEmpty()) { // while not all vertices visited
			v = s.peek();

			if(v>k)
				count++;

			VertexIDList adjList = getVertex(v).getAdjs();
			Iterator<Integer> vIt = adjList.iterator();
			// get iterator over adjacency list representing neighbours
			while (vIt.hasNext() && getVertex(v).isMarked())
				v = vIt.next(); // skip over visited neighbours

			if (getVertex(v).isMarked()) {  // only occurs if all neighbours visited
				s.pop();       // remove from stack
			}
			else {             // v is an unvisited neighbour
				s.push(v);     // add to stack
				getVertex(v).setMarked(); }
		}
		return count;
	}


	public Integer depthFirstTraversalMaxPath(Integer v){


		int[] maximalPathslenght = new int[1000];

		for (int i=0;i<1000;i++)
			maximalPathslenght[i]=0;


		// PRE: v is the id of a vertex in the graph
		// POST: Prints out a depth-first traversal of a graph
		//         (for just the connected component containing v)

		// Iterative version of DFT
		Stack<Integer> s = new Stack<Integer>();


		DEPTH_FIRST_TRAVERSAL_PATH+=" "+v;
		System.out.println();
		s.push(v);
		getVertex(v).setMarked();

		int index=0;
		while (!s.isEmpty()) { // while not all vertices visited
			v = s.peek();
			VertexIDList adjList = getVertex(v).getAdjs();
			Iterator<Integer> vIt = adjList.iterator();
			// get iterator over adjacency list representing neighbours
			while (vIt.hasNext() && getVertex(v).isMarked())
				v = vIt.next(); // skip over visited neighbours

			if (getVertex(v).isMarked()) {  // only occurs if all neighbours visited
				MAXIMAL_DEPTH_FIRST_TRAVERSAL_PATH=s.toString();
				s.pop();       // remove from stack

				String maximal=MAXIMAL_DEPTH_FIRST_TRAVERSAL_PATH.replaceAll(",","");

				maximalPathslenght[index++]=maximal.length()-2;
			}
			else {             // v is an unvisited neighbour
				s.push(v);     // add to stack
				getVertex(v).setMarked();
				DEPTH_FIRST_TRAVERSAL_PATH+=" "+v;
			}
		}

		int max=0;

		a :for (int i:maximalPathslenght) {
			if(i==0)
				break a;
			if(i>max)
				max=i;

		}


		return max;
	}

	public void depthFirstTraversalRec1(Integer v) {
		// PRE: v is the id of a vertex in the graph
		// POST: Prints out a depth-first traversal of a graph
		//         (for just the connected component containing v)
		
		// Recursive version of DFT
		DEPTH_FIRST_TRAVERSAL_PATH+=" "+v;

		getVertex(v).setMarked(); // get vertex object with id v,
		                          // indicate visited by setting marked
		VertexIDList adjList = getVertex(v).getAdjs();
		                          // get adjacency list representing neighbours
		Iterator<Integer> vIt = adjList.iterator();
		while (vIt.hasNext()) {   // iterate over neighbours
			Integer nextVertex = vIt.next();
			if (!getVertex(nextVertex).isMarked())  // if neighbour hasn't been visited
				depthFirstTraversalRec1(nextVertex); // visit it
		}
	}
	
	public List<Integer> depthFirstTraversalRec2(Integer v) {
		// PRE: v is the id of a vertex in the graph
		// POST: Returns a list containing a depth-first traversal of a graph
		//         (for just the connected component containing v)
		
		// Recursive version of DFT
		List<Integer> resList = new Vector<Integer>(); // list to hold DFT

		getVertex(v).setMarked(); // get vertex object with id v,
								  // indicate visited by setting marked
		VertexIDList adjList = getVertex(v).getAdjs();
								  // get adjacency list representing neighbours
		Iterator<Integer> vIt = adjList.iterator();
		while (vIt.hasNext()) {   // iterate over neighbours
			Integer nextVertex = vIt.next();
			if (!getVertex(nextVertex).isMarked()) {  // if neighbour hasn't been visited
				List<Integer> tmpResList = depthFirstTraversalRec2(nextVertex);
				                  // get traversal from recursive call
				resList.addAll(tmpResList);  // combine lists from recursive calls
			}
		}
		resList.add(0, v); // put current vertex at start of list
		return resList;
	}
	
	public void depthFirstTraversalIter1(Integer v) {
		// PRE: v is the id of a vertex in the graph
		// POST: Prints out a depth-first traversal of a graph
		//         (for just the connected component containing v)
		
		// Iterative version of DFT
		Stack<Integer> s = new Stack<Integer>();

//		System.out.print(" " + v);

		DEPTH_FIRST_TRAVERSAL_PATH+=" "+v;
		System.out.println();
		s.push(v);
		getVertex(v).setMarked();
		
		while (!s.isEmpty()) { // while not all vertices visited
			v = s.peek();
			VertexIDList adjList = getVertex(v).getAdjs();
			Iterator<Integer> vIt = adjList.iterator();
							   // get iterator over adjacency list representing neighbours			
			while (vIt.hasNext() && getVertex(v).isMarked())
				v = vIt.next(); // skip over visited neighbours
		
			if (getVertex(v).isMarked()) {  // only occurs if all neighbours visited
				s.pop();       // remove from stack
			}
			else {             // v is an unvisited neighbour
				s.push(v);     // add to stack
				getVertex(v).setMarked();
				DEPTH_FIRST_TRAVERSAL_PATH+=" "+v;
			}
		}
	}



	public static void main(String[] args) {
		GraphApplic g = new GraphApplic();
		List<Integer> l;
		
		g.setDefault();
		System.out.println("DFT #1");
		g.depthFirstTraversalRec1(g.getFirstVertexID());
		System.out.println(DEPTH_FIRST_TRAVERSAL_PATH);
		g.setUnmarked();
		// reset DEPTH_FIRST_TRAVERSAL_PATH
		DEPTH_FIRST_TRAVERSAL_PATH="";
		System.out.println();
		
		System.out.println("DFT #2");
		l = g.depthFirstTraversalRec2(g.getFirstVertexID());
		g.setUnmarked();
		Iterator<Integer> lIt = l.iterator();
		while (lIt.hasNext())
			DEPTH_FIRST_TRAVERSAL_PATH+=" "+lIt.next();
		System.out.println(DEPTH_FIRST_TRAVERSAL_PATH);

		// reset DEPTH_FIRST_TRAVERSAL_PATH
		DEPTH_FIRST_TRAVERSAL_PATH="";
		System.out.println();

		System.out.println("DFT #3");
		g.depthFirstTraversalIter1(g.getFirstVertexID());
		g.setUnmarked();

		System.out.println(DEPTH_FIRST_TRAVERSAL_PATH);

		// reset DEPTH_FIRST_TRAVERSAL_PATH
		DEPTH_FIRST_TRAVERSAL_PATH="";
		System.out.println();

		System.out.println("Nodes greater than   4");
		int path=g.numVerticesGtrDegree(g.getFirstVertexID(),4);
		System.out.println(path);
		g.setUnmarked();


	}
}
