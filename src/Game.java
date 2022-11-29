import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;

public class Game implements Utilities{
    private Map map;
    private LinkedList<Snake> players;
    java.awt.Dimension dim;

    public Game(App app, int mapSize){
        System.out.println("building game...");
        dim = new java.awt.Dimension(160, 100); // block size

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

        // GUI
        app.getPanel().setLayout(new GridLayout(mapSize,mapSize));
        JLabel[][] grid = map.getGrid();
        for(int i=0; i<mapSize; i++){
            for(int j=0; j<mapSize; j++){
                grid[i][j] = new JLabel();
                grid[i][j].setMinimumSize(new java.awt.Dimension(dim));
                grid[i][j].setPreferredSize(new java.awt.Dimension(dim));
                grid[i][j].setMaximumSize(new java.awt.Dimension(dim));
                grid[i][j].setBorder(new LineBorder(Color.BLACK));
                grid[i][j].setBackground(map.getBlockColor(i, j));
                grid[i][j].setOpaque(true);
                app.getPanel().add(grid[i][j]);
            }
        }
        //app.getFrame().add(app.getPanel());
        app.getFrame().pack();
    }

    public void runSnakeRun(int turns){
        System.out.println("run snake, RUUUUN!!!");
        //map.printBlocky();//System.out.println(map);
        for(int i=1; i<turns+1; i++){
            try{
                Thread.sleep(500);
            }catch (InterruptedException ie){
                System.out.println("Hi");
            }
            System.out.println(i);
            players.getFirst().moveRight();
            //map.printBlocky();//System.out.println(map);
        }
        System.out.println("\nrunning over...");
    }

    public void move(){
        // move by priority, minimal value is better
        // bonus >> empty >> snake >> self
        
    }
}
