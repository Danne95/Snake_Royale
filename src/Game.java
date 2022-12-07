import java.awt.Color;
import java.util.LinkedList;

public class Game{
    private Map map;
    private LinkedList<Entity> entities;

    public Game(App app, int mapSize, int snakeAmount){
        System.out.println("building game...");
        entities = new LinkedList<Entity>();

        // map
        map = new Map(app, mapSize);

        // snake1
        int[] s1xs = {1,1,1,2,2,3,3,3,3,4};//,4,5};
        int[] s1ys = {1,2,3,3,4,4,5,6,7,7};//,8,8};
        Snake snake1 = new Snake(app, Color.GREEN.darker(), s1xs, s1ys, "east", map);
        entities.add(snake1);

        //int[] s2xs = {mapSize-2,mapSize-2,mapSize-2,mapSize-3,mapSize-3,mapSize-4,mapSize-4};
        //int[] s2ys = {mapSize-2,mapSize-3,mapSize-4,mapSize-4,mapSize-5,mapSize-5,mapSize-6};
        //Snake snake2 = new Snake(app, Color.BLUE.darker(), s2xs, s2ys, "east", map);
        //entities.add(snake2);

        // apple1
        Apple apple1 = new Apple(Color.RED.brighter(), new Point(4,4), map);
        entities.add(apple1);

        // apple1
        Apple apple2 = new Apple(Color.RED.brighter(), new Point(6,6), map);
        entities.add(apple2);

        // apple1
        Apple apple3 = new Apple(Color.RED.brighter(), new Point(7,3), map);
        entities.add(apple3);
    }

    public void start(int turns, int delay){
        int index;
        for(int i=1; i<turns+1; i++){
            try{
                Thread.sleep(delay);
            }catch (InterruptedException ie){}
            System.out.print(i + ": ");
            if(entities.getFirst().getClass() == Snake.class){
                index = ((Snake)entities.getFirst()).move();
            //players.get(1).move();
            }
        }
        System.out.println("\nout of turns...");
    }
}
