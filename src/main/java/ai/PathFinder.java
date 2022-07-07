package ai;

import main.GamePanel;

import java.util.ArrayList;

public class PathFinder {

    private GamePanel gamePanel;
    private Node[][] node;
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> pathList = new ArrayList<>();
    private Node startNode;
    private Node goalNode;
    private Node currentNode;
    private boolean goalReached = false;
    private int step = 0;

    public PathFinder(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        instantiateNodes();
    }

    public void instantiateNodes() {

        node = new Node[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        int col = 0;
        int row = 0;

        while (col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()) {
            node[col][row] = new Node(col, row);

            col++;
            if (col == gamePanel.getMaxWorldCol()) {
                col = 0;
                row++;
            }
        }
    }

    public void resetNodes() {

        int col = 0;
        int row = 0;

        while (col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()) {

            node[col][row].setOpen(false);
            node[col][row].setChecked(false);
            node[col][row].setSolid(false);

            col++;
            if (col == gamePanel.getMaxWorldCol()) {
                col = 0;
                row++;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {

        resetNodes();
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()) {

            // SET SOLID NODE
            // CHECK TILES
            int tileNum = gamePanel.getTileManager().getMapTileNum()[gamePanel.getCurrentMap()][col][row];
            if (gamePanel.getTileManager().getTile()[tileNum].collision) {
                node[col][row].setSolid(true);
            }
            // CHECK INTERACTIVE TILES
            for (int i = 0; i < gamePanel.getITile()[1].length; i++) {
                if (gamePanel.getITile()[gamePanel.getCurrentMap()][i] != null && gamePanel.getITile()[gamePanel.getCurrentMap()][i].isDestructible()) {
                    int itCol = gamePanel.getITile()[gamePanel.getCurrentMap()][i].getWorldX() / gamePanel.getTileSize();
                    int itRow = gamePanel.getITile()[gamePanel.getCurrentMap()][i].getWorldY() / gamePanel.getTileSize();
                    node[itCol][itRow].setSolid(true);
                }
            }

            // SET COST
            getCost(node[col][row]);

            col++;
            if (col == gamePanel.getMaxWorldCol()) {
                col = 0;
                row++;
            }
        }
    }
    public void getCost(Node node) {

        // G cost
        int xDistance = Math.abs(node.getCol() - startNode.getCol());
        int yDistance = Math.abs(node.getRow() - startNode.getRow());
        node.setgCost(xDistance + yDistance);

        // H cost
        xDistance = Math.abs(node.getCol() - goalNode.getCol());
        yDistance = Math.abs(node.getRow() - goalNode.getRow());
        node.sethCost(xDistance + yDistance);

        // F cost
        node.setfCost(node.getgCost() + node.gethCost());
    }

    public boolean search() {

        while (!goalReached && step < 500) {

            int col = currentNode.getCol();
            int row = currentNode.getRow();

            // Check the current node
            currentNode.setChecked(true);
            openList.remove(currentNode);

            // Open the up node
            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);
            }
            // Open the left node
            if (row - 1 >= 0) {
                openNode(node[col - 1][row]);
            }
            // Open the down
            if (row + 1 < gamePanel.getMaxWorldRow()) {
                openNode(node[col][row + 1]);
            }
            // Open the right node
            if (row + 1 < gamePanel.getMaxWorldCol()) {
                openNode(node[col + 1][row]);
            }

            // Find the best node
            int bestNodeIndex = 0;
            int bestNodeFCost = Integer.MAX_VALUE;

            for (int i = 0; i < openList.size(); i++) {

                // Check if this node's F cost is better
                if (openList.get(i).getfCost() < bestNodeFCost) {
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).getfCost();
                }
                // If F cost is equal, check the G cost
                else if (openList.get(i).getfCost() == bestNodeFCost) {
                    if (openList.get(i).getgCost() < openList.get(bestNodeIndex).getgCost()) {
                        bestNodeIndex = i;
                    }
                }
            }

            // If there is no node in the openList, end the loop
            if (openList.size() == 0) {
                break;
            }

            // After the loop, openList[bestNodeIndex] is the next step (currentNode)
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }

            step++;
        }

        return goalReached;
    }

    public void openNode(Node node) {

        if (!node.isOpen() && !node.isChecked() && !node.isSolid()) {

            node.setOpen(true);
            node.setParent(currentNode);
            openList.add(node);
        }
    }

    public void trackThePath() {

        Node current = goalNode;

        while (current != startNode) {
            pathList.add(0, current);
            current = current.getParent();
        }
    }

}
