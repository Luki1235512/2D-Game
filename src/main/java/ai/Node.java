package ai;

public class Node {

    private Node parent;
    private int col;
    private int row;
    private int gCost;
    private int fCost;
    private boolean solid;
    private boolean open;
    private boolean checked;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }

}
