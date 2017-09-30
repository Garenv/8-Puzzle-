import java.util.*;

public class Node {
    public boolean visited;
    public String state;
    public ArrayList<Node> children;
    public Node parent;
    
    public int cost, estimatedCostToGoal, totalCost, totaldepth;

    public int getDepth() {
        return totaldepth;
    }

    public void setDepth(int depth) {
        this.totaldepth = depth;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setTotalCost(int cost, int estimatedCost) {
        this.totalCost = cost + estimatedCost;
    }

    public int getEstimatedCostToGoal() {
        return estimatedCostToGoal;
    }

    public void setEstimatedCostToGoal(int estimatedCostToGoal) {
        this.estimatedCostToGoal = estimatedCostToGoal;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node(String state) {
        this.state = state;
        children = new ArrayList<Node>();
    }
    
    public String getState() {
        return state;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }
}