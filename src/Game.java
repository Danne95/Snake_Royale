import java.awt.Color;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Game implements Utilities{
    private Map map;
    //private LinkedList<Entity> entities;
    private LinkedList<Snake> snakes;
    private LinkedList<Entity> apples;

    public Game(App app, int mapSize, int snakeAmount){
        System.out.println("building game...");
        //entities = new LinkedList<Entity>();
        snakes = new LinkedList<Snake>();
        apples = new LinkedList<Entity>();

        // map
        map = new Map(app, mapSize);

        // snake1
        int[] s1xs = {1,1,1,2,2,3,3,3,4,4};
        int[] s1ys = {1,2,3,3,4,4,5,6,6,7};
        Snake snake1 = new Snake(app, Color.GREEN.darker(), s1xs, s1ys, "east", map);
        snakes.add(snake1);
        
        // snake2
        int[] s2xs = {1,1,1,2,2,3,3,3,4,4};
        int[] s2ys = {mapSize-2,mapSize-3,mapSize-4,mapSize-4,mapSize-5,mapSize-5,mapSize-6,mapSize-7,mapSize-7,mapSize-8};
        Snake snake2 = new Snake(app, Color.ORANGE, s2xs, s2ys, "east", map);
        snakes.add(snake2);

        // snake3
        int[] s3xs = {mapSize-2,mapSize-2,mapSize-2,mapSize-3,mapSize-3,mapSize-4,mapSize-4,mapSize-4,mapSize-5,mapSize-5};
        int[] s3ys = {1,2,3,3,4,4,5,6,6,7};
        Snake snake3 = new Snake(app, Color.MAGENTA.darker(), s3xs, s3ys, "east", map);
        snakes.add(snake3);
        
        // snake4
        int[] s4xs = {mapSize-2,mapSize-2,mapSize-2,mapSize-3,mapSize-3,mapSize-4,mapSize-4,mapSize-4,mapSize-5,mapSize-5};
        int[] s4ys = {mapSize-2,mapSize-3,mapSize-4,mapSize-4,mapSize-5,mapSize-5,mapSize-6,mapSize-7,mapSize-7,mapSize-8};
        Snake snake4 = new Snake(app, Color.BLUE.darker(), s4xs, s4ys, "east", map);
        snakes.add(snake4);

        // apple1
        Apple apple1 = new Apple(Color.RED.brighter(), new Point(mapSize/2-3,mapSize/2-3), map);
        apples.add(apple1);

        // apple2
        Apple apple2 = new Apple(Color.RED.brighter(), new Point(mapSize/2-3,mapSize/2+3), map);
        apples.add(apple2);

        // apple3
        Apple apple3 = new Apple(Color.RED.brighter(), new Point(mapSize/2+3,mapSize/2-3), map);
        apples.add(apple3);

        // apple4
        Apple apple4 = new Apple(Color.RED.brighter(), new Point(mapSize/2+3,mapSize/2+3), map);
        apples.add(apple4);

        // apple5 ^<
        Apple apple5 = new Apple(Color.RED.brighter(), new Point(mapSize/2-10,mapSize/2-3), map);
        apples.add(apple5);

        // apple6 ^>
        Apple apple6 = new Apple(Color.RED.brighter(), new Point(mapSize/2-10,mapSize/2+3), map);
        apples.add(apple6);

        // apple7 v<
        Apple apple7 = new Apple(Color.RED.brighter(), new Point(mapSize/2+10,mapSize/2-3), map);
        apples.add(apple7);

        // apple8 v>
        Apple apple8 = new Apple(Color.RED.brighter(), new Point(mapSize/2+10,mapSize/2+3), map);
        apples.add(apple8);

        // apple9 <^
        Apple apple9 = new Apple(Color.RED.brighter(), new Point(mapSize/2-3,mapSize/2-10), map);
        apples.add(apple9);

        // apple10 >^
        Apple apple10 = new Apple(Color.RED.brighter(), new Point(mapSize/2-3,mapSize/2+10), map);
        apples.add(apple10);

        // apple11 <v
        Apple apple11 = new Apple(Color.RED.brighter(), new Point(mapSize/2+3,mapSize/2-10), map);
        apples.add(apple11);

        // apple12 >v
        Apple apple12 = new Apple(Color.RED.brighter(), new Point(mapSize/2+3,mapSize/2+10), map);
        apples.add(apple12);
        
        System.out.println(snakes.size() + " snakes and " + apples.size() + " apples.\n");
    }

    public void start(int turns, int delay){
        Point contestedBlock;
        Entity victim;
        boolean dead;
        for(int i=0; i<turns; i++){
            System.out.println("\nturn " + i + ": ");
            for(int j=0; j<snakes.size(); j++){
                Snake snakeJ = snakes.get(j);
                if(snakeJ.getAlive()){
                    try{
                        Thread.sleep(delay);
                    }catch (InterruptedException ie){}

                    // snake moved to this point
                    try{
                        contestedBlock = snakeJ.bestMove();
                        System.out.println("\tbestmove: " + contestedBlock);
                        victim = snakeJ.move(contestedBlock);//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                        if(victim != null){
                            System.out.println("\tvictim:" + victim.getColor());
                            System.out.println(snakeJ.getColor() + " attacks " + victim.getColor() + " \tmovement conflicts at " + contestedBlock); 
                            dead = victim.bitten(contestedBlock);
                            if(dead){
                                snakeJ.addTitle(newTitle(victim.getElement()));
                            }
                        }
                    }catch (NoSuchElementException nsee){
                        //trying to catch a mysterious "phantom snake" with no body(bug), 
                        summary();
                        System.exit(1);
                    }
                }
            }
        }
        System.out.println("\nout of turns...\n\n\n\n\n");
    }

    // so how is everyone doing in the end?:
    public void summary(){
        System.out.println  ("\n\n\n\n--------------------------------------------------------------------");
        System.out.println          ("--------------------------end game summary--------------------------\n\n");
        
        System.out.println("snakes: ");
        for(int i=0; i<snakes.size(); i++){
            System.out.println(snakes.get(i) + snakes.get(i).getTitles() + "\n");
        }

        System.out.println("\n\napples: ");
        for(int i=0; i<apples.size(); i++){
            System.out.println(apples.get(i));
        }
    }
}