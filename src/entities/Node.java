package entities;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * This Class represents the node of the graph.
 * 
 * @author Shonova
 *
 */
public class Node {
	private String id;
	private double lon;
	private double lat;
	private boolean isCross;
	private double weight;	
	private LinkedHashMap<Node, Double> neighbours; 
	private Node prevNode; // Previous node in optimal path from source
	
	
	/**
	 * Creates Node
	 * @param id unique id of the Node
	 * @param lon longitude of the Node
	 * @param lat latitude of the Node
	 */
	public Node(String id, String lon, String lat) {
		this.id = id;
		this.lon = Double.parseDouble(lon);
		this.lat = Double.parseDouble(lat);
		this.weight = Float.MAX_VALUE;
		this.neighbours = new LinkedHashMap<>();
		this.prevNode = null;
	}
	
	public Node getPrevNode() {
		return prevNode;
	}
	
	
	public void setPrevNode(Node prevNode) {
		this.prevNode = prevNode;
	}
	
	
	public LinkedHashMap<Node, Double> getNeighbours() {
		return neighbours;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setCross(boolean isCross) {
		this.isCross = isCross;
	}
	
	public boolean isCross() {
		return isCross;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}	
	
	public void setWeight(double w) {
		this.weight = w;
	}
	
	public double getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return  id + " [ pos=\"" + lon + "," + lat + "!\" ]\n";
	}
}
