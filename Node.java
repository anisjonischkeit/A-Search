import java.util.*;

public class Node {

	String identifier;
	int hieristic;
	ArrayList<ArrayList<Object>> connections = new ArrayList<ArrayList<Object>>();



	public Node(String identifier, int hieristic){
        this.identifier = identifier;
        this.hieristic = hieristic;
        connections.add(new ArrayList<Object>());
        connections.add(new ArrayList<Object>());
    }

    public void addConnection(Node node, int cost){
    	
        
        connections.get(0).add(node);
        connections.get(1).add(cost);
    }
}
    