package entities;

/**
 * This class represents the edge of graph.
 * 
 * @author Shonova
 * @version 1.0
 */
public class Edge {

	private String id = "";
	private String streetName = " ";
	private int speedMax = 50;
	private double length = 0;
	private Node node1;
	private Node node2;

	private StringBuilder top = new StringBuilder();
	private StringBuilder end = new StringBuilder();

	public Edge(String id, Node node1, Node node2) {
		this.id = id;
		this.node1 = node1;
		this.node2 = node2;
		setLength();
	}
	
	public Edge(Node node1, Node node2) {
		this.node1 = node1;
		this.node2 = node2;
		setLength();
	}

	/**
	 * Edge constructor
	 * 
	 * @param id
	 *            unique id of the edge
	 */
	public Edge(String id) {
		this.id = id;
	}

	/**
	 * This function adds point to edge;
	 * 
	 * @param node
	 *            the point to be added
	 */
	public Node getNode1() {
		return node1;
	}
	
	public Node getNode2() {
		return node2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return streetName;
	}

	public void setName(String name) {
		this.streetName = name;
	}

	public int getSpeedMax() {
		return speedMax;
	}

	public void setSpeedMax(int speedMax) {
		this.speedMax = speedMax;
	}

	public double getLength() {
		return length;
	}

	/**
	 * This function converts decimal degrees to radians
	 */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/**
	 * This function converts radians to decimal degrees
	 */
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	/**
	 * This function calculates length of the edge by geographic coordinates of
	 * start point and end point
	 */
	public void setLength() {
		double theta = node1.getLon() - node2.getLon();
		double dist = Math.sin(deg2rad(node1.getLat())) * Math.sin(deg2rad(node2.getLat()))
				+ Math.cos(deg2rad(node2.getLat())) * Math.cos(deg2rad(node1.getLat())) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		length = dist;
	}

	/**
	 * This method returns string representation of Nodes/points of the edge
	 */
	public String getNodeToString() {
		return top.toString();
	}

	public String getEdgeToString() {
		return end.toString();
	}

}
