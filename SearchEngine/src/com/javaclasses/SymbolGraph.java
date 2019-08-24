package com.javaclasses;
import java.util.ArrayList;
import java.util.Scanner;
public class SymbolGraph {
	private ST<String, Integer> st; // string -> index
	private String[] keys; // index -> string
	private Graph G;

	public SymbolGraph(String filename, String delimiter) {
		st = new ST<String, Integer>();

		// First pass builds the index by reading strings to associate
		// distinct strings with an index
		In in = new In(filename);
		// while (in.hasNextLine()) {
		while (!in.isEmpty()) {
			String[] a = in.readLine().split(delimiter);
			for (int i = 0; i < a.length; i++) {
				if (!st.contains(a[i]))
					st.put(a[i], st.size());
			}
		}
		//StdOut.println("Done reading " + filename);

		// inverted index to get string keys in an aray
		keys = new String[st.size()];
		for (String name : st.keys()) {
			keys[st.get(name)] = name;
		}

		// second pass builds the graph by connecting first vertex on each
		// line to all others
		G = new Graph(st.size());
		in = new In(filename);
		while (in.hasNextLine()) {
			String[] a = in.readLine().split(delimiter);
			int v = st.get(a[0]);
			for (int i = 1; i < a.length; i++) {
				int w = st.get(a[i]);
				G.addEdge(v, w);
			}
		}
	}


	public boolean contains(String s) {
		return st.contains(s);
	}

	/**
	 * Returns the integer associated with the vertex named <tt>s</tt>.
	 * 
	 * @param s
	 *            the name of a vertex
	 * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex
	 *         named <tt>s</tt>
	 */
	public int index(String s) {
		return st.get(s);
	}

	/**
	 * Returns the name of the vertex associated with the integer <tt>v</tt>.
	 * 
	 * @param v
	 *            the integer corresponding to a vertex (between 0 and <em>V</em> -
	 *            1)
	 * @return the name of the vertex associated with the integer <tt>v</tt>
	 */
	public String name(int v) {
		return keys[v];
	}

	/**
	 * Returns the graph assoicated with the symbol graph. It is the client's
	 * responsibility not to mutate the graph.
	 * 
	 * @return the graph associated with the symbol graph
	 */
	public Graph G() {
		return G;
	}

	/**
	 * Unit tests the <tt>SymbolGraph</tt> data type.
	 */
	public static String[] getPincodes(String source) {

		SymbolGraph sg = new SymbolGraph("C:\\Users\\DELL\\Final Project\\searchengine\\searchengine\\src\\com\\javaclasses\\pincode.txt", "/");
		Graph G = sg.G();
		ArrayList<String> arr = new ArrayList<String>();
		
			 if (sg.contains(source)) {
		
			int s = sg.index(source);
			for (int v : G.adj(s)) {
				arr.add(sg.name(v));
			}
			String[] pincodes = new String[arr.size()];
			for (int i = 0; i < pincodes.length; i++) {
				pincodes[i] = arr.get(i);
			}
			RadixSort.radixSortA(pincodes, 3);
			System.out.println("\nPincodes for the City: " + source.toUpperCase());
			for (String p : pincodes) {
				System.out.println(p);
			}
			return pincodes;
		} else {
			StdOut.println("input does not contain '" + source + "'\n");
			return null;
		}

	}


	public static void main(String args[]) {
		  Scanner myObj = new Scanner(System.in);
	    // String input
		  System.out.println("Enter the City");
		  String source = myObj.nextLine();
		 // System.out.println(source);
		 
		String[] pincodes = getPincodes(source);
		/*for (String p : pincodes) {
			System.out.println(p);
		}
		// return pincodes; */

	}
	}
