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
        int[] s1xs = {1,1,1,2,2,3,3,3,3,4,4,5};
        int[] s1ys = {1,2,3,3,4,4,5,6,7,7,8,8};
        Snake snake1 = new Snake(app, Color.GREEN.darker(), s1xs, s1ys, "east", map);
        players.add(snake1);

        int[] s2xs = {mapSize-2,mapSize-2,mapSize-2,mapSize-3,mapSize-3,mapSize-4,mapSize-4};
        int[] s2ys = {mapSize-2,mapSize-3,mapSize-4,mapSize-4,mapSize-5,mapSize-5,mapSize-6};
        //Snake snake2 = new Snake(app, Color.BLUE.darker(), s2xs, s2ys, "east", map);
        //players.add(snake2);
    }

    public void start(int turns, int delay){
        int[] moveCounter = {0,0,0,0};
        int index;
        for(int i=1; i<turns+1; i++){
            try{
                Thread.sleep(delay);
            }catch (InterruptedException ie){}
            System.out.print(i + ": ");
            index = players.getFirst().move();
            moveCounter[index]++;
            //players.get(1).move();
        }
        System.out.println("\tup: " + moveCounter[0] + "\tright: " + moveCounter[1] + "\tdown: " + moveCounter[2] + "\tleft: " + moveCounter[3]);
        System.out.println("\nout of turns...");
    }
}
