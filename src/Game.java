import java.awt.Color;
import java.util.LinkedList;

public class Game implements Utilities{
    private Map map;
    //private LinkedList<Entity> entities;
    private LinkedList<Snake> snakes;
    private LinkedList<Entity> apples;
    private LinkedList<Entity> shadowRealm;

    public Game(App app, int mapSize, int snakeAmount){
        System.out.println("building game...");
        //entities = new LinkedList<Entity>();
        snakes = new LinkedList<Snake>();
        apples = new LinkedList<Entity>();
        shadowRealm = new LinkedList<Entity>();

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
        for(int i=0; i<turns; i++){
            System.out.println("\nturn " + i + ": ");
            for(int j=0; j<snakes.size(); j++){
                Snake temp = snakes.get(j);
                try{
                    Thread.sleep(delay);
                }catch (InterruptedException ie){}
                // snake moved to this point
                contestedBlock = temp.move();
                if(contestedBlock != null){
                    System.out.println(temp.getColor() + " movement conflicts at " + contestedBlock); 
                    findVictim(contestedBlock, temp);
                }
            }
        }
        System.out.println("\nout of turns...\n\n\n\n\n");
    }

    // handle refresh of new block for other entity
    public void findVictim(Point point, Snake attacker){
        boolean dead;
        Entity temp = null;

        // check apples
        for(int i=0; i<apples.size(); i++){
            temp = apples.get(i);
            if(temp.contains(point)){
                dead = temp.bitten(point);
                if(dead){
                    attacker.addTitle(appleTitle());
                    shadowRealm.add(apples.remove(i));
                }
                return;
            }
        }

        // check snakes if no apple was bitten
        for(int i=0; i<snakes.size(); i++){
            temp = snakes.get(i);
            if(temp.getColor() != attacker.getColor() && temp.contains(point)){
                dead = temp.bitten(point);
                if(dead){
                    attacker.addTitle(snakeTitle());
                    shadowRealm.add(snakes.remove(i));
                }
                return;
            }
        }
    }

    // so how is everyone doing in the end?:
    public void summary(){
        System.out.println  ("\n\n\n\n--------------------------------------------------------------------");
        System.out.println          ("--------------------------end game summary--------------------------\n\n");
        
        System.out.println("snakes alive: ");
        for(int i=0; i<snakes.size(); i++){
            System.out.println(snakes.get(i) + snakes.get(i).getTitles() + "\n");
        }

        System.out.println("\n\napples alive: ");
        for(int i=0; i<apples.size(); i++){
            System.out.println(apples.get(i));
        }

        System.out.println("\n\nthose who died: ");
        for(int i=0; i<shadowRealm.size(); i++){
            System.out.println(shadowRealm.get(i));
        }
    }
}