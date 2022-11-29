import java.util.Arrays;
import javax.swing.JLabel;
import java.awt.Color;

public class Map {
    private int mapSize;
    private Color[][] map;
    private App app;
    JLabel[][] grid;

    public Map(App app, int mapSize){
        this.mapSize = mapSize;
        this.app = app;
        grid = new JLabel[mapSize][mapSize];

        map = new Color[mapSize][mapSize];
        for(int i=0; i<mapSize; i++){
            Arrays.fill(map[i], Color.LIGHT_GRAY);
        }
    }

    public void placeSnake(Snake snake){
        for(int i=0; i<snake.getLength()+1; i++){
            Point temp = snake.getBody().get(i);
            map[temp.getx()][temp.gety()] = snake.getColor();
        }
    }

    public void setBlock(Point p, Color color){
        System.out.println("setting " + p.getx() + ", " + p.gety() + " with " + color);
        map[p.getx()][p.gety()] = color;
        System.out.println("grid: " + grid);
        System.out.println("hello " + grid[p.getx()][p.gety()].toString());
        grid[p.getx()][p.gety()].setBackground(color);
        grid[p.getx()][p.gety()].setOpaque(true);
    }

    public App getApp(){return app;}
    public int getMapSize(){ return mapSize;}
    public Color getBlockColor(int i, int j){return map[i][j];}
    public JLabel[][] getGrid(){return grid;}

    public String toString(){
        return Arrays.deepToString(map);
    }

    public void printBlocky(){
        for(int i=0; i< mapSize; i++){
            for(int j=0; j< mapSize; j++)
                System.out.print(map[i][j]);
        }
        System.out.println();
    }
}
