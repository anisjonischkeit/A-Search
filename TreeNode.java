import java.util.*;

public class TreeNode implements Comparable {
    
    ArrayList<ArrayList<Object>> branches = new ArrayList<ArrayList<Object>>(); // [[Node], [cost]]

    String identifier;
    int distanceFromStart;
    int distanceFromParent;
    int hieristic;
    TreeNode Parent;
    
    public TreeNode(Node node, TreeNode parent, int distanceFromParent ){
        this.identifier = node.identifier;
        this.Parent = parent;
        this.hieristic = node.hieristic;
        this.distanceFromParent = distanceFromParent;
        branches.addAll( node.connections );

        this.distanceFromStart = this.distanceFromParent;
        TreeNode currParent = this.Parent;
        if (this.Parent != null) {
            while (currParent != null) {
                this.distanceFromStart += currParent.distanceFromParent;
                currParent = currParent.Parent;
            }
        } else {
            this.distanceFromStart = 0;
            this.distanceFromParent = 0;
        }

    }

    public ArrayList<TreeNode> getBranches() {
        ArrayList<TreeNode> treeBranches = new ArrayList<TreeNode>();
        for (int i = 0; i < this.branches.get(0).size(); i++){
            treeBranches.add( new TreeNode( (Node)(this.branches.get(0).get(i)), this, (int)(this.branches.get(1).get(i)) ) );
        }

        return treeBranches;
    }

    @Override
    public int compareTo(Object arg0) 
    {
        TreeNode other = (TreeNode)(arg0);
        if(distanceFromStart + hieristic < other.distanceFromStart + other.hieristic)
        {
            return -1;
        }
        else if(distanceFromStart + hieristic > other.distanceFromStart + other.hieristic)
        {
            return 1;
        }
        return 0;
    }
    
}