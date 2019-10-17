package graphs.freshcode.wk10fixed;

import java.util.*;

public class VertexIDList extends TreeMap<Integer, Double> {

	/*
	 * List of vertices, to be used predominantly to represent
	 * the neighbours of a vertex
	 * 
	 * As an extension of TreeMap, inherits its methods, such
	 * as size()
	 */
	
	private Iterator<Map.Entry<Integer, Double>> it; 
	
	public VertexIDList() {
		// constructor
		super();
	}
	
	public Integer top() {
		// returns first element of list, treating as a queue
		return this.firstKey();
	}
	
	public void pop() {
		// deletes first element of list, treating as a queue
		this.remove(this.firstKey());
	}
	
	public void push(Integer n) {
		// inserts first element of list, treating as a queue
		// set to 0 weight as default if not specified
		this.put(n, 0.0); 
	}
	
	public void push(Integer n, Double w) {
		// inserts first element of list, treating as a queue, weight w
		this.put(n, w);
	}
	
	public Double getWeight(Integer n) {
		return this.get(n);
	}
	
	public void print() {
		it = this.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Double> pairs = it.next();
			//System.out.print(" " + pairs.getKey());
			// can print out weight as well
			System.out.print(" " + pairs.getKey() + "(" + pairs.getValue() + ")");
		}		
		System.out.println();
	}
	
	public Iterator<Integer> iterator() {
		// returns an iterator to use to iterate over the list
		return this.keySet().iterator();
	}
	
	public static void main(String[] args) {
		VertexIDList v = new VertexIDList();
		
		v.push(2);
		v.push(5);
		v.push(3);
		v.print();
		v.pop();
		System.out.println(v.top());
		v.print();
		
		Iterator<Integer> vIt = v.iterator();
		
		System.out.println("testing iterator ...");
		while (vIt.hasNext()) {
			System.out.println(vIt.next());
		}
		v.print();
	}
}
