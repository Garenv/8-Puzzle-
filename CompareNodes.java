import java.util.*;

public class CompareNodes implements Comparator<Node> {
    public int compare(Node x, Node y) {
        if (x.getTotalCost() < y.getTotalCost()) {
            return -1;
        }
        if (x.getTotalCost() > y.getTotalCost()) {
            return 1;
        }
        return 0;
    }
}