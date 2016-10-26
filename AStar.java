import java.util.*;

public class AStar{
    
    private static void traverse(Node start, Node end){
        
        ArrayList<String> closedList = new ArrayList<String>();
        PriorityQueue<TreeNode> openList = new PriorityQueue<TreeNode>();

        TreeNode treeNode = new TreeNode(start, null, 0);

        openList.add(treeNode);
        while (!openList.isEmpty()) {
            TreeNode Current = openList.poll();
            closedList.add(Current.identifier);

            if(Current.identifier.equals(end.identifier) ){
                printPath(Current);
                System.out.println("Sequence of Traversed Nodes: " + closedList.toString());
                break;
            } else {
                ArrayList<TreeNode> treeBranches = Current.getBranches();

                for (String searchedId : closedList ) {
                    treeBranches.removeIf(branch -> branch.identifier.equals(searchedId));
                }

                for (Iterator<TreeNode> i = openList.iterator(); i.hasNext(); ){
                    TreeNode searched = i.next(); // must be called before you can call i.remove()
                    for (Iterator<TreeNode> j = treeBranches.iterator(); j.hasNext(); ){
                        TreeNode branch = j.next(); // must be called before you can call i.remove()
                        if ( branch.identifier.equals(searched.identifier) ) {
                            if (branch.distanceFromStart + branch.hieristic > searched.distanceFromStart + searched.hieristic) {
                                j.remove();
                            } else {
                                i.remove();
                            }
                        }
                    }
                }

                openList.addAll(treeBranches);
                String temp = "";
                for (TreeNode item : openList) {
                    temp += item.identifier + ", ";
                }
                System.out.print("openList = " + temp);
                System.out.println(" closedList = " + closedList);
            }
        }
        
    }
    
    private static void printPath(TreeNode node){
        LinkedList<String> path = new LinkedList<String>();
        TreeNode current = node;
        
        path.add(current.identifier);
        
        while(current.Parent != null){
            current = current.Parent;
            path.addFirst(current.identifier);
        }
        
        System.out.println("Path: " + path.toString());
        
    }
    
    public static void main(String[] args){
        
        Node s = new Node("S", 11);
       
        Node a = new Node("A", 8);
        Node b = new Node("B", 9);
        Node c = new Node("C", 10);
        Node d = new Node("D", 5);

        Node g = new Node("G", 0);

        s.addConnection(a, 2);
        s.addConnection(b, 3);

        a.addConnection(s, 2);
        a.addConnection(b, 2);
        a.addConnection(d, 4);
            
        b.addConnection(s, 3);
        b.addConnection(a, 2);
        b.addConnection(d, 7);
        b.addConnection(c, 5);

        c.addConnection(b, 5);

        d.addConnection(a, 4);
        d.addConnection(b, 7);
        d.addConnection(g, 6);

        g.addConnection(d, 6);
        
        traverse(s, g);
        
    }
    
}