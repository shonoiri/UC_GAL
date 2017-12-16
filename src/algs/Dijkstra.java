package algs;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import entities.Edge;
import entities.Graph;
import entities.Node;

public class Dijkstra {

	private LinkedHashMap<String, Node> nodes = new LinkedHashMap<>();
	private LinkedList<Edge> edges;
	private LinkedList<Edge> costra = new LinkedList<>();
	
	public Dijkstra(Graph graph) {
		for (Node node : graph.getNodes()) {
			nodes.put(node.getId(), node);
		}
		this.edges = new LinkedList<>(graph.getEdges());
		nodes.get(graph.getNodes().getFirst().getId()).setWeight(0);
		execute(graph.getNodes().getFirst());
	}
	
	public void execute(Node currentNode) {
	while (!nodes.isEmpty()) {
		Node u = getMinWeightNode();
		nodes.remove(u);
		Double alt = 0d;
		u.getNeighbours().forEach(alt = );
		for (Node node : u.getNeighbours().values()) {
			Double alt = node.getWeight();
		}
	}
		
		
		/*Node next = currentNode.getNeighbours().getFirst();
		Edge shortesEdge = edges.getFirst();
		for (int i = 0; i < currentNode.getNeighbours().size(); i++) {
			Edge edge = edges.getFirst();
			double w = currentNode.getWeight() + edge.getLength();
			Node node = nodes.get(currentNode.getNeighbours().get(i).getId());
			node.setWeight(w);			
			if(next.getWeight() > w) {
				next = node;
				shortesEdge = edge;						
			}
			edges.removeFirst();
		}
		costra.add(shortesEdge);
		if(!edges.isEmpty())
			execute(next);
	*/}
	
	private Node getMinWeightNode() {
		return nodes.values().stream().min(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Double.compare(o1.getWeight(), o2.getWeight());
			}
		}).get();
	}
	
	private Edge getEdge(String node1, String node2) {
		return edges.stream().filter(e ->(
			e.getNode1().getId().equals(node1) && e.getNode2().getId().equals(node2)
		)).findFirst().get();
	}
	
}
