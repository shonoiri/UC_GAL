package entities;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class represent graph.
 * 
 * @author Shonova
 *
 */
public class Graph {

	private LinkedList<Node> nodes = new LinkedList<>();
	private LinkedList<Edge> edges = new LinkedList<>();
	private String name;

	public Graph(String name) {
		this.name = name;
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}

	public LinkedList<Edge> getEdges() {
		return edges;
	}
	
	public void addNode(Node node) {
		nodes.add(node);
	}

	public String getName() {
		return name;
	}

	public LinkedList<Node> getNodes() {
		return nodes;
	}

	public void setEdges(LinkedList<Edge> edges) {
		this.edges = edges;
	}

	public void setNodes(LinkedList<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		StringBuilder graph = new StringBuilder();
		graph.append("strict graph {");
		//TODO: graph to string
/*		edges.forEach(e -> {
			graph.append('\n').append("node [ label= \"").append(e.getName()).append("\"")
					.append(", height=0.1, width=0.1 ]").append('\n');
			graph.append(e.getNodeToString());
			graph.append(e.getEdgeToString());
		});*/
		graph.append("}\n");

		return graph.toString();
	}
}
