import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;

public class Game implements Utilities{
    private Map map;
    private LinkedList<Snake> players;

    public Game(App app, int mapSize){
        System.out.println("building game...");


        // map
        map = new Map(app, mapSize);

        // snake1
        LinkedList<Point> s1body = new LinkedList<>();
        Point p1 = new Point(1, 4);
        Point p2 = new Point(1, 3);
        Point p3 = new Point(1, 2);
        Point p4 = new Point(1, 1);
        s1body.add(p1);
        s1body.add(p2);
        s1body.add(p3);
        s1body.add(p4);
        Snake snake1 = new Snake(app, Color.GREEN.darker(), s1body, "right", map);
        players = new LinkedList<Snake>();
        players.add(snake1);
        map.placeSnake(snake1);
        System.out.println(snake1);

    }

    public void runSnakeRun(int turns){
        System.out.println("run snake, RUUUUN!!!");
        for(int i=1; i<turns+1; i++){
            try{
                Thread.sleep(100);
            }catch (InterruptedException ie){}
            System.out.print(i + ": ");
            players.getFirst().moveRight();
        }
        System.out.println("\nrunning over...");
    }

    public void move(){
        // move by priority, minimal value is better
        // bonus >> empty >> snake >> self
        
    }
}
