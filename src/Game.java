import java.awt.Color;
import java.util.LinkedList;

public class Game implements Utilities{
    private Map map;
    private LinkedList<Snake> players;

    public Game(App app, int mapSize, int snakeAmount){
        System.out.println("building game...");
        players = new LinkedList<Snake>();

        // map
        map = new Map(app, mapSize);

        // snake1
        int[] s1xs = {1,1,1,2,2,3,3};
        int[] s1ys = {1,2,3,3,4,4,5};
        Snake snake1 = new Snake(app, Color.GREEN.darker(), s1xs, s1ys, "east", map);
        players.add(snake1);
    }

    public void start(int turns){
        // move by priority, minimal value is better
        // bonus >> empty >> snake >> self
        for(int i=1; i<turns+1; i++){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException ie){}
            System.out.print(i + ": ");
            players.getFirst().move();
        }
        System.out.println("\nout of turns...");
    }
}
