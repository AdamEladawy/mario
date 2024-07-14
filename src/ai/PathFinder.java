package ai;

import mario.game.drawings.GamePanel;

import java.util.ArrayList;

public class PathFinder {

    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    boolean goalReached  = false;
    int step = 0;

    public PathFinder(GamePanel gp){
        this.gp = gp;
        instatiateNode();
    }
    public void instatiateNode(){
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;

//        while (col < gp.maxWorldCol && row < gp.maxWorldRow){
//            node[col][row]
//        }
    }
}