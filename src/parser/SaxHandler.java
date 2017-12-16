package parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import algs.Dijkstra;
import entities.Edge;
import entities.Graph;
import entities.Node;

/**
 * This Class is Listener implementation and generates Graph from OSM file.
 * 
 * @author Shonova
 *
 */
public class SaxHandler extends DefaultHandler {

	private static final String[] roadTypes = new String[] { "motorway", "trunk", "primary", "secondary", "tertiary",
			"road", "unclassified", "residential", "service" };
	private ArrayList<String> refs = new ArrayList<>();
	private LinkedHashMap<String, Node> nodes = new LinkedHashMap<>();
	private String currentEdgeId;
	private Graph graph;

	public SaxHandler(String graphName) {
		this.graph = new Graph(graphName);
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println(LocalDateTime.now() + " Start document parsing ");
	}

	/**
	 * As input document parsing is done string representation of graph in DOT
	 * format would be created.
	 */
	@Override
	public void endDocument() throws SAXException {
		System.out.println(LocalDateTime.now() + " End document parsing ");
		Dijkstra d = new Dijkstra(graph);
		
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(Main.outputFile))) {
			writer.write(graph.toString());
		} catch (IOException e) {
			System.out.println("Invalid output file");
			e.printStackTrace();
		}
	}

	/**
	 * This method evaluate element of OSM file by its name and attributes. In case
	 * of "node" element the new Node is created. In case of "way" element the new
	 * Edge is created and set to currentEdge. In case of way/nd element referenced
	 * node id added to refs list. In case of "tag" element first attribute is
	 * checked - if it is "highway" of any of "roads" types all referenced points
	 * adds to curentEdge and isHW flag is marked as true;
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("node"))
			nodes.put(attributes.getValue(0),
					new Node(attributes.getValue(0), attributes.getValue("lon"), attributes.getValue("lat")));
		if (qName.equals("way")) {
			refs = new ArrayList<>();
			currentEdgeId = attributes.getValue("id");
		}
		if (qName.equals("nd")) {
			refs.add(attributes.getValue("ref"));
		}
		if (qName.equals("tag") && attributes.getValue(0).equals("highway")
				&& Arrays.asList(roadTypes).contains(attributes.getValue("v").toLowerCase())) {
			
			Iterator<String> i = refs.iterator();			
			Node prev = nodes.get(i.next());
			while (i.hasNext()) {
				Node node = nodes.get(i.next());
				Edge edge = new Edge(currentEdgeId, prev, node);
				node.getNeighbours().put(prev, edge.getLength());
				prev.getNeighbours().put(node, edge.getLength());
				graph.addNode(prev);
				graph.addNode(node);
				graph.addEdge(edge);
				prev = node;				
			}
		}
	}

}
